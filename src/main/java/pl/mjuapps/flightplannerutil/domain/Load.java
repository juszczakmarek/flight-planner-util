package pl.mjuapps.flightplannerutil.domain;

import lombok.Data;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

@Data
public class Load {

    private long id;
    private Quantity<Mass> weight;
    private long pieces;

}
