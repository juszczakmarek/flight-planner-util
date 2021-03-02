package pl.mjuapps.flightplannerutil.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(properties = "flight-planner.swagger.enabled=false")
class SwaggerConfigurationSwaggerDisabledTest extends SwaggerConfigurationSwaggerTest{

    @Test
    void call_Test_From_Super() throws Exception {
        should_Verify_SwaggerUi();
    }

    @Override
    public void should_Verify_SwaggerDocs() throws Exception {
        mockMvc.perform(get(DOCS_URL))
                .andExpect(status().isNotFound());
    }

}