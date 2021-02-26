package pl.mjuapps.flightplannerutil.repository;

import pl.mjuapps.flightplannerutil.domain.Flight;

import java.time.Instant;
import java.util.List;

/**
 * Repository offering convenient api for retrieving data
 */
public interface FlightRepository {

    /**
     * Return all the {@link Flight}s for referenced {@link Flight}.identifier and {@link Flight}.departureDate
     *
     * @param identifier identifier of the flight (so called flight number)
     * @param instant departure date
     * @return list of {@link Flight}s
     */
    List<Flight> findAllByIdentifierAndDepartureDate(Integer identifier, Instant instant);

}
