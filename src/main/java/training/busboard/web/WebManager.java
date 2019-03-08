package training.busboard.web;

import training.busboard.cli.DataManager;
import training.busboard.exceptionUtil.PostcodeRequestException;
import training.busboard.exceptionUtil.TflRequestException;

public class WebManager {
    private DataManager dataManager = new DataManager();

    BusInfo getBusInfoFromPostcode(String postcode, int numBusesToPrint) throws TflRequestException, PostcodeRequestException {
        return new BusInfo(postcode, dataManager.getNextBusesByPostcodeWeb(postcode, numBusesToPrint));
    }
}
