package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_KILOGRAMS_WEIGHT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_POUNDS_WEIGHT;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_WEIGHT_VALUE;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.utils.QuantityFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.QuantityFunctions.UNIT_NOT_ALLOWED_MSG;

class QuantityFunctionsTest {

    @Test
    void unitFunction_Should_Return_UnitKilograms() {
        assertThat(QuantityFunctions.MASS_UNIT_FUNCTION.apply("kg"))
                .isEqualTo(Units.KILOGRAM);
    }

    @Test
    void unitFunction_Should_Return_UnitPounds() {
        assertThat(QuantityFunctions.MASS_UNIT_FUNCTION.apply("lb"))
                .isEqualTo(USCustomary.POUND);
    }

    @Test
    void unitFunction_Should_Return_Null_When_UnitOtherThanPoundOrKilogram() {
        assertThat(QuantityFunctions.MASS_UNIT_FUNCTION.apply(DUMMY))
                .isNull();
    }

    @Test
    void quantityFunction_Should_Return_QuantityOfKilograms() {
        assertThat(QuantityFunctions.QUANTITY_FUNCTION.apply("kg", DEFAULT_WEIGHT_VALUE))
                .isEqualTo(DEFAULT_KILOGRAMS_WEIGHT);
    }

    @Test
    void quantityFunction_Should_Return_QuantityOfPounds() {
        assertThat(QuantityFunctions.QUANTITY_FUNCTION.apply("lb", DEFAULT_WEIGHT_VALUE))
                .isEqualTo(DEFAULT_POUNDS_WEIGHT);
    }

    @Test
    void quantityFunction_Should_ThrowIllegalArgumentException_When_UnitNotAllowed() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> QuantityFunctions.QUANTITY_FUNCTION.apply(DUMMY, DEFAULT_WEIGHT_VALUE))
                .withMessage(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS));
    }

}