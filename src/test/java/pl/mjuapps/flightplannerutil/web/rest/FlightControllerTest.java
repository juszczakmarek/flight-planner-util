package pl.mjuapps.flightplannerutil.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.mjuapps.flightplannerutil.web.model.FlightWeightDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_DEPARTURE_DATE_STRING;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DEFAULT_IDENTIFIER;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.DUMMY;
import static pl.mjuapps.flightplannerutil.TestDataInitializer.typicalFlightWeightDtoKg;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.ALLOWED_UNITS;
import static pl.mjuapps.flightplannerutil.utils.MassUnitFunctions.UNIT_NOT_ALLOWED_MSG;
import static pl.mjuapps.flightplannerutil.web.service.impl.FlightWeightApiServiceImpl.FLIGHTS_NOT_FOUND_MSG;

@SpringBootTest
class FlightControllerTest {

    private static final String URL = "/api/flights/{flightNumber}/weight?date={date}";

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
    void should_Return_FlightWeightDto() throws Exception {
        FlightWeightDto typicalFlightWeightDtoKg = typicalFlightWeightDtoKg();
        MockHttpServletRequestBuilder requestBuilder = get(URL, DEFAULT_IDENTIFIER, DEFAULT_DEPARTURE_DATE_STRING)
                                                       .header("Accept-Measure-Unit", "kg");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cargoTotalWeight"    ).value(typicalFlightWeightDtoKg.getCargoTotalWeight()))
                .andExpect(jsonPath("$.baggageTotalWeight"  ).value(typicalFlightWeightDtoKg.getBaggageTotalWeight()))
                .andExpect(jsonPath("$.totalWeight"         ).value(typicalFlightWeightDtoKg.getTotalWeight()))
                .andExpect(jsonPath("$.unit"                ).value(typicalFlightWeightDtoKg.getUnit()));
    }

    @Test
    void should_Return_BadRequest_When_HeaderAcceptMeasureUnit_IsMissing() throws Exception {
        mockMvc.perform(get(URL, DEFAULT_IDENTIFIER, DEFAULT_DEPARTURE_DATE_STRING))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Missing request header 'Accept-Measure-Unit' for method parameter of type String"));
    }

    @Test
    void should_Return_BadRequest_When_UnitNotAllowed() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(URL, DEFAULT_IDENTIFIER, DEFAULT_DEPARTURE_DATE_STRING)
                .header("Accept-Measure-Unit", DUMMY);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(String.format(UNIT_NOT_ALLOWED_MSG, DUMMY, ALLOWED_UNITS)));
    }

    @Test
    void should_Return_NotFound_When_NoFlightFor_Identifier() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(URL, -1, DEFAULT_DEPARTURE_DATE_STRING)
                .header("Accept-Measure-Unit", "kg");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(status().reason(String.format(FLIGHTS_NOT_FOUND_MSG, -1, DEFAULT_DEPARTURE_DATE_STRING)));
    }

    @Test
    void should_Return_NotFound_When_NoFlightFor_Date() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(URL, DEFAULT_IDENTIFIER, "2010-01-01")
                .header("Accept-Measure-Unit", "kg");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(status().reason(String.format(FLIGHTS_NOT_FOUND_MSG, DEFAULT_IDENTIFIER, "2010-01-01")));
    }

}