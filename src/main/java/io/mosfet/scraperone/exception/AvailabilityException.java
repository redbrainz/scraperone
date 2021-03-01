package io.mosfet.scraperone.exception;

public class AvailabilityException extends RuntimeException {
    public AvailabilityException(Exception e) {
        super(e);
    }
}
