package pl.mjuapps.flightplannerutil.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.repository.FlightRepository;
import pl.mjuapps.flightplannerutil.utils.FlightFunctions;
import pl.mjuapps.flightplannerutil.web.model.AirportFlightsDto;
import pl.mjuapps.flightplannerutil.web.rest.AirportController;

import java.time.Instant;
import java.util.List;
import java.util.function.BiFunction;

import static pl.mjuapps.flightplannerutil.utils.DateTimeFunctions.PARSE_TO_START_OF_DAY_UTC;

/**
 * Service supporting {@link AirportController}
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class FlightsForAirportApiService implements BiFunction<String, String, AirportFlightsDto> {

    public static final String NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG = "No departures and arrivals for airport %s and date %s";

    private final FlightRepository flightRepository;

    /**
     * Creates {@link AirportFlightsDto} for given airport code and date
     * @param airportCode IATA Airport code
     * @param dateString referenced date
     * @return {@link AirportFlightsDto}
     *
     * @throws {@link ResponseStatusException} when no arrivals and departures for given criteria
     */
    @Override
    public AirportFlightsDto apply(String airportCode, String dateString) {
        Instant date = PARSE_TO_START_OF_DAY_UTC.apply(dateString);
        List<Flight> departures = flightRepository.findAllByAirportAndDateAndDirection(airportCode, date, Flight::getDepartingAirport);
        List<Flight> arrivals = flightRepository.findAllByAirportAndDateAndDirection(airportCode, date, Flight::getArrivalAirport);

        if (departures.isEmpty() && arrivals.isEmpty()) {
            throwResponseStatusExceptionNotFound(airportCode, dateString);
        }

        return AirportFlightsDto.builder()
                .departingFlights(departures.size())
                .departingBaggage(FlightFunctions.FLIGHT_TO_BAGGAGE_PIECES_FUNCTION.apply(departures))
                .arrivingBaggage(FlightFunctions.FLIGHT_TO_BAGGAGE_PIECES_FUNCTION.apply(arrivals))
                .arrivingFlights(arrivals.size())
                .build();
    }

    private void throwResponseStatusExceptionNotFound(String airportCode, String dateString) {
        String message = String.format(NO_FLIGHTS_FOR_AIRPORT_AND_DATE_MSG, airportCode, dateString);
        log.info(message);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

}
