package pl.mjuapps.flightplannerutil.utils;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class TimeZoneCodeValidator implements Consumer<String> {

    public static final String ALLOWED_TIME_ZONE = "UTC";
    public static String TIME_ZONE_NOT_ALLOWED = "Only accepted time zone is " + ALLOWED_TIME_ZONE;

    @Override
    public void accept(String timeZone) {
        if (!timeZone.equals(ALLOWED_TIME_ZONE)) {
            throw new IllegalArgumentException(TIME_ZONE_NOT_ALLOWED);
        }
    }
}
