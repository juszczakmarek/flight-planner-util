package pl.mjuapps.flightplannerutil.utils;

import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.function.BiFunction;

import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNIT_CONSUMER;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.MASS_UNIT_FUNCTION;

/**
 * Functions offering various operation on {@link Quantity}
 */
public class QuantityFunctions {

    /**
     * Function providing {@link Quantity} for given weight value and unit
     */
    public static final BiFunction<String, Integer, Quantity<Mass>> QUANTITY_FUNCTION = (symbol, weight) -> {
        ALLOWED_UNIT_CONSUMER.accept(symbol);
        return Quantities.getQuantity(weight, MASS_UNIT_FUNCTION.apply(symbol));
    };

}
