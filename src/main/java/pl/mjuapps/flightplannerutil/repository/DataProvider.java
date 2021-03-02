package pl.mjuapps.flightplannerutil.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.mjuapps.flightplannerutil.config.FlightPlannerResourceProperties;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.Collections;
import java.util.List;

/**
 * Class representing "dummmy" source of data. In most cases, this class wouldn't exist. Instead of this we would
 * normally have i.e. JpaRepository. But for the sake of this task, it is sufficient to provide test data in that way
 *
 * Also this component provide semi-immutability for {@link DataProvider#flights} and {@link DataProvider#cargos}.
 * It still possible to manipulate mentioned fields, because none of them is final, but proposed approach is
 * more than enough for this test application.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public final class DataProvider {

    private final FlightPlannerResourceProperties flightPlannerResourceProperties;

    private List<Flight> flights;
    private List<Cargo> cargos;

    /**
     * Initializer for test data
     * @param flights flights
     * @param cargos cargos
     */
    public void initializeTestData(@NonNull List<Flight> flights, @NonNull List<Cargo> cargos) {
        if (flightPlannerResourceProperties.getAutoLoadEnabled()) {
            initialize(flights, cargos);
        }
    }

    private void initialize(List<Flight> flights, List<Cargo> cargos) {
        if (this.flights == null && this.cargos == null) {
            this.flights = Collections.unmodifiableList(flights);
            this.cargos = Collections.unmodifiableList(cargos);
            log.info("Initialized data: \n{}\n\n{}\n", this.flights, this.cargos);
        } else {
            log.warn("Data were already initialized. Ignoring your request");
        }
    }

    /**
     * Modified getter for {@link DataProvider}.{@link Flight}s.
     * @return list of {@link Flight}s or empty list if data where not initialized yet
     */
    public List<Flight> flights() {
        if (flights == null) {
            return getFallback("flights");
        }
        return flights;
    }

    /**
     * Modified getter for {@link DataProvider}.{@link Cargo}s.
     * @return list of {@link Cargo}s or empty list if data where not initialized yet
     */
    public List<Cargo> cargos() {
        if (cargos == null) {
            return getFallback("cargos");
        }
        return cargos;
    }

    private <T> List<T> getFallback(String fieldName) {
        log.warn("{} should be instantiated first. Returning empty list as a fallback",
                fieldName);
        return Collections.emptyList();
    }
}
