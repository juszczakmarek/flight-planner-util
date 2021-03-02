package pl.mjuapps.flightplannerutil.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.mjuapps.flightplannerutil.web.model.FlightWeightDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_STRING;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalFlightWeightDtoKg;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.UNIT_NOT_ALLOWED_MSG;
import static pl.mjuapps.flightplannerutil.web.service.impl.FlightWeightApiServiceImpl.FLIGHTS_NOT_FOUND_MSG;

@SpringBootTest
class FlightWeightApiServiceTest {

    @Autowired
    private FlightWeightApiService flightWeightApiService;

    @Test
    void weightDto_Should_Return_FlightWeightDto() {
        FlightWeightDto flightWeightDto = flightWeightApiService.weightDto(20210402, DEFAULT_DEPARTURE_DATE_STRING, "kg");
        assertThat(flightWeightDto)
                .isEqualTo(typicalFlightWeightDtoKg());
    }

    @Test
    void weightDto_Should_Throw_IllegalArgumentException_When_UnitNotAllowed() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> flightWeightApiService.weightDto(20210402, DEFAULT_DEPARTURE_DATE_STRING, DUMMY))
                .withMessage(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS));
    }

    @Test
    void weightDto_Should_Throw_ResponseStatusException_When_NoFlights() {
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> flightWeightApiService.weightDto(20210403, DEFAULT_DEPARTURE_DATE_STRING, "kg"))
                .withMessageContaining(String.format(FLIGHTS_NOT_FOUND_MSG, 20210403, DEFAULT_DEPARTURE_DATE_STRING))
                .withMessageContaining(HttpStatus.NOT_FOUND.toString());

    }

}