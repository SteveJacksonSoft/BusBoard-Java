package training.busboard.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.exceptionUtil.PostcodeRequestException;
import training.busboard.exceptionUtil.TflRequestException;
import training.busboard.models.Bus;
import training.busboard.services.NextBusesByPostcodeService;
import training.busboard.services.NextBusesByStopcodeService;
import training.busboard.services.ServiceName;

import java.util.List;

public class DataManager {
    private static Logger LOGGER = LogManager.getLogger();
    private final int numBusesToPrint = 5;
    private UserInterface ui = new UserInterface();

    void run() {
        ui.welcome();
        this.runService();
    }

    private void runService() {
        ServiceName serviceName = ui.getServiceToRun();
        if (serviceName == ServiceName.STOPCODE_SERVICE) {
            this.getNextBusesByStopcodeCLI();
        } else if (serviceName == ServiceName.POSTCODE_SERVICE) {
            this.getNextBusesByPostcodeCLI();
        } else {
            ui.complain();
        }
        if (ui.shouldContinue()) {
            this.runService();
        }
    }

    private void getNextBusesByStopcodeCLI() {
        LOGGER.debug("Running 'next buses by stop code' service.");
        String inputStopcode = ui.getStopcode();
        LOGGER.debug("Received stop code: " + inputStopcode);
        try {
            List<Bus> nextBuses = NextBusesByStopcodeService.getNextBuses(inputStopcode, numBusesToPrint);
            LOGGER.debug("Received list of next buses from TFL.");
            ui.printBuses(nextBuses);
        } catch (TflRequestException e) {
            LOGGER.error(e);
            ui.breakBadNews(e.getMessage());
        }
    }

    private void getNextBusesByPostcodeCLI() {
        LOGGER.debug("Running 'next buses by postcode' service.");
        String inputPostcode = ui.getPostcode();
        LOGGER.debug("Received post code: " + inputPostcode);
        try {
            List<Bus> nextBuses = NextBusesByPostcodeService.getNextBuses(inputPostcode, numBusesToPrint);
            ui.printBuses(nextBuses);
        } catch (Exception e) {
            ui.breakBadNews(e.getMessage());
        }
    }

    public List<Bus> getNextBusesByPostcodeWeb(String postcode, int numBusesToPrint) throws PostcodeRequestException, TflRequestException {
        List<Bus> buses = NextBusesByPostcodeService.getNextBuses(postcode, numBusesToPrint);
        if (buses.size() > numBusesToPrint) {
            return buses.subList(0, numBusesToPrint);
        } else {
            return buses;
        }
    }

}
