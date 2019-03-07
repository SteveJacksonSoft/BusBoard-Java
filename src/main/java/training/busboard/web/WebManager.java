package training.busboard.web;

import training.busboard.cli.DataManager;

public class WebManager {
    private DataManager dataManager = new DataManager();

    BusInfo getBusInfoFromPostcode(String postcode, int numBusesToPrint) {
        return new BusInfo(postcode, dataManager.getNextBusesFromPostcodeWeb(postcode, numBusesToPrint));
    }
}
