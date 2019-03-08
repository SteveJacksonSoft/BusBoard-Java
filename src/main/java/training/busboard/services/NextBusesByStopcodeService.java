package training.busboard.services;

import training.busboard.exceptionUtil.TflRequestException;
import training.busboard.models.Bus;
import training.busboard.requests.BusesFromStopcodeRequest;

import java.util.Comparator;
import java.util.List;

public class NextBusesByStopcodeService {

    public static List<Bus> getNextBuses(String stopcode, int numToGet) throws TflRequestException {
        List<Bus> nextBuses = new BusesFromStopcodeRequest().requestNextBuses(stopcode);
        nextBuses.sort(Comparator.comparing(Bus::getSecondsUntilArrival));
        return nextBuses.subList(0,numToGet);
    }

}
