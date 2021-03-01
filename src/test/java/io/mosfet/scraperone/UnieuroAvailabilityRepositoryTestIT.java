package io.mosfet.scraperone;

import io.mosfet.scraperone.domain.PlaystationAvailability;
import io.mosfet.scraperone.repository.UnieuroAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockserver.model.HttpRequest.request;

class UnieuroAvailabilityRepositoryTestIT extends IntegrationTestSuite {

    @Test
    void checkAvailabilityFromUnieuro() throws IOException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        getMockServer().when(request().withMethod("GET")
                .withPath("/online/Console-Playstation-5/PlayStation-5-pidSONPS5DISC"))
                .respond(HttpResponse.response()
                        .withBody(ResourcesUtils.getResource("io/mosfet/unieuro.html")));

        UnieuroAvailabilityRepository unieuroAvailabilityRepository = new UnieuroAvailabilityRepository(httpClient, "http://localhost:9999");

        PlaystationAvailability playstationAvailability = unieuroAvailabilityRepository.retrieveAvailability();

        assertFalse(playstationAvailability.isAvailable());
    }


}