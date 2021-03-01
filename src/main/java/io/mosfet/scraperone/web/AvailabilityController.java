package io.mosfet.scraperone.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailabilityController {
    @GetMapping("/availability")
    public boolean getAvailability() {

        return true;
    }

}
