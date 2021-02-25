package pl.mjuapps.flightplannerutil.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.mjuapps.flightplannerutil.service.dummy.DummyResource;

import static pl.mjuapps.flightplannerutil.service.ResourceFunctionTest.DUMMY_RESOURCE_PATH;
import static pl.mjuapps.flightplannerutil.service.ResourceFunctionTest.assertDummyResources;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.resource.auto-load-enabled=false") //no resource autoload
class FileReaderServiceTest {

    @Autowired
    private FileReaderService<DummyResource> fileReaderService;

    @Test
    void apply_ShouldReadFile_ForGivenPathAndRefType_And_ReturnList_Of_DummyResources() {
        assertDummyResources(fileReaderService.apply(DUMMY_RESOURCE_PATH, DummyResource.class));
    }

}