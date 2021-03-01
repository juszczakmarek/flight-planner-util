package pl.mjuapps.flightplannerutil.web.translator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ExceptionTranslatorTest {

    public static final String DUMMY_ROOT = "/exceptionTranslatorTest";
    public static final String ILLEGAL_ARG_PATH = "/illegalArgumentException";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void should_TranslateIllegalArgumentException_To_BadRequest() throws Exception {
        String uri = DUMMY_ROOT + ILLEGAL_ARG_PATH;
        mockMvc.perform(get(uri))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("testing " + ILLEGAL_ARG_PATH));
    }



}