package pl.mjuapps.flightplannerutil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.utils.MassUnitFunctions;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Perform sum operation on the given
 */
@Service
@RequiredArgsConstructor
public class QuantitiesSumService implements BiFunction<List<Quantity<Mass>>, Unit<Mass>, Quantity<Mass>> {

    private final QuantitiesConvertingFunction quantitiesConvertingFunction;

    /**
     * Return sum of all given quantities. To make sum operations possible, it starts with the unifying
     * of all units to match given unit.
     *
     * @param quantities quantities to be summed
     * @param unit in which calculation will be performed. Supported units are {@link MassUnitFunctions#ALLOWED_UNITS}
     * @return result of sum operation in referenced unit
     */
    @Override
    public Quantity<Mass> apply(List<Quantity<Mass>> quantities, Unit<Mass> unit) {
        List<Quantity<Mass>> convertedQuantities = quantitiesConvertingFunction.apply(quantities, unit);
        Quantity<Mass> totalMass = Quantities.getQuantity(0, unit);
        for (Quantity<Mass> massQuantity : convertedQuantities) {
            totalMass = massQuantity.add(totalMass);
        }
        return totalMass;
    }

}
