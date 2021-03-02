package pl.mjuapps.flightplannerutil.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.mjuapps.flightplannerutil.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false")
public class PostConstructDataInitializationProcessDisabledTest {

    @Autowired
    private DataProvider dataProvider;

    @Test
    void testData_Should_BeEmpty_When_ResourceAutoLoadDisabled() {
        assertThat(dataProvider.flights()).isEmpty();
        assertThat(dataProvider.cargos()).isEmpty();
    }

}
