package training.busboard.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.models.Bus;
import training.busboard.models.StopPointBusData;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BusesFromStopcodeRequests {
    private static Logger LOGGER = LogManager.getLogger();

    public List<Bus> requestNextBuses(String stopcode) {

        LOGGER.debug("Sending request from requestNextBuses().");
        Stream<Bus> nextBuses = Requests.getTflBaseTarget().path("{stopcode}/Arrivals")
                .resolveTemplate("stopcode", stopcode)
                .queryParam("app_id", "25b29ea5")
                .queryParam("app_key", "ff583ea695e335856a814aedcc475d9c")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<StopPointBusData>>() {})
                    // Convert to IncomingBuses
                .stream()
                .map(StopPointBusData::toBus);

        return nextBuses.collect(Collectors.toList());
    }

}
