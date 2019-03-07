package training.busboard.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.models.Position;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PositionFromPostcodeRequest {
    private static Logger LOGGER = LogManager.getLogger();

    public Position requestPosition(String postcode) throws Exception {
        LOGGER.debug("Writing postcode request to: https://api.postcodes.io/postcodes/" + postcode);

        Response response = Requests.getPostcodeBaseTarget()
                .path(postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        if (response.getStatus()/100 != 2) {
            LOGGER.error(response);
            throw new Exception("Status code not 200.");
        }

        return response.readEntity(PositionResult.class).getResult();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PositionResult {
        private Position result;

        public Position getResult() {
            return result;
        }
    }
}
