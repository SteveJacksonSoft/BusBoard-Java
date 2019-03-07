package training.busboard.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        Enum serviceName = ui.getServiceToRun();
        if (serviceName == ServiceName.STOPCODE_SERVICE) {
            this.runNextBusesByStopcodeService();
        } else if (serviceName == ServiceName.POSTCODE_SERVICE) {
            this.runNextBusesByPostcodeService();
        } else {
            ui.complain();
        }
        if (ui.shouldContinue()) {
            this.runService();
        }
    }

    private void runNextBusesByStopcodeService() {
        LOGGER.debug("Running 'next buses by stop code' service.");
        String inputStopcode = ui.getStopcode();
        LOGGER.debug("Received stop code: " + inputStopcode);
        List<Bus> nextBuses = NextBusesByStopcodeService.getNextBuses(inputStopcode, numBusesToPrint);
        LOGGER.debug("Received list of next buses from TFL.");
        ui.printBuses(nextBuses);
    }

    private void runNextBusesByPostcodeService() {
        LOGGER.debug("Running 'next buses by postcode' service.");
        String inputPostcode = ui.getPostcode();
        LOGGER.debug("Received post code: " + inputPostcode);
        try {
            List<Bus> nextBuses = NextBusesByPostcodeService.getNextBuses(inputPostcode, numBusesToPrint);
            ui.printBuses(nextBuses);
        } catch (Exception e) {
            LOGGER.error("Exception thrown whilst requesting postcode information", e);
            System.out.println("There was a problem getting information about the postcode: " + inputPostcode);
            return;
        }
    }

    public String[] getNextBusesFromPostcodeWeb(String postcode, int numBusesToPrint) {
        List<Bus> buses = NextBusesByPostcodeService.getNextBuses(postcode, numBusesToPrint);
        return buses.subList(0, numBusesToPrint).stream().map(Bus::writeETA).toArray(String[]::new);
    }

}
