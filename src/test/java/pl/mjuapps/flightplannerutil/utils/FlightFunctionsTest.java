package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mjuapps.flightplannerutil.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalCargos;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalFlights;
import static pl.mjuapps.flightplannerutil.utils.FlightFunctions.FLIGHT_TO_BAGGAGE_PIECES_FUNCTION;

@SpringBootTest
class FlightFunctionsTest {

    @Autowired
    private DataProvider dataProvider;

    @BeforeEach
    void setUp() {
        dataProvider.initializeTestData(typicalFlights(), typicalCargos(false));
    }

    @Test
    void flightToBaggagePiecesFunction_Should_ReturnBaggagePieces() {
        assertThat(FLIGHT_TO_BAGGAGE_PIECES_FUNCTION.apply(dataProvider.flights()))
                .isEqualTo(10);
    }

}