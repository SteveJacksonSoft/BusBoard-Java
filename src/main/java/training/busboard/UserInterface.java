package training.busboard;

import training.busboard.models.IncomingBus;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void welcome() {
        System.out.println("Welcome to BusBoard!!");
        System.out.println("=====================");
    }

    public String getStopCode() {
        System.out.print("Please enter a stop code or enter q to quit. > ");
        String input = scanner.nextLine().toLowerCase();
        if (!input.matches("\\d+\\w*")) {
            if (input.equals("q")) {
                this.exit(-1);
            }
            System.out.println("You have entered an invalid stop code. Please try again.");
            return this.getStopCode();
        }
        return input;
    }

    public void printBuses(List<IncomingBus> nextBuses) {
        nextBuses.forEach(System.out::println);
    }

    public void exit(int status) {
        System.out.println("Programme closing.");
        System.exit(status);
    }
}
