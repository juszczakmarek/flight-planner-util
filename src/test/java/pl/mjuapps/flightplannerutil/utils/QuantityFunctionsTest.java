package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_VALUE;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TEN_KILOGRAMS_QUANTITY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.TEN_POUNDS_QUANTITY;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.UNIT_NOT_ALLOWED_MSG;

class QuantityFunctionsTest {

    @Test
    void quantityFunction_Should_Return_QuantityOfKilograms() {
        assertThat(QuantityFunctions.QUANTITY_FUNCTION.apply("kg", DEFAULT_VALUE))
                .isEqualTo(TEN_KILOGRAMS_QUANTITY);
    }

    @Test
    void quantityFunction_Should_Return_QuantityOfPounds() {
        assertThat(QuantityFunctions.QUANTITY_FUNCTION.apply("lb", DEFAULT_VALUE))
                .isEqualTo(TEN_POUNDS_QUANTITY);
    }

    @Test
    void quantityFunction_Should_ThrowIllegalArgumentException_When_UnitNotAllowed() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> QuantityFunctions.QUANTITY_FUNCTION.apply(DUMMY, DEFAULT_VALUE))
                .withMessage(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS));
    }

}