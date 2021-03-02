package pl.mjuapps.flightplannerutil.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_ARRIVAL_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_INSTANT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_IDENTIFIER;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.asserter.FlightAsserter.assertTypicalFlights;

@SpringBootTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    private Instant notMatchingDate;

    @BeforeEach
    void setUp() {
        notMatchingDate = DEFAULT_DEPARTURE_DATE_INSTANT.plus(3, ChronoUnit.DAYS);
    }

    @Test
    void findAllByIdentifierAndDepartureDate_Should_ReturnFlights() {
        assertTypicalFlights(flightRepository.findAllByIdentifierAndDepartureDate(DEFAULT_IDENTIFIER, DEFAULT_DEPARTURE_DATE_INSTANT));
    }

    @Test
    void findAllByIdentifierAndDepartureDate_Should_ReturnEmptyList_When_NoFlight_ForDate() {
        assertThat(flightRepository.findAllByIdentifierAndDepartureDate(DEFAULT_IDENTIFIER, notMatchingDate))
                .isEmpty();
    }

    @Test
    void findAllByIdentifierAndDepartureDate_Should_ReturnEmptyList_When_NoFlight_WithIdentifier() {
        assertThat(flightRepository.findAllByIdentifierAndDepartureDate(-1, DEFAULT_DEPARTURE_DATE_INSTANT))
                .isEmpty();
    }

    @Test
    void findAllByAirportAndDate_Should_ReturnFlights_When_DepartureDirection() {
        assertTypicalFlights(flightRepository.findAllByAirportAndDateAndDirection
                (DEFAULT_DEPARTURE_AIRPORT, DEFAULT_DEPARTURE_DATE_INSTANT, Flight::getDepartingAirport));
    }

    @Test
    void findAllByAirportAndDate_Should_ReturnFlights_When_ArrivalDirection() {
        assertTypicalFlights(flightRepository.findAllByAirportAndDateAndDirection
                (DEFAULT_ARRIVAL_AIRPORT, DEFAULT_DEPARTURE_DATE_INSTANT, Flight::getArrivalAirport));
    }

    @Test
    void findAllByAirportAndDate_Should_BeEmpty_When_NoDepartingAirport() {
        assertThat(flightRepository.findAllByAirportAndDateAndDirection
                (DUMMY, DEFAULT_DEPARTURE_DATE_INSTANT, Flight::getDepartingAirport))
                .isEmpty();
    }

    @Test
    void findAllByAirportAndDate_Should_BeEmpty_When_NoArrivalAirport() {
        assertThat(flightRepository.findAllByAirportAndDateAndDirection
                (DEFAULT_DEPARTURE_AIRPORT, DEFAULT_DEPARTURE_DATE_INSTANT, Flight::getArrivalAirport))
                .isEmpty();
    }

    @Test
    void findAllByAirportAndDate_Should_BeEmpty_When_NoDeparturesForDate() {
        assertThat(flightRepository.findAllByAirportAndDateAndDirection
                (DEFAULT_DEPARTURE_AIRPORT, notMatchingDate, Flight::getDepartingAirport))
                .isEmpty();
    }
    @Test
    void findAllByAirportAndDate_Should_BeEmpty_When_NoArrivalsForDate() {
        assertThat(flightRepository.findAllByAirportAndDateAndDirection
                (DEFAULT_ARRIVAL_AIRPORT, notMatchingDate, Flight::getArrivalAirport))
                .isEmpty();
    }

}