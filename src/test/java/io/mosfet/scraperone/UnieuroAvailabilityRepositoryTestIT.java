package io.mosfet.scraperone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockserver.model.HttpRequest.request;

class UnieuroAvailabilityRepositoryTestIT {

    private ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(9999);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
    }

    @Test
    void checkAvailabilityFromUnieuro() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        mockServer.when(request().withMethod("GET")
                .withPath("/online/Console-Playstation-5/PlayStation-5-pidSONPS5DISC")
                .withBody(ResourcesUtils.getResource("io/mosfet/unieuro.html"))
        );

        UnieuroAvailabilityRepository unieuroAvailabilityRepository = new UnieuroAvailabilityRepository(httpClient, "http://localhost:9999");

        PlaystationAvailability playstationAvailability = unieuroAvailabilityRepository.retrieveAvailability();

        assertFalse(playstationAvailability.isAvailable());
    }


}