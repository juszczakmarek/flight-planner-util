package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.ONE_KG_TO_POUNDS;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TEN_KILOGRAMS_QUANTITY;
import static pl.mjuapps.flightplannerutil.service.MassUnitConvertingFunctionTest.assertQuantity;

@SpringBootTest
class QuantitiesConvertingFunctionTest {

    @Autowired
    private QuantitiesConvertingFunction quantitiesConvertingFunction;

    private List<Quantity<Mass>> quantities;

    @BeforeEach
    void setUp() {
        quantities = new ArrayList<>();
        quantities.add(TEN_KILOGRAMS_QUANTITY);
    }

    @Test
    void apply_Should_ReturnGivenQuantities_When_HaveExpectedUnit() {
        assertThat(quantitiesConvertingFunction.apply(quantities, Units.KILOGRAM))
                .hasSize(1)
                .allSatisfy(quantity -> assertThat(quantity).isEqualTo(TEN_KILOGRAMS_QUANTITY));
    }

    @Test
    void apply_Should_Return_KgQuantities_When_OneIsKg_And_OneIsLb() {
        quantities.add(Quantities.getQuantity(ONE_KG_TO_POUNDS, USCustomary.POUND));
        assertThat(quantitiesConvertingFunction.apply(quantities, Units.KILOGRAM))
                .hasSize(2)
                .anySatisfy(quantity -> assertQuantity(quantity, TEN_KILOGRAMS_QUANTITY.getValue().doubleValue(), Units.KILOGRAM))
                .anySatisfy(quantity -> assertQuantity(quantity, 1D, Units.KILOGRAM));
    }

}