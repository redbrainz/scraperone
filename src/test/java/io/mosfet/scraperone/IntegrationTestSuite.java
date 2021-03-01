package io.mosfet.scraperone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.integration.ClientAndServer;

public class IntegrationTestSuite {

    private ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(9999);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
    }


    public ClientAndServer getMockServer() {
        return mockServer;
    }
}
