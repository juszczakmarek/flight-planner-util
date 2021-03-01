package pl.mjuapps.flightplannerutil.service;

import org.springframework.stereotype.Service;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.Mass;
import java.util.function.BiFunction;

/**
 * Service responsible for converting {@link Units} of {@link Quantity}
 */
@Service
public class MassUnitConvertingFunction implements BiFunction<Quantity<Mass>, Unit<Mass>, Quantity<Mass>> {

    /**
     * Converts given {@link Quantity} to provided {@link Units} or returns given {@link Quantity}
     * if it alredy have target unit
     *
     * @param quantity quantity to converted
     * @param unit target unit
     * @return converted or given quantity (if given quantity.unit is equal to target unit)
     */
    @Override
    public Quantity<Mass> apply(Quantity<Mass> quantity, Unit<Mass> unit) {
        if (quantity.getUnit().equals(unit)) {
            return quantity;
        }
        UnitConverter unitConverter = quantity.getUnit().getConverterTo(unit);
        return Quantities.getQuantity(unitConverter.convert(quantity.getValue()), unit);
    }
}
