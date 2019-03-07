package training.busboard.services;

import training.busboard.models.Bus;
import training.busboard.requests.BusesFromStopcodeRequests;

import java.util.Comparator;
import java.util.List;

public class NextBusesByStopcodeService {

    public static List<Bus> getNextBuses(String stopcode, int numToGet) {
        List<Bus> nextBuses = new BusesFromStopcodeRequests().requestNextBuses(stopcode);
        nextBuses.sort(Comparator.comparing(Bus::getSecondsUntilArrival));
        return nextBuses.subList(0,numToGet);
    }

}
