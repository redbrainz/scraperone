package io.mosfet.scraperone.web;

import io.mosfet.scraperone.port.AvailabilityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailabilityController {
    private final AvailabilityRepository availabilityRepository;

    public AvailabilityController(AvailabilityRepository availabilityRepository) {

        this.availabilityRepository = availabilityRepository;
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> getAvailability() {

        if (availabilityRepository.retrieveAvailability().isAvailable()) {
            return ResponseEntity.ok(true);
        }
       return ResponseEntity.status(400).body(false);
    }

}
