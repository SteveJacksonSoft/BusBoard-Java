package training.busboard.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

class Requests {
    private static Logger LOGGER = LogManager.getLogger();
    private static final String appId = "25b29ea5";
    private static final String appKey = "ff583ea695e335856a814aedcc475d9c";
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

    static String getAppId() {
        return appId;
    }

    static String getAppKey() {
        return appKey;
    }
}
