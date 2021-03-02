package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mjuapps.flightplannerutil.TestDataInitializer;
import pl.mjuapps.flightplannerutil.asserter.CargoAsserter;
import pl.mjuapps.flightplannerutil.asserter.FlightAsserter;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalCargo;
import static pl.mjuapps.flightplannerutil.service.FlightCargoRelationConsumer.INCORRECT_CARGO_FLIGHT_RELATION;

@SpringBootTest
class FlightCargoRelationConsumerTest {

    @Autowired
    private FlightCargoRelationConsumer flightCargoRelationConsumer;

    private Flight flight;
    private Cargo cargo;

    @BeforeEach
    void setUp() {
        flight = TestDataInitializer.typicalFlight();
        cargo = typicalCargo(true, flight);
        assertThat(cargo.getFlight()).hasAllNullFieldsOrPropertiesExcept("id"); //initially cargo only has flight id
    }

    @Test
    void accept_Should_UpdateRelations_When_CargoHasFlight() {
        flightCargoRelationConsumer.accept(flight, Collections.singletonList(cargo));
        FlightAsserter.assertFlightWithNestedFields(flight);
        CargoAsserter.assertCargo(cargo);
    }

    @Test
    void accept_Should_ThrowIllegalArgumentException_When_FlightNotExisting_For_Cargo() {
        cargo.getFlight().setId(99999);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> flightCargoRelationConsumer.accept(flight, Collections.singletonList(cargo)))
                .withMessage(String.format(INCORRECT_CARGO_FLIGHT_RELATION, flight, 0));
    }

    @Test
    void accept_Should_ThrowIllegalArgumentException_When_MoreThanOneCargo_For_Flight() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> flightCargoRelationConsumer.accept(
                        flight, Arrays.asList(cargo, typicalCargo(true, flight))))
                .withMessage(String.format(INCORRECT_CARGO_FLIGHT_RELATION, flight, 2));
    }

}