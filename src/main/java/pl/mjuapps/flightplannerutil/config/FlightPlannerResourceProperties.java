package pl.mjuapps.flightplannerutil.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Configuration class for retrieving project specific properties. As of now, there are only a few properties there.
 * Once it will grow over time, it will be required to split to smaller, logically consistent configuration classes
 */
@Configuration
@ConfigurationProperties(prefix = "flight-planner.resource")
@Data
public class FlightPlannerResourceProperties {

    public static final String FLIGHT_RESOURCE_NAME = "flight";
    public static final String CARGO_RESOURCE_NAME = "cargo";

    private String path;
    private Map<String, String> fileNameMapping;
    private Boolean autoLoadEnabled;

    public String getFlightResourcePath() { return path + fileNameMapping.get(FLIGHT_RESOURCE_NAME); }

    public String getCargoResourcePath() {
        return path + fileNameMapping.get(CARGO_RESOURCE_NAME);
    }

    public Boolean autoLoadEnabled() { return autoLoadEnabled; }

}
