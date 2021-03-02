package pl.mjuapps.flightplannerutil.utils;

import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.List;
import java.util.function.Function;

/**
 * Utility class for various functions for {@link Flight}
 */
public class FlightFunctions {

    /**
     * Convert list of {@link Flight}s to pieces of baggage
     */
    public static final Function<List<Flight>, Integer> FLIGHT_TO_BAGGAGE_PIECES_FUNCTION =
            flights -> flights.stream()
                    .flatMap(flight -> flight.getCargo().getBaggage().stream())
                    .mapToInt(baggage -> baggage.getPieces()).sum();

}
