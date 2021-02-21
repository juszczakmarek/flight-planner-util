package pl.mjuapps.flightplannerutil.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cargo {

    private Flight flight;
    private List<Load> baggage;
    private List<Load> cargo;

}
