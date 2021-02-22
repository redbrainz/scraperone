package io.mosfet.scraperone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UnieuroAvailabilityRepository implements AvailabilityRepository {

    public static final String URL = "/online/Console-Playstation-5/PlayStation-5-pidSONPS5DISC";
    private final HttpClient httpClient;
    private final String host;

    public UnieuroAvailabilityRepository(HttpClient httpClient, String host) {
        this.httpClient = httpClient;
        this.host = host;
    }

    @Override
    public PlaystationAvailability retrieveAvailability() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + URL))
                .setHeader(httpHeadersUtils.USER_AGENT, httpHeadersUtils.USER_AGENT_VALUE)
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new AvailabilityException(e);
        }

        Document document = Jsoup.parse(httpResponse.body());

        boolean availability = document.getElementsByClass("product-availability").toString().contains("Non Disponibile");

        return new PlaystationAvailability(availability);
    }
}
