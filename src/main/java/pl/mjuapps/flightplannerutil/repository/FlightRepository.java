package pl.mjuapps.flightplannerutil.repository;

import pl.mjuapps.flightplannerutil.domain.Flight;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

/**
 * Repository offering convenient api for retrieving data. Method names are following the SpringData
 * methods naming convention
 */
public interface FlightRepository {

    /**
     * Return all the {@link Flight}s for referenced {@link Flight}.identifier and {@link Flight}.departureDate
     *
     * @param identifier identifier of the flight (so called flight number)
     * @param departure departure date
     * @return list of {@link Flight}s
     */
    List<Flight> findAllByIdentifierAndDepartureDate(Integer identifier, Instant departure);

    /**
     * Return all the {@link Flight}s for referenced airport and departure date. Requires describing
     *
     * @param airport referenced airport
     * @param departure date of departure
     * @param flightDirectionFunction function converting flight to departure or arriving airport
     * @return flights
     */
    List<Flight> findAllByAirportAndDateAndDirection(String airport, Instant departure,
            Function<Flight, String> flightDirectionFunction);

}
