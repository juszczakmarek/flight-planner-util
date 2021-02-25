package pl.mjuapps.flightplannerutil.asserter;

import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Load;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TYPICAL_LOADS;
import static pl.mjuapps.flightplannerutil.asserter.FlightAsserter.assertFlightBasic;

public class CargoAsserter {

    public static void assertCargo(Cargo cargo) {
        assertFlightBasic(cargo.getFlight());
        assertLoads(cargo.getBaggage());
        assertLoads(cargo.getCargo());
    }

    public static void assertLoads(List<Load> loads) {
        assertThat(loads)
                .hasSize(TYPICAL_LOADS.size())
                .containsAll(TYPICAL_LOADS);
    }

}
