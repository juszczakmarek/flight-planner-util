package pl.mjuapps.flightplannerutil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Converter of the {@link Unit} for given {@link Quantity}s
 */
@Service
@RequiredArgsConstructor
public class QuantitiesConvertingFunction
        implements BiFunction<List<Quantity<Mass>>, Unit<Mass>, List<Quantity<Mass>>> {

    private final MassUnitConvertingFunction convertingFunction;

    /**
     * Convert units for given {@link Quantity}s
     * @param quantities quantities to be converted
     * @param massUnit target unit
     * @return converted quantities
     */
    @Override
    public List<Quantity<Mass>> apply(List<Quantity<Mass>> quantities, Unit<Mass> massUnit) {
        return quantities.stream()
                .map(quantity -> convertingFunction.apply(quantity, massUnit))
                .collect(Collectors.toList());
    }
}
