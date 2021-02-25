package pl.mjuapps.flightplannerutil.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.mjuapps.flightplannerutil.asserter.CargoAsserter;
import pl.mjuapps.flightplannerutil.asserter.FlightAsserter;
import pl.mjuapps.flightplannerutil.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostConstructDataInitializationProcessTest {

    @Autowired
    private DataProvider dataProvider;

    @Test
    void data_Should_BeInitialized_When_ApplicationStarts() {
        assertThat(dataProvider.flights())
                .hasSize(1)
                .anySatisfy(FlightAsserter::assertFlightWithNestedFields);
        assertThat(dataProvider.cargos())
                .hasSize(1)
                .anySatisfy(CargoAsserter::assertCargo);
    }





}