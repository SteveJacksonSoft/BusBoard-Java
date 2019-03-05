package training.busboard.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.models.IncomingBus;
import training.busboard.models.StopPointBusData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrivalEstimationRequest {
    private static Logger LOGGER = LogManager.getLogger();

    public static List<IncomingBus> requestNextBuses(String stopCode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        LOGGER.debug("Sending request from requestNextBuses().");
        Stream<IncomingBus> nextBuses = client.target("https://api.tfl.gov.uk/StopPoint/{stopCode}/Arrivals")
                .resolveTemplate("stopCode", stopCode)
                .queryParam("app_id", "25b29ea5")
                .queryParam("app_key", "ff583ea695e335856a814aedcc475d9c")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<StopPointBusData>>() {})
                    // Convert to IncomingBuses
                .stream()
                .map(StopPointBusData::toBus);


        client.close();

        return nextBuses.collect(Collectors.toList());
    }

}
