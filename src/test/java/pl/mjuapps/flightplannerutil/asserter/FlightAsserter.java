package pl.mjuapps.flightplannerutil.asserter;

import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.domain.Track;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_ARRIVAL_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_AIRPORT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_INSTANT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_ID;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_IDENTIFIER;
import static pl.mjuapps.flightplannerutil.asserter.CargoAsserter.assertCargo;

public class FlightAsserter {

    public static void assertTypicalFlights(List<Flight> flights) {
        assertThat(flights)
                .hasSize(1)
                .allSatisfy(flight -> assertFlightWithNestedFields(flight));
    }

    public static void assertFlightWithNestedFields(Flight flight) {
        assertFlightBasic(flight);
        assertTrack(flight.getTrack());
        assertCargo(flight.getCargo());
    }

    public static void assertFlightBasic(Flight flight) {
        assertEquals(DEFAULT_ID, flight.getId());
        assertEquals(DEFAULT_IDENTIFIER, flight.getIdentifier());
        assertEquals(DEFAULT_DEPARTURE_DATE_INSTANT, flight.getDepartureDate());
    }

    public static void assertTrack(Track track) {
        assertEquals(DEFAULT_ARRIVAL_AIRPORT    , track.getArrivalAirport());
        assertEquals(DEFAULT_DEPARTURE_AIRPORT  , track.getDepartureAirport());
    }

}
