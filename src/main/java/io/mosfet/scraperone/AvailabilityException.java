package io.mosfet.scraperone;

public class AvailabilityException extends RuntimeException {
    public AvailabilityException(Exception e) {
        super(e);
    }
}
