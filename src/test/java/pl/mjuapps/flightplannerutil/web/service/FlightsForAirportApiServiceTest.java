package pl.mjuapps.flightplannerutil.web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.server.ResponseStatusException;
import pl.mjuapps.flightplannerutil.config.FlightPlannerResourceProperties;
import pl.mjuapps.flightplannerutil.repository.DataProvider;
import pl.mjuapps.flightplannerutil.web.model.AirportFlightsDto;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_STRING;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_VALUE;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.setUpMockedFlights;
import static pl.mjuapps.flightplannerutil.web.service.FlightsForAirportApiService.NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
class FlightsForAirportApiServiceTest {

    @MockBean
    private FlightPlannerResourceProperties flightPlannerResourceProperties;

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private FlightsForAirportApiService flightsForAirportApiService;

    @BeforeEach
    void setUp() {
        setUpMockedFlights(dataProvider, flightPlannerResourceProperties);
    }

    @Test
    void airportFlights_Should_Return_AirportFlightsDto_WithDepartures() {
        assertDefaultAirportFlightsDto(
                flightsForAirportApiService.apply(DEFAULT_DEPARTURE_AIRPORT, DEFAULT_DEPARTURE_DATE_STRING));
    }

    @Test
    void airportFlights_Should_ThrowResponseStatusException_When_NoArrivalsAndDepartures_ForAirport() {
        assertThatExceptionOfType(ResponseStatusException.class)
            .isThrownBy(() -> flightsForAirportApiService.apply(DUMMY, DEFAULT_DEPARTURE_DATE_STRING))
            .withMessageContaining(String.format(NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG, DUMMY, DEFAULT_DEPARTURE_DATE_STRING));
    }

    @Test
    void airportFlights_Should_ThrowResponseStatusException_When_NoArrivalsAndDepartures_ForDate() {
        assertThatExceptionOfType(ResponseStatusException.class)
            .isThrownBy(() -> flightsForAirportApiService.apply(DEFAULT_DEPARTURE_AIRPORT, "2000-01-01"))
            .withMessageContaining(String.format(NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG, DEFAULT_DEPARTURE_AIRPORT, "2000-01-01"));
    }

    public static void assertDefaultAirportFlightsDto(AirportFlightsDto airportFlightsDto) {
        assertEquals(DEFAULT_VALUE , airportFlightsDto.getArrivingBaggage());
        assertEquals(DEFAULT_VALUE , airportFlightsDto.getDepartingBaggage());
        assertEquals(1    , airportFlightsDto.getArrivingFlights());
        assertEquals(1    , airportFlightsDto.getDepartingFlights());
    }
}