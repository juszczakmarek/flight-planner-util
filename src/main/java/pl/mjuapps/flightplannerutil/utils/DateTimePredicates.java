package pl.mjuapps.flightplannerutil.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.BiPredicate;

/**
 * Utility class for date and time related predicates.
 */
public class DateTimePredicates {

    public static final String DATE_TIME_WITH_ZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss ZZZZZ";

    /**
     * Checks if two {@link Instant}s are sharing the same date (ignoring hours, minutes, etc.)
     */
    public static final BiPredicate<Instant, Instant> SAME_DATE_INSTANT = (inst1, inst2) ->
            inst1.truncatedTo(ChronoUnit.DAYS).equals(inst2.truncatedTo(ChronoUnit.DAYS));

}
