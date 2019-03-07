package training.busboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.models.Bus;
import training.busboard.services.NextBusesByPostcodeService;
import training.busboard.services.NextBusesByStopcodeService;

import java.util.List;

public class Manager {
    private static Logger LOGGER = LogManager.getLogger();
    private final int numBusesToPrint = 5;
    private UserInterface ui = new UserInterface();

    public void run() {
        ui.welcome();
        this.runService();
    }

    private void runService() {
        String serviceCode = ui.getServiceToRun();
        if (serviceCode.equals("s")) {
            this.runNextBusesByStopcodeService();
        } else if (serviceCode.equals("p")) {
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
            List<Bus> nextBuses = new NextBusesByPostcodeService().getNextBuses(inputPostcode, numBusesToPrint);
            ui.printBuses(nextBuses);
        } catch (Exception e) {
            LOGGER.error("Exception thrown whilst requesting postcode information", e);
            System.out.println("There was a problem getting information about the postcode: " + inputPostcode);
            return;
        }
    }
}
