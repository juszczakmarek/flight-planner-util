package pl.mjuapps.flightplannerutil.domain.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.mjuapps.flightplannerutil.domain.Load;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.createLoad;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.UNIT_NOT_ALLOWED_MSG;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false")
class LoadDeserializerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deserialize_Should_ReturnProperLoadObject() throws JsonProcessingException {
        assertThat(objectMapper.readValue(properLoadJson(1, "kg"), Load.class))
                .isEqualTo(createLoad(1));
    }

    //testing only special case for Quantity deserialization. All other incorrect cases will result in
    //throwing exception anyway
    @Test
    void deserialize_Should_ThrowIllegalArgumentException_When_UnitIsNotAllowed() throws JsonProcessingException {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> objectMapper.readValue(properLoadJson(1, DUMMY), Load.class))
                .withMessage(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS));
    }

    private static String properLoadJson(int value, String unitSymbol) {
        return "{\n"
                + "\"id\": " + value + ",\n"
                + "\"weight\": "  + value + ",\n"
                + "\"weightUnit\": \""+ unitSymbol + "\",\n"
                + "\"pieces\": " + value + "\n"
                + "}";
    }

}