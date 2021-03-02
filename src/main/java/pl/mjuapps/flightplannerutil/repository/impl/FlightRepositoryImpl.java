package pl.mjuapps.flightplannerutil.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.repository.DataProvider;
import pl.mjuapps.flightplannerutil.repository.FlightRepository;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.mjuapps.flightplannerutil.utils.DateTimePredicates.SAME_DATE_INSTANT;

@RequiredArgsConstructor
@Service
public class FlightRepositoryImpl implements FlightRepository {

    private final DataProvider dataProvider;

    @Override
    public List<Flight> findAllByIdentifierAndDepartureDate(Integer identifier, Instant departure) {
        return dataProvider.flights().stream()
                .filter(flight -> flight.getIdentifier().equals(identifier))
                .filter(flight -> SAME_DATE_INSTANT.test(flight.getDepartureDate(), departure))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> findAllByAirportAndDateAndDirection(String airport, Instant departure, Function<Flight, String> flightDirectionFunction) {
        return dataProvider.flights().stream()
                .filter(flight -> flightDirectionFunction.apply(flight).equals(airport))
                .filter(flight -> SAME_DATE_INSTANT.test(flight.getDepartureDate(), departure))
                .collect(Collectors.toList());
    }

}
