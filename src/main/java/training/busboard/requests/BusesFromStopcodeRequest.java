package training.busboard.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.exceptionUtil.ApiRequestException;
import training.busboard.exceptionUtil.TflRequestException;
import training.busboard.models.Bus;
import training.busboard.models.StopPointBusData;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class BusesFromStopcodeRequest {
    private static Logger LOGGER = LogManager.getLogger();

    public List<Bus> requestNextBuses(String stopcode) throws TflRequestException {

        LOGGER.debug("Sending request from requestNextBuses() for stopcode: " + stopcode);
        Response response = Requests.getTflBaseTarget().path("{stopcode}/Arrivals")
                .resolveTemplate("stopcode", stopcode)
                .queryParam("app_id", "25b29ea5")
                .queryParam("app_key", "ff583ea695e335856a814aedcc475d9c")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        try {
            Requests.checkStatusCode(response, Api.TFL);
        } catch (ApiRequestException e) {
            throw new TflRequestException(e);
        }

        return response.readEntity(new GenericType<List<StopPointBusData>>() {})
                .stream()
                .map(StopPointBusData::toBus)
                .collect(Collectors.toList());
    }

}
