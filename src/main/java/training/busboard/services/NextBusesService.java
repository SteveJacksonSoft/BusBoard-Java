package training.busboard.services;

import training.busboard.models.IncomingBus;
import training.busboard.requests.ArrivalEstimationRequest;

import java.util.List;

public class NextBusesService {

    public static List<IncomingBus> getNextBuses(String stopCode, int numToGet) {

        List<IncomingBus> nextBuses = ArrivalEstimationRequest.requestNextBuses(stopCode);
        nextBuses.sort(IncomingBus::arrivesBefore);
        return nextBuses.subList(0,numToGet);
    }

}
