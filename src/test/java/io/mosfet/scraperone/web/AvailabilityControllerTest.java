package io.mosfet.scraperone.web;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.net.URISyntaxException;

class AvailabilityControllerTest {
    @Test
    void isSuccessful() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AvailabilityController()).build();
        mockMvc.perform(MockMvcRequestBuilders.get(new URIBuilder("/availability").build())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}