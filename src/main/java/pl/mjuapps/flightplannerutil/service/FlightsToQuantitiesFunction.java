package pl.mjuapps.flightplannerutil.service;

import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.domain.Load;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service for converting {@link Load}s from {@link Flight}s to {@link Quantity} provided by given function
 */
@Service
public class FlightsToQuantitiesFunction
        implements BiFunction<List<Flight>, Function<Cargo, List<Load>>, List<Quantity<Mass>>> {

    /**
     * Extract {@link Load}s and convert it to {@link Quantity}
     * @param flights list of {@link Flight}s
     * @param cargoToLoadsFunction function describing how to get {@link Load} from {@link Flight}
     * @return list of {@link Load} converted to {@link Quantity}
     */
    @Override
    public List<Quantity<Mass>> apply(List<Flight> flights, Function<Cargo, List<Load>> cargoToLoadsFunction) {
        return flights.stream()
                .map(Flight::getCargo)
                .map(cargoToLoadsFunction)
                .flatMap(Collection::stream)
                .map(Load::getWeight)
                .collect(Collectors.toList());
    }
}
