package pl.mjuapps.flightplannerutil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
class FlightPlannerUtilApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
