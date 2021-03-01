package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_WEIGHT_VALUE;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.ONE_KG_TO_POUNDS;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TEN_KILOGRAMS_QUANTITY;

@SpringBootTest
class MassUnitConvertingFunctionTest {

    @Autowired
    private MassUnitConvertingFunction massUnitConvertingFunction;

    @Test
    void apply_Should_ReturnGivenQuantity_When_UnitIsTheSame() {
        assertThat(massUnitConvertingFunction.apply(TEN_KILOGRAMS_QUANTITY, Units.KILOGRAM))
                .isEqualTo(TEN_KILOGRAMS_QUANTITY);
    }

    @Test
    void apply_Should_ReturnPounds_When_ConvertingKilogramsToPounds() {
        Quantity<Mass> quantity = massUnitConvertingFunction.apply(TEN_KILOGRAMS_QUANTITY, USCustomary.POUND);
        assertQuantity(quantity, ONE_KG_TO_POUNDS * DEFAULT_WEIGHT_VALUE, USCustomary.POUND);
    }

    public static void assertQuantity(Quantity<Mass> actual, Double value, Unit unit) {
        assertEquals(unit, actual.getUnit());
        assertEquals(value, actual.getValue().doubleValue());
    }

}