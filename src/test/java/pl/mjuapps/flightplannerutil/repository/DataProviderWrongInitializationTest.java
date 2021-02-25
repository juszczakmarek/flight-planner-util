package pl.mjuapps.flightplannerutil.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static java.util.Collections.emptyList;
import static pl.mjuapps.flightplannerutil.asserter.AssertionUtils.assertNullPointerException;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
public class DataProviderWrongInitializationTest {

    @Autowired
    private DataProvider dataProvider;

    @Test
    void initializeTestData_Should_ThrowNPE_WithMessage_When_FlightAreNull() {
        assertNullPointerException(() -> dataProvider.initializeTestData(null, emptyList()),
                                   "flights");
    }

    @Test
    void initializeTestData_Should_ThrowNPE_WithMessage_When_CargosAreNull() {
        assertNullPointerException(() -> dataProvider.initializeTestData(emptyList(), null),
                                   "cargos");
    }

}
