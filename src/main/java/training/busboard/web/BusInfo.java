package training.busboard.web;

import training.busboard.models.Bus;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private List<Bus> buses;

    public BusInfo(String postcode, List<Bus> buses) {
        this.postcode = postcode.toUpperCase();
        this.buses = buses;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<Bus> getBuses() {
        return buses;
    }
}
