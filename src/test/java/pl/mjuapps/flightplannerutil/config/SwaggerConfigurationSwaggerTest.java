package pl.mjuapps.flightplannerutil.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SwaggerConfigurationSwaggerTest {

    protected static final String UI_URL = "/swagger-ui.html";
    protected static final String DOCS_URL = "/v2/api-docs";

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    protected void should_Verify_SwaggerUi() throws Exception {
        mockMvc.perform(get(UI_URL))
                .andExpect(status().isOk());
    }

    @Test
    protected void should_Verify_SwaggerDocs() throws Exception {
        mockMvc.perform(get(DOCS_URL))
                .andExpect(status().isOk());
    }

}