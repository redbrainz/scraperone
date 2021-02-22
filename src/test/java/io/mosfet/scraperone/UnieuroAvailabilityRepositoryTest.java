package io.mosfet.scraperone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UnieuroAvailabilityRepositoryTest {

    @Mock
    public HttpClient httpClient;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldReturnAvailabilityException() throws IOException, InterruptedException {

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(AvailabilityException.class);

        UnieuroAvailabilityRepository availabilityRepository = new UnieuroAvailabilityRepository(
                httpClient, "http://localhost");

        assertThrows(AvailabilityException.class, availabilityRepository::retrieveAvailability);
    }
}
