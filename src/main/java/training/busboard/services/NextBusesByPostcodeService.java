package training.busboard.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.models.Bus;
import training.busboard.models.StopPoint;
import training.busboard.requests.BusesFromStopcodeRequests;
import training.busboard.requests.PositionFromPostcodeRequest;
import training.busboard.models.Position;
import training.busboard.requests.StopPointsFromPositionRequests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NextBusesByPostcodeService {
    private static Logger LOGGER = LogManager.getLogger();

    public static List<Bus> getNextBuses(String postcode, int numToGet) {
        try {
            Position pos = new PositionFromPostcodeRequest().requestPosition(postcode);

            List<StopPoint> stopPoints = new StopPointsFromPositionRequests().requestStopPoints(pos);
            List<Bus> buses = new ArrayList<>();
            stopPoints.forEach(stopPoint ->
                    buses.addAll(
                            new BusesFromStopcodeRequests().requestNextBuses(stopPoint.getStopcode())
                    )
            );
            buses.sort(Comparator.comparing(Bus::getSecondsUntilArrival));
            return buses.subList(0, numToGet);

        } catch (Exception e) {
            LOGGER.error("Exception thrown whilst requesting postcode information", e);
            System.out.println("There was a problem getting information about the postcode: " + postcode);
            return new ArrayList<>();
        }
    }
}
