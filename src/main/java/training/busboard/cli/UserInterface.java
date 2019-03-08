package training.busboard.cli;

import training.busboard.models.Bus;
import training.busboard.services.ServiceName;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    void welcome() {
        System.out.println("Welcome to BusBoard!!");
        System.out.println("=====================");
    }

    ServiceName getServiceToRun() {
        System.out.println("Would you like to search for buses by postcode (enter p) or by stopcode (enter s)?");
        System.out.print("Enter q to quit. > ");
        String input = scanner.next("[sSpPqQ]").toLowerCase();
        if (input.equals("")) {
            System.out.println("You have entered an invalid command. Please try again.");
            this.getServiceToRun();
        }
        scanner.nextLine();
        if (input.equals("q")) {
            this.exit(-1);
        }
        if (input.equals("p")) {
            return ServiceName.POSTCODE_SERVICE;
        } else {
            return ServiceName.STOPCODE_SERVICE;
        }
    }

    String getStopcode() {
        System.out.print("Please enter a stop code or enter /q/ to quit. > ");
        String input = scanner.nextLine().trim().toLowerCase();
        try {
            return this.processInput(input, "\\d+\\w*");
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid stopcode. Please try again.");
            return this.getStopcode();
        }
    }

    String getPostcode() {
        System.out.print("Please enter a postcode or enter /q/ to quit. > ");
        String input = scanner.nextLine().trim().toLowerCase();
        try {
            String processedInput = this.processInput(input, "\\w{1,2}\\d{1,2}\\s+\\d\\w\\w");
            System.out.println("Getting buses for " + processedInput.toUpperCase());
            return processedInput;
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid postcode. Please try again.");
            return this.getPostcode();
        }
    }

    void printBuses(List<Bus> nextBuses) {
        nextBuses.forEach(bus -> System.out.println(bus.writeETA()));
    }

    boolean shouldContinue() {
        System.out.print("Would you like to run a new service (y/n)? > ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            return true;
        } else if (input.equals("n")) {
            return false;
        } else {
            System.out.println("That wasn't an n or a y, but I'll assume you don't want to.");
            return false;
        }
    }

    void complain() {
        System.out.println("You have entered invalid input.");
    }

    void breakBadNews(String message) {
        System.out.println("A problem occurred when processing your request.");
        System.out.println("Error message: " + message);
    }

    void exit(int status) {
        System.out.println("Programme closing.");
        System.exit(status);
    }

    private String processInput(String input, String regex) throws InputMismatchException {
        if (!input.matches(regex)) {
            if (input.equals("/q/")) {
                this.exit(-1);
            } else {
                throw new InputMismatchException("Invalid input given.");
            }
        }
        return input;
    }
}
