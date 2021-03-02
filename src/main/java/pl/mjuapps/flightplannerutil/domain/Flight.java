package pl.mjuapps.flightplannerutil.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.Instant;

import static pl.mjuapps.flightplannerutil.utils.DateTimePredicates.DATE_TIME_WITH_ZONE_PATTERN;

/**
 * Representation of flight from json
 */
@Data
public class Flight {

    @JsonProperty("flightId")
    private Integer id;

    @JsonProperty("flightNumber")
    private Integer identifier;

    @JsonUnwrapped
    private Track track;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_WITH_ZONE_PATTERN)
    private Instant departureDate;

    @JsonIgnore
    private Cargo cargo;

    public Flight addCargo(Cargo cargo) {
        this.cargo = cargo;
        cargo.setFlight(this);
        return this;
    }

    /**
     * Modified getter for retrieving departing airport
     * @return airport IATA code
     */
    @JsonIgnore
    public String getDepartingAirport() {
        return track.getDepartureAirport();
    }

    /**
     * Modified getter for retrieving arrival airport
     * @return airport IATA code
     */
    @JsonIgnore
    public String getArrivalAirport() {
        return track.getArrivalAirport();
    }

    @Override public String toString() {
        return "\nFlight{"
                + "id=" + id
                + ", identifier='" + identifier + '\''
                + ", track=" + track
                + ", departureDate=" + departureDate
                + ", cargo=" + cargo + "}";
    }
}
