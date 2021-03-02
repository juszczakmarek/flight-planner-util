package pl.mjuapps.flightplannerutil.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of track between two airports. Normally, this class would be much more complex and for sure, normally
 * each node on the track should represented by Airport object not String. However, due to simplicity of this task
 * there is a very simple approach proposed
 */
@Data
public class Track {

    @JsonProperty("departureAirportIATACode")
    private String departureAirport;

    @JsonProperty("arrivalAirportIATACode")
    private String arrivalAirport;

}
