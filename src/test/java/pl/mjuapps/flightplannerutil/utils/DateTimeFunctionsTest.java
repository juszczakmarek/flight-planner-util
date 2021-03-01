package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_INSTANT;
import static pl.mjuapps.flightplannerutil.utils.DateTimeFunctions.SAME_DATE_INSTANT;

class DateTimeFunctionsTest {

    @Test
    void predicate_Should_AcceptInstants_For_TheSameDate() {
        assertTrue(SAME_DATE_INSTANT.test(
                DEFAULT_DEPARTURE_DATE_INSTANT,
                DEFAULT_DEPARTURE_DATE_INSTANT.plus(3, ChronoUnit.HOURS)));
    }

    @Test
    void predicate_Should_RejectInstants_For_TheSameDate() {
        assertFalse(SAME_DATE_INSTANT.test(
                DEFAULT_DEPARTURE_DATE_INSTANT,
                DEFAULT_DEPARTURE_DATE_INSTANT.plus(1, ChronoUnit.DAYS)));
    }

}