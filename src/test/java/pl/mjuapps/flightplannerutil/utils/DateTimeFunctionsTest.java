package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_STRING;

class DateTimeFunctionsTest {

    @Test
    void paresToStartOfDayUTC_Should_ReturnInstantStartOfTheDay() {
        assertThat(DateTimeFunctions.PARSE_TO_START_OF_DAY_UTC.apply(DEFAULT_DEPARTURE_DATE_STRING))
                .isEqualTo(Instant.ofEpochSecond(1541116800));
    }

}