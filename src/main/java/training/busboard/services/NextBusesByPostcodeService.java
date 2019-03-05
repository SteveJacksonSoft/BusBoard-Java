package training.busboard.services;

import training.busboard.models.Bus;
import training.busboard.models.StopPoint;
import training.busboard.requests.BusesFromStopcodeRequests;
import training.busboard.requests.PositionFromPostcodeRequest;
import training.busboard.models.Position;
import training.busboard.requests.StopPointsFromPositionRequests;

import java.util.ArrayList;
import java.util.List;

public class NextBusesByPostcodeService {
    public List<Bus> getNextBuses(String postcode, int numToGet) throws Exception {
        Position pos = new PositionFromPostcodeRequest().requestPosition(postcode);
        List<StopPoint> stopPoints = new StopPointsFromPositionRequests().requestStopPoints(pos);
        List<Bus> buses = new ArrayList<>();
        stopPoints.forEach(stopPoint ->
                buses.addAll(
                    new BusesFromStopcodeRequests().requestNextBuses(stopPoint.getStopcode())
                )
        );
        buses.sort(Bus::arrivesBefore);
        return buses.subList(0, numToGet);
    }
}
