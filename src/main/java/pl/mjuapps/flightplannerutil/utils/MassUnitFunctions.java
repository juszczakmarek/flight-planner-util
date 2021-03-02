package pl.mjuapps.flightplannerutil.utils;

import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Functions for performing various operations on {@link Unit}
 */
public class MassUnitFunctions {

    public static final String UNIT_NOT_ALLOWED_MSG = "Unit [%s] is illegal. Allowed units: %s";
    public static final List<String> ALLOWED_UNITS = Arrays.asList("kg", "lb");

    /**
     * Function for retrieving {@link Unit} for given symbol. Currently, only supported symbols are:
     * {@link MassUnitFunctions#ALLOWED_UNITS}
     */
    public static final Function<String, Unit<Mass>> MASS_UNIT_FUNCTION = unit -> {
        Unit<Mass> massUnit = (Unit<Mass>) Units.getInstance().getUnit(unit);
        return  massUnit != null ? massUnit : (Unit<Mass>) USCustomary.getInstance().getUnit(unit);
    };

    /**
     * Consumer for checking if the provided unit is allowed
     */
    public static final Consumer<String> ALLOWED_UNIT_CONSUMER = unit -> {
        if (!ALLOWED_UNITS.contains(unit)) {
            throw new IllegalArgumentException(String.format(UNIT_NOT_ALLOWED_MSG, unit, ALLOWED_UNITS));
        }
    };

}
