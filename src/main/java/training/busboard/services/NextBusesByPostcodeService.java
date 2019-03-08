package training.busboard.services;

import training.busboard.exceptionUtil.PostcodeRequestException;
import training.busboard.exceptionUtil.TflRequestException;
import training.busboard.exceptionUtil.TflRequestRuntimeException;
import training.busboard.models.Bus;
import training.busboard.models.StopPoint;
import training.busboard.requests.BusesFromStopcodeRequest;
import training.busboard.requests.PositionFromPostcodeRequest;
import training.busboard.models.Position;
import training.busboard.requests.StopPointsFromPositionRequest;
import training.busboard.exceptionUtil.LambdaWrappers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NextBusesByPostcodeService {
    public static List<Bus> getNextBuses(String postcode, int numToGet) throws PostcodeRequestException, TflRequestException {
        try {
            Position pos = new PositionFromPostcodeRequest().requestPosition(postcode);

            List<StopPoint> stopPoints = new StopPointsFromPositionRequest().requestStopPoints(pos);
            List<Bus> buses = new ArrayList<>();
            stopPoints.forEach(LambdaWrappers.tflExceptionLambdaWrapper(stopPoint ->
                        buses.addAll(
                                new BusesFromStopcodeRequest().requestNextBuses(stopPoint.getStopcode())
                        )
            ));
            buses.sort(Comparator.comparing(Bus::getSecondsUntilArrival));
            if (buses.size() > numToGet) {
                return buses.subList(0, numToGet);
            } else {
                return buses;
            }

        } catch (TflRequestRuntimeException e) {
            throw new TflRequestException(e);
        }
    }
}
