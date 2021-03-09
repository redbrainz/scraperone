package io.mosfet.scraperone.configuration;

import io.mosfet.scraperone.port.AvailabilityRepository;
import io.mosfet.scraperone.repository.UnieuroAvailabilityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public AvailabilityRepository availabilityRepository(HttpClient httpclient) {
        return new UnieuroAvailabilityRepository(httpclient, "https://www.unieuro.it");
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }
}
