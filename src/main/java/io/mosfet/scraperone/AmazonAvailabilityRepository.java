package io.mosfet.scraperone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmazonAvailabilityRepository implements AvailabilityRepository {

    private static final String URL = "/Sony-PlayStation-5/dp/B08KJF2D25/ref=as_li_ss_tl?th=1&linkCode=sl1&tag=offcl-21&linkId=954db62b8906f088fcd3f47a24d45235&language=it_IT";
    private final HttpClient httpClient;
    private final String host;

    public AmazonAvailabilityRepository(HttpClient httpClient, String host) {
        this.httpClient = httpClient;
        this.host = host;
    }

    @Override
    public PlaystationAvailability retrieveAvailability() {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + URL))
                .setHeader(HttpHeadersUtils.USER_AGENT, HttpHeadersUtils.USER_AGENT_VALUE)
                .setHeader(HttpHeadersUtils.CONTENT_TYPE, HttpHeadersUtils.CONTENT_TYPE_VALUE)
                .build();

        HttpResponse<String> httpResponse = call(request);
        Document document = Jsoup.parse(httpResponse.body());

        boolean availability = document.getElementById("availability").toString().contains("Non disponibile.");

        return new PlaystationAvailability(!availability);
    }

    private HttpResponse<String> call(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AvailabilityException(e);
        }
    }
}
