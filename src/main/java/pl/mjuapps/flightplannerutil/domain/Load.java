package pl.mjuapps.flightplannerutil.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import pl.mjuapps.flightplannerutil.domain.deserializer.LoadDeserializer;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

/**
 * Representation of load. It can be considered for both: {@link Cargo#getBaggage()} and {@link Cargo#getCargo()}
 */
@Data
@JsonDeserialize(using = LoadDeserializer.class)
public class Load {

    private long id;
    private Quantity<Mass> weight;
    private int pieces;

}
