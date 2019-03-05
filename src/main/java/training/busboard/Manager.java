package training.busboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.busboard.models.IncomingBus;
import training.busboard.services.NextBusesService;

import java.util.List;

public class Manager {
    private static Logger LOGGER = LogManager.getLogger();
    private UserInterface ui = new UserInterface();

    public void run() {
        ui.welcome();
        this.runNextBusesByStopCodeService();
    }

    private void runNextBusesByStopCodeService() {
        LOGGER.debug("Running 'next buses by stop code' service.");
        String inputStopCode = ui.getStopCode();
        LOGGER.debug("Received stop code: " + inputStopCode);
        List<IncomingBus> nextBuses = NextBusesService.getNextBuses(inputStopCode, 5);
        LOGGER.debug("Received list of next buses from TFL.");
        ui.printBuses(nextBuses);
        ui.exit(0);
    }
}
