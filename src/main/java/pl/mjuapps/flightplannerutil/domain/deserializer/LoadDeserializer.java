package pl.mjuapps.flightplannerutil.domain.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import pl.mjuapps.flightplannerutil.domain.Load;
import pl.mjuapps.flightplannerutil.utils.QuantityFunctions;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.io.IOException;

/**
 * Custom deserializer for {@link Load}. Main goal for using this deserializer is
 * proper deserialization of weight
 */
public class LoadDeserializer extends JsonDeserializer<Load> {

    @Override
    public Load deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Load load = new Load();
        load.setId(node.get("id").numberValue().longValue());
        int weightValue = node.get("weight").numberValue().intValue();
        String unit = node.get("weightUnit").asText();
        Quantity<Mass> weight = QuantityFunctions.QUANTITY_FUNCTION.apply(unit, weightValue);
        load.setWeight(weight);
        load.setPieces(node.get("pieces").numberValue().intValue());
        return load;
    }
}
