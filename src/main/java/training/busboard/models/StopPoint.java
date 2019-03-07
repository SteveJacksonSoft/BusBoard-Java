package training.busboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {
    private String commonName;
    private String lat;
    private String lon;
    private String stopcode;

    private StopPoint(){}

    public String getCommonName() {
        return commonName;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    @JsonProperty("naptanId")
    public String getStopcode() {
        return stopcode;
    }
}
