package io.mosfet.scraperone.port;

import io.mosfet.scraperone.domain.PlaystationAvailability;

public interface AvailabilityRepository {
    PlaystationAvailability retrieveAvailability();
}
