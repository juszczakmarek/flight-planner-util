package pl.mjuapps.flightplannerutil.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;

/**
 * Function for various operations on date and time
 */
public class DateTimeFunctions {

    /**
     * Converts given date represented by {@link String} to beginning of the day in {@link Instant} representation.
     * It is converted to UTC
     */
    public static final Function<String, Instant> PARSE_TO_START_OF_DAY_UTC =
            dataAsString -> LocalDate.parse(dataAsString).atStartOfDay(ZoneId.of("UTC")).toInstant();

}
