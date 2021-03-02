package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;

@SpringBootTest
class TimeZoneCodeValidatorTest {

    @Autowired
    private TimeZoneCodeValidator timeZoneCodeValidator;

    @Test
    void should_ThrowIllegalArgumentException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> timeZoneCodeValidator.accept(DUMMY))
                .withMessage(TimeZoneCodeValidator.TIME_ZONE_NOT_ALLOWED);
    }

    @Test
    void should_Accept() {
        timeZoneCodeValidator.accept(TimeZoneCodeValidator.ALLOWED_TIME_ZONE);
    }

}