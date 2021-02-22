package io.mosfet.scraperone;

import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockserver.model.HttpRequest.request;

class AmazonAvailabilityRepositoryTestIT extends IntegrationTestSuite {

    @Test
    void retrieveDataFromAmazon() throws IOException {

        getMockServer().when(request().withMethod("GET"))
                .respond(HttpResponse.response()
                        .withBody(ResourcesUtils.getResource("io/mosfet/amazon.html")));

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        AmazonAvailabilityRepository amazonAvailabilityRepository = new AmazonAvailabilityRepository(httpClient, "http://localhost:9999");

        assertFalse(amazonAvailabilityRepository.retrieveAvailability().isAvailable());
    }
}