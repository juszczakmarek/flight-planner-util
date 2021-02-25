package pl.mjuapps.flightplannerutil.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.repository.DataProvider;
import pl.mjuapps.flightplannerutil.service.FileReaderService;
import pl.mjuapps.flightplannerutil.service.FlightCargoRelationConsumer;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This service is responsible for reading data from json files and setting bi-directional relations between
 * {@link Flight}s and {@link Cargo}s
 */
@Service
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(name = "flight-planner.resource.auto-load-enabled", havingValue = "true")
public class PostConstructDataInitializer {

    private final FileReaderService fileReaderService;
    private final FlightCargoRelationConsumer flightCargoRelationConsumer;
    private final FlightPlannerResourceProperties flightPlannerResourceProperties;
    private final DataProvider dataProvider;

    @PostConstruct
    public void data() {
        if (flightPlannerResourceProperties.autoLoadEnabled()) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Flight> flights = fileReaderService.apply(flightPlannerResourceProperties.getFlightResourcePath(), Flight.class);
        List<Cargo> cargos = fileReaderService.apply(flightPlannerResourceProperties.getCargoResourcePath(), Cargo.class);
        flights.forEach(flight -> flightCargoRelationConsumer.accept(flight, cargos));
        dataProvider.initializeTestData(flights, cargos);
    }
}
