package training.busboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointBusData {
    private static Logger LOGGER = LogManager.getLogger();
    private String naptanId;
    private String lineId;
    private int timeToStation;
    private String stationName;

    public StopPointBusData() {}

//    public StopPointBusData(String naptanId, String lineId, String expectedArrival, String stationName) {
//        this.naptanId = naptanId;
//        this.lineId = lineId;
//        this.expectedArrival = expectedArrival;
//        this.stationName = stationName;
//    }

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

    public Bus toBus() {
        return new Bus(lineId, timeToStation, stationName);
    }
}