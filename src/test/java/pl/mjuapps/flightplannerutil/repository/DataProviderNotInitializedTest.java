package pl.mjuapps.flightplannerutil.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.asserter.AssertionUtils.assertListIsSemiImmutable;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false")
public class DataProviderNotInitializedTest {

    @Autowired
    private DataProvider dataProvider;

    @Test
    void dataProvider_Should_HaveEmptyDataLists_When_InitializeTestData_Not_Executed() {
        assertThat(dataProvider.flights()).isEmpty();
        assertThat(dataProvider.cargos()).isEmpty();
    }

    @Test
    void dataProvider_Should_ProvideImmutableListOf_Flights_When_InitializeTestData_Not_Executed() {
        assertListIsSemiImmutable(dataProvider.flights(), Flight.class, false);
    }

    @Test
    void dataProvider_Should_ProvideImmutableListOf_Cargos_When_InitializeTestData_Not_Executed() {
        assertListIsSemiImmutable(dataProvider.cargos(), Cargo.class, false);
    }

}
