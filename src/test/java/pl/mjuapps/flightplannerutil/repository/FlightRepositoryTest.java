package pl.mjuapps.flightplannerutil.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_INSTANT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_IDENTIFIER;
import static pl.mjuapps.flightplannerutil.asserter.FlightAsserter.assertFlightWithNestedFields;

@SpringBootTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void findAllByIdentifierAndDepartureDate_ShouldReturnFlights() {
        assertThat(flightRepository.findAllByIdentifierAndDepartureDate(DEFAULT_IDENTIFIER, DEFAULT_DEPARTURE_DATE_INSTANT))
                .hasSize(1)
                .allSatisfy(flight -> assertFlightWithNestedFields(flight));
    }

    @Test
    void findAllByIdentifierAndDepartureDate_Should_ReturnEmptyList_When_NoFlight_ForDate() {
        Instant notMatchingDate = DEFAULT_DEPARTURE_DATE_INSTANT.plus(3, ChronoUnit.DAYS);
        assertThat(flightRepository.findAllByIdentifierAndDepartureDate(DEFAULT_IDENTIFIER, notMatchingDate))
                .isEmpty();
    }

    @Test
    void findAllByIdentifierAndDepartureDate_Should_ReturnEmptyList_When_NoFlight_WithIdentifier() {
        assertThat(flightRepository.findAllByIdentifierAndDepartureDate(-1, DEFAULT_DEPARTURE_DATE_INSTANT))
                .isEmpty();
    }

}