package training.busboard.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.exceptionUtil.ApiRequestException;
import training.busboard.exceptionUtil.TflRequestException;
import training.busboard.models.Position;
import training.busboard.models.StopPoint;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class StopPointsFromPositionRequest {
    private static Logger LOGGER = LogManager.getLogger();

    public List<StopPoint> requestStopPoints(Position position) throws TflRequestException {
        LOGGER.debug("Sending request to get stop points from position [" + position.getLatitude() + "," + position.getLatitude() + "].");
        Response response = Requests.getTflBaseTarget()
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("radius", "500")
                .queryParam("useStopPointHierarchy", "true")
                .queryParam("modes", "bus")
                .queryParam("returnLines", "true")
                .queryParam("lat", position.getLatitude())
                .queryParam("lon", position.getLongitude())
                .queryParam("app_id", Requests.getTflAppId())
                .queryParam("app_key", Requests.getTflAppKey())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        try {
            Requests.checkStatusCode(response, Api.TFL);
        } catch (ApiRequestException e) {
            throw new TflRequestException(e);
        }


        return response.readEntity(StopPointsResult.class).getStopPoints();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StopPointsResult {
        List<StopPoint> stopPoints;

        private StopPointsResult() {}

        public List<StopPoint> getStopPoints() {
            return stopPoints;
        }
    }

}
