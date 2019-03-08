package training.busboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointBusData {
    private String naptanId;
    private String lineId;
    private int timeToStation;
    private String stationName;
    private String destinationName;

    public StopPointBusData() {}

    public String getNaptanId() {
        return naptanId;
    }

    public String getLineId() {
        return lineId;
    }

    public int getTimeToStation() {
        return timeToStation;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public Bus toBus() {
        return new Bus(lineId, timeToStation, stationName, destinationName);
    }
}