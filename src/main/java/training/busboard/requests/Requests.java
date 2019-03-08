package training.busboard.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;
import training.busboard.exceptionUtil.ApiRequestException;
import training.busboard.exceptionUtil.TflRequestException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

class Requests {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String tflAppId = "25b29ea5";
    private static final String tflAppKey = "ff583ea695e335856a814aedcc475d9c";
    private static final String tflApiUri = "https://api.tfl.gov.uk/StopPoint/";
    private static final String postcodeApiUri = "https://api.postcodes.io/postcodes/";
    private static Client client = ClientBuilder.newBuilder().register(JacksonFeatures.class).build();
    private static WebTarget tflBaseTarget = client.target(tflApiUri);
    private static WebTarget postcodeBaseTarget = client.target(postcodeApiUri);

    static WebTarget getTflBaseTarget() {
        return tflBaseTarget;
    }

    static WebTarget getPostcodeBaseTarget() {
        return postcodeBaseTarget;
    }

    static String getTflAppId() {
        return tflAppId;
    }

    static String getTflAppKey() {
        return tflAppKey;
    }

    static void checkStatusCode(Response response, Api api) throws ApiRequestException {
        LOGGER.debug("Received response status: " + response.getStatus());
        if (response.getStatus()/100 == 4) {
            LOGGER.error("Received a 4xx status code from " + api + " api.");
            LOGGER.error(response);
            throw new ApiRequestException ("Received status code " + response.getStatus() + " from " + api.toString().toLowerCase() + " api.", response);
        }
    }
}
