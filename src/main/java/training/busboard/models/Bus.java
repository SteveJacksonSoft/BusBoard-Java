package training.busboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {
    private static Logger LOGGER = LogManager.getLogger();
    private String lineId;
    private int secondsUntilArrival;
    private String stopName;

    public Bus(String lineId, int secondsUntilArrival, String stopName) {
        this.lineId = lineId.toUpperCase();
        this.secondsUntilArrival = secondsUntilArrival;
        this.stopName = stopName;
    }

    public int arrivesBefore(Bus otherBus) {
        if (this.secondsUntilArrival < otherBus.secondsUntilArrival) {
            return -1;
        } else if (this.secondsUntilArrival > otherBus.secondsUntilArrival) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        String output;

        LOGGER.debug("Writing output string for bus ");
        int[] arrivalTime = this.getTimeInHMS(secondsUntilArrival);
        if (arrivalTime[0] != 0) {
            output = String.format("Bus %s will arrive at %s in %dh, %2dm and %2ds.",
                    this.lineId, this.stopName, arrivalTime[0], arrivalTime[1], arrivalTime[2]);
        } else if (arrivalTime[1] != 0) {
            output = String.format("Bus %s will arrive at %s in %2dm and %2ds.",
                    this.lineId, this.stopName, arrivalTime[1], arrivalTime[2]);
        } else if (arrivalTime[2] != 0) {
            output = String.format("Bus %s will arrive at %s in %2ds.",
                    this.lineId, this.stopName, arrivalTime[2]);
        } else {
            output = String.format("Bus %s is due to arrive at %s immediately.",
                    this.lineId, this.stopName);
        }

        return output;
    }

    private int[] getTimeInHMS(int seconds) {
        int[] timeInHMS = new int[3];
        timeInHMS[0] = seconds / 3600;
        timeInHMS[1] = (seconds - timeInHMS[0]*3600) / 60;
        timeInHMS[2] = seconds - (timeInHMS[0]*3600) - (timeInHMS[1]*60);
        return timeInHMS;
    }
}
