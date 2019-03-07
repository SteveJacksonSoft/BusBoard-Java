package training.busboard.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;
import training.busboard.models.Position;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PositionFromPostcodeRequest {
    private static Logger LOGGER = LogManager.getLogger();

    public Position requestPosition(String postcode) throws Exception {
        LOGGER.debug("Writing postcode request to: https://api.postcodes.io/postcodes/" + postcode);
        Client client = ClientBuilder.newBuilder().register(JacksonFeatures.class).build();

        Response response = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        if (response.getStatus() != 200) {
            throw new Exception();
        }

        return response.readEntity(PositionResult.class).getResult();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PositionResult {
        private Position result;

//        private PositionResult() {}

        public Position getResult() {

            return result;
        }
    }
}
