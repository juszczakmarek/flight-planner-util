package pl.mjuapps.flightplannerutil.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

/**
 * Representation of Cargo from json
 */
@Data
public class Cargo {

    @JsonUnwrapped
    @JsonIgnoreProperties({"flightNumber", "track.departureAirportIATACode", "track.arrivalAirportIATACode", "departureDate"})
    private Flight flight;

    private List<Load> baggage;
    private List<Load> cargo;

    @Override public String toString() {
        return "Cargo{"
                + "flight=" + flight.getId()
                + ", baggage=" + baggage
                + ", cargo=" + cargo + '}';
    }
}
