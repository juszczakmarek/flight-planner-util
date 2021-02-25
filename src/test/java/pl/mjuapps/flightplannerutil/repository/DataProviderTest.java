package pl.mjuapps.flightplannerutil.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mjuapps.flightplannerutil.asserter.CargoAsserter;
import pl.mjuapps.flightplannerutil.asserter.FlightAsserter;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalCargos;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalFlights;
import static pl.mjuapps.flightplannerutil.asserter.AssertionUtils.assertListIsSemiImmutable;

@SpringBootTest
class DataProviderTest {

    @Autowired
    private DataProvider dataProvider;

    @BeforeEach
    void setUp() {
        dataProvider.initializeTestData(typicalFlights(), typicalCargos(false));
    }

    @Test
    void dataProvider_ShouldContain_ListOf_Flights() {
        assertThat(dataProvider.flights())
                .hasSize(1)
                .allSatisfy(flight -> FlightAsserter.assertFlightBasic(flight));
    }

    @Test
    void dataProvider_ShouldContain_ListOf_Cargos() {
        assertThat(dataProvider.cargos())
                .hasSize(1)
                .allSatisfy(cargo -> CargoAsserter.assertCargo(cargo));
    }

    @Test
    void dataProvider_Should_ProvideImmutableListOf_Flights_When_DataInitialized() {
        assertListIsSemiImmutable(dataProvider.flights(), Flight.class, true);
    }

    @Test
    void dataProvider_Should_ProvideImmutableListOf_Cargos_When_DataInitialized() {
        assertListIsSemiImmutable(dataProvider.cargos(), Cargo.class, true);
    }

    @Test
    void dataProvider_Should_BeInitializedOnlyOnce() {
        dataProvider.initializeTestData(Collections.emptyList(), Collections.emptyList());
        //below assertions mean, that second init was ignored
        dataProvider_ShouldContain_ListOf_Flights();
        dataProvider_ShouldContain_ListOf_Cargos();
    }




}