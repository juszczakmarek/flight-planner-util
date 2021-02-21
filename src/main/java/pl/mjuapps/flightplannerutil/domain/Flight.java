package pl.mjuapps.flightplannerutil.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class Flight {

    private long id;
    private long number;
    private Airport departure;
    private Airport arrival;
    private Instant departureDate;

}
