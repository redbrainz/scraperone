package io.mosfet.scraperone.domain;

public class PlaystationAvailability {

    private final boolean availability;

    public PlaystationAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isAvailable() {
        return availability;
    }
}
