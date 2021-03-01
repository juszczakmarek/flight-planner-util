package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.domain.Load;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalCargo;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalFlights;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalLoads;

@SpringBootTest
class FlightsToQuantitiesFunctionTest {

    @Autowired
    private FlightsToQuantitiesFunction flightsToQuantitiesFunction;

    List<Flight> flights;

    @BeforeEach
    void setUp() {
        flights = typicalFlights();
        Flight flight = flights.get(0);
        Cargo cargo = typicalCargo(false, flight);
        flight.setCargo(cargo);
    }

    @Test
    void apply_Should_ReturnBaggageQuantities_For_Flights() {
        assertQuantities(flightsToQuantitiesFunction.apply(flights, Cargo::getCargo), typicalLoads());
    }

    private static void assertQuantities(List<Quantity<Mass>> quantities, List<Load> loads) {
        assertThat(quantities)
                .allSatisfy(quantity -> assertLoadForQuantity(quantity, loads));
    }

    private static void assertLoadForQuantity(Quantity<Mass> quantity, List<Load> loads) {
        assertThat(loads)
                .anySatisfy(load -> load.getWeight().equals(quantity));
    }

}