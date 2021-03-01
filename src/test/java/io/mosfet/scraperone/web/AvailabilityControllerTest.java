package io.mosfet.scraperone.web;

import io.mosfet.scraperone.domain.PlaystationAvailability;
import io.mosfet.scraperone.port.AvailabilityRepository;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AvailabilityControllerTest {
    @Mock
    private AvailabilityRepository availabilityRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void isSuccessful() throws Exception {
        when(availabilityRepository.retrieveAvailability()).thenReturn(new PlaystationAvailability(true));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AvailabilityController(availabilityRepository)).build();
        mockMvc.perform(MockMvcRequestBuilders.get(new URIBuilder("/availability").build())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    void is4xx() throws Exception {
        when(availabilityRepository.retrieveAvailability()).thenReturn(new PlaystationAvailability(false));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AvailabilityController(availabilityRepository)).build();
        mockMvc.perform(MockMvcRequestBuilders.get(new URIBuilder("/availability").build())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("false"));
    }
}