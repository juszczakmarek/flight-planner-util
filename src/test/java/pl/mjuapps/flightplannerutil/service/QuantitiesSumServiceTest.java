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
import java.util.List;

import static pl.mjuapps.flightplannerutil.TestDataInitializer.ONE_KG_TO_POUNDS;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TEN_KILOGRAMS_QUANTITY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalQuantities;
import static pl.mjuapps.flightplannerutil.service.MassUnitConvertingFunctionTest.assertQuantity;

@SpringBootTest
class QuantitiesSumServiceTest {

    @Autowired
    private QuantitiesSumService quantitiesSumService;

    private List<Quantity<Mass>> quantities;

    @BeforeEach
    void setUp() {
        quantities = typicalQuantities();
        quantities.add(TEN_KILOGRAMS_QUANTITY);
    }

    @Test
    void apply_Should_ReturnSum_When_QuantitiesAreInKg_And_SumInKg() {
        assertQuantity(quantitiesSumService.apply(quantities, Units.KILOGRAM), 20D, Units.KILOGRAM);
    }

    @Test
    void apply_Should_ReturnSum_When_QuantitiesAreInKgAndLb_And_SumInKg() {
        quantities.add(Quantities.getQuantity(ONE_KG_TO_POUNDS * 5, USCustomary.POUND));
        assertQuantity(quantitiesSumService.apply(quantities, Units.KILOGRAM), 25D, Units.KILOGRAM);
    }


}