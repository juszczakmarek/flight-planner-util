package pl.mjuapps.flightplannerutil.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of track between two airports. Normally, this class would be much more complex
 */
@Data
public class Track {

    @JsonProperty("departureAirportIATACode")
    private String departureAirport;

    @JsonProperty("arrivalAirportIATACode")
    private String arrivalAirport;

}
