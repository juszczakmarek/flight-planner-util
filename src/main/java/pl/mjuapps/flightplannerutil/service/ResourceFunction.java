package pl.mjuapps.flightplannerutil.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Function for retrieving {@link List} of objects from resource (resource == json file)
 * @param <T> referenced type (in our case it is one of: {@link Cargo}, {@link Flight}
 */
@Service
@RequiredArgsConstructor
public class ResourceFunction<T> implements BiFunction<Resource, Class<T>, List<T>> {

    private final ObjectMapper objectMapper;

    /**
     * Returns list of object with the referenced type
     * @param resource resource with data
     * @param typeReference type of data
     * @return list of data of referenced type
     */
    @SneakyThrows
    @Override
    public List<T> apply(Resource resource, Class<T> typeReference) {
        Class<T[]> array = (Class<T[]>) Class.forName("[L" + typeReference.getName() + ";");
        T[] objects = objectMapper.readValue(resource.getFile(), array);
        return Arrays.asList(objects);
    }
}
