package pl.mjuapps.flightplannerutil.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Service for retrieving {@link List} of {@link T} from files
 * @param <T> referenced type (in our case it is one of: {@link Cargo}, {@link Flight}
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class FileReaderService<T> implements BiFunction<String, Class<T>, List<T>> {

    private final ResourceLoader resourceLoader;
    private final ResourceFunction<T> resourceFunction;

    @SneakyThrows
    @Override
    public List<T> apply(String resourcePath, Class<T> typeReference) {
        log.info("Reading resources with type {} and path {}", typeReference.getTypeName(), resourcePath);
        Resource resource = resourceLoader.getResource(resourcePath);
        return resourceFunction.apply(resource, typeReference);
    }
}
