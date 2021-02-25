package pl.mjuapps.flightplannerutil.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Consumer for setting bi-directional relation between {@link Flight} and {@link Cargo}. This is needed, as
 * json with Cargo data contains only ids of {@link Flight}s, not the real object reference. So, after deserialization
 * {@link Cargo} contains {@link Flight} object, but only field which is set is {@link Flight#getId()} while other
 * fields are null
 *
 * <ul>Throws {@link IllegalArgumentException} when:
 *  <li>cargo references flight which is not existing</li>
 *  <li>more than cargo reference the same flight</li>
 * </ul>
 */
@Service
@Log4j2
public class FlightCargoRelationConsumer implements BiConsumer<Flight, List<Cargo>> {

    public static final String INCORRECT_CARGO_FLIGHT_RELATION = "Incorrect cargo-flight relation. Exactly one cargo can be assigned to flight. "
            + "Number of cargos found for flight %s = %s";

    @Override
    public void accept(Flight flight, List<Cargo> cargos) {
        List<Cargo> cargosForFlight = cargos.stream().filter(c -> flight.getId().intValue() == c.getFlight().getId().intValue())
                .collect(Collectors.toList());
        if (cargosForFlight.size() != 1) {
            throw new IllegalArgumentException(String.format(INCORRECT_CARGO_FLIGHT_RELATION, flight, cargosForFlight.size()));
        }
        flight.addCargo(cargosForFlight.iterator().next());
    }
}
