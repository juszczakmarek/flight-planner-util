package pl.mjuapps.flightplannerutil.utils;

import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Functions for operating on {@link Quantity}
 */
public class QuantityFunctions {

    public static final String UNIT_NOT_ALLOWED_MSG = "Unit [%s] is illegal. Allowed units: %s";
    public static final List<String> ALLOWED_UNITS = Arrays.asList("kg", "lb");

    /**
     * Function for retrieving {@link Unit<Mass>} for given symbol. Currently, only supported symbols are:
     * {@link QuantityFunctions#ALLOWED_UNITS}
     */
    public static final Function<String, Unit<Mass>> MASS_UNIT_FUNCTION = unit -> {
        Unit<Mass> massUnit = (Unit<Mass>) Units.getInstance().getUnit(unit);
        return  massUnit != null ? massUnit : (Unit<Mass>) USCustomary.getInstance().getUnit(unit);
    };

    /**
     * Function providing {@link Quantity<Mass>} for given weight value and unit
     */
    public static final BiFunction<String, Integer, Quantity<Mass>> QUANTITY_FUNCTION = (symbol, weight) -> {
        if (!ALLOWED_UNITS.contains(symbol)) {
            throw new IllegalArgumentException(String.format(UNIT_NOT_ALLOWED_MSG, symbol, ALLOWED_UNITS));
        }
        return Quantities.getQuantity(weight, MASS_UNIT_FUNCTION.apply(symbol));
    };
}
