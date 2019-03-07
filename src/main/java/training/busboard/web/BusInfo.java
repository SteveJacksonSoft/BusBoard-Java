package training.busboard.web;

public class BusInfo {
    private final String postcode;
    private String[] linesOfBusETA;

    public BusInfo(String postcode, String[] linesOfBusETA) {
        this.postcode = postcode.toUpperCase();
        this.linesOfBusETA = linesOfBusETA;
    }

    public String getPostcode() {
        return postcode;
    }

    public String[] getLinesOfBusETA() {
        return linesOfBusETA;
    }
}
