package io.mosfet.scraperone;

import java.io.IOException;

public interface AvailabilityRepository {
    PlaystationAvailability retrieveAvailability() throws IOException, InterruptedException;
}
