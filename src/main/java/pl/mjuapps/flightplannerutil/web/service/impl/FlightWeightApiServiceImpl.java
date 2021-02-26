package pl.mjuapps.flightplannerutil.web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.repository.FlightRepository;
import pl.mjuapps.flightplannerutil.service.FlightsToQuantitiesFunction;
import pl.mjuapps.flightplannerutil.service.QuantitiesSumService;
import pl.mjuapps.flightplannerutil.web.model.FlightWeightDto;
import pl.mjuapps.flightplannerutil.web.service.FlightWeightApiService;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNIT_CONSUMER;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.MASS_UNIT_FUNCTION;

@Service
@RequiredArgsConstructor
@Log4j2
public class FlightWeightApiServiceImpl implements FlightWeightApiService {

    public static final String FLIGHTS_NOT_FOUND_MSG = "No flights for given identifier %s and date %s";

    private final FlightRepository flightRepository;
    private final QuantitiesSumService quantitiesSumService;
    private final FlightsToQuantitiesFunction toQuantitiesFunction;

    @Override
    public FlightWeightDto weightDto(Integer identifier, String dateString, String unitSymbol) {
        ALLOWED_UNIT_CONSUMER.accept(unitSymbol);
        Instant instant = LocalDate.parse(dateString).atStartOfDay(ZoneId.of("UTC")).toInstant();
        Unit<Mass> unit = MASS_UNIT_FUNCTION.apply(unitSymbol);
        List<Flight> flights = flightRepository.findAllByIdentifierAndDepartureDate(identifier, instant);
        if (flights.isEmpty()) {
            throwResponseStatusExceptionNotFound(identifier, dateString);
        }
        Quantity<Mass> totalCargo = quantitiesSumService.apply(toQuantitiesFunction.apply(flights, Cargo::getCargo), unit);
        Quantity<Mass> totalBaggage = quantitiesSumService.apply(toQuantitiesFunction.apply(flights, Cargo::getBaggage), unit);
        return FlightWeightDto.builder()
                .unit(unitSymbol)
                .baggageTotalWeight(totalBaggage.getValue().doubleValue())
                .cargoTotalWeight(totalCargo.getValue().doubleValue())
                .totalWeight(totalBaggage.add(totalCargo).getValue().doubleValue())
                .build();
    }


    private void throwResponseStatusExceptionNotFound(Integer identifier, String dateString) {
        String message = String.format(FLIGHTS_NOT_FOUND_MSG, identifier, dateString);
        log.info(message);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

}
