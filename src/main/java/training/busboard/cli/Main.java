package training.busboard.cli;

public class Main {
    public static void main(String args[]) {
        System.out.println("App started.");

        DataManager dataManager = new DataManager();

        dataManager.run();
    }
}	
