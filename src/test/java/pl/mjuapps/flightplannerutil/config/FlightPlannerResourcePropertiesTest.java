package pl.mjuapps.flightplannerutil.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestPropertySource(properties = {"flight-planner.resource.auto-load-enabled=false",
                                  "flight-planner.resource.root=x",
                                  "flight-planner.resource.file-name-mapping.flight=f.json",
                                  "flight-planner.resource.file-name-mapping.cargo=c.json"})
class FlightPlannerResourcePropertiesTest {

    @Autowired
    private FlightPlannerResourceProperties flightPlannerResourceProperties;

    @Test
    void getFlightResourcePath_Should_ReturnProperties() {
        assertEquals("x:data/f.json", flightPlannerResourceProperties.getFlightResourcePath());
        assertEquals("x:data/c.json", flightPlannerResourceProperties.getCargoResourcePath());
        assertFalse(flightPlannerResourceProperties.autoLoadEnabled());
    }

}