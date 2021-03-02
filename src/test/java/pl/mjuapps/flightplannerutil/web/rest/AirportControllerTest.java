package pl.mjuapps.flightplannerutil.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.mjuapps.flightplannerutil.config.FlightPlannerResourceProperties;
import pl.mjuapps.flightplannerutil.repository.DataProvider;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_STRING;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_VALUE;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.setUpMockedFlights;
import static pl.mjuapps.flightplannerutil.web.service.FlightsForAirportApiService.NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
class AirportControllerTest {

    private static final String URL = "/api/airports/{airportCode}/flights?date={date}";

    @MockBean
    private FlightPlannerResourceProperties flightPlannerResourceProperties;

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        setUpMockedFlights(dataProvider, flightPlannerResourceProperties);
    }

    @Test
    void should_Return_AirportFlightsDto() throws Exception {
        mockMvc.perform(get(URL, DEFAULT_DEPARTURE_AIRPORT, DEFAULT_DEPARTURE_DATE_STRING))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.departingFlights").value(1))
                .andExpect(jsonPath("$.departingBaggage").value(DEFAULT_VALUE))
                .andExpect(jsonPath("$.arrivingFlights" ).value(1))
                .andExpect(jsonPath("$.arrivingBaggage" ).value(DEFAULT_VALUE));
    }

    @Test
    void should_Return_NotFound_When_NoFlightsFor_Airport() throws Exception {
        mockMvc.perform(get(URL, DUMMY, DEFAULT_DEPARTURE_DATE_STRING))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(String.format(NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG, DUMMY, DEFAULT_DEPARTURE_DATE_STRING)));
    }

    @Test
    void should_Return_NotFound_When_NoFlightsFor_Date() throws Exception {
        mockMvc.perform(get(URL, DEFAULT_DEPARTURE_AIRPORT, "2000-01-01"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(String.format(NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG, DEFAULT_DEPARTURE_AIRPORT, "2000-01-01")));
    }

}