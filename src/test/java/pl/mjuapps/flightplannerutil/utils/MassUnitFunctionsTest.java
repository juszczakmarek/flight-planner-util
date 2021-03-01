package pl.mjuapps.flightplannerutil.utils;

import org.junit.jupiter.api.Test;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNIT_CONSUMER;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.UNIT_NOT_ALLOWED_MSG;

class MassUnitFunctionsTest {

    @Test
    void massUnitFunction_Should_Return_UnitKilograms() {
        assertThat(MassUnitFunctions.MASS_UNIT_FUNCTION.apply("kg"))
                .isEqualTo(Units.KILOGRAM);
    }

    @Test
    void massUnitFunction_Should_Return_UnitPounds() {
        assertThat(MassUnitFunctions.MASS_UNIT_FUNCTION.apply("lb"))
                .isEqualTo(USCustomary.POUND);
    }

    @Test
    void massUnitFunction_Should_Return_Null_When_UnitOtherThanPoundOrKilogram() {
        assertThat(MassUnitFunctions.MASS_UNIT_FUNCTION.apply(DUMMY))
                .isNull();
    }

    @Test
    void allowedUnitConsumer_Should_DoNothing_When_Unit_Allowed() {
        ALLOWED_UNIT_CONSUMER.accept("kg");
        ALLOWED_UNIT_CONSUMER.accept("lb");
    }

    @Test
    void allowedUnitConsumer_Should_ThrowIllegalArgumentException_When_Unit_NotAllowed() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ALLOWED_UNIT_CONSUMER.accept(DUMMY))
                .withMessage(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS));
    }

}