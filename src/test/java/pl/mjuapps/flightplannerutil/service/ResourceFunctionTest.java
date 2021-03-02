package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.TestPropertySource;
import pl.mjuapps.flightplannerutil.service.dummy.DummyResource;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
class ResourceFunctionTest {

    public static final String DUMMY_RESOURCE_PATH = "classpath:data/dummy/DummyResource.json";

    @Autowired
    private ResourceFunction<DummyResource> resourceFunction;

    @Autowired
    private ResourceLoader resourceLoader;

    private Resource resource;

    @BeforeEach
    void setUp() {
        resource =  resourceLoader.getResource(DUMMY_RESOURCE_PATH);
    }

    @Test
    void apply_Should_ReturnList_Of_DummyResources() {
        assertDummyResources(resourceFunction.apply(resource, DummyResource.class));
    }

    public static void assertDummyResources(List<DummyResource> dummyResources) {
        assertThat(dummyResources)
                .hasSize(1)
                .anySatisfy(dummyResource -> assertEquals(DUMMY, dummyResource.getField()));
    }
}