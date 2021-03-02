package pl.mjuapps.flightplannerutil.web.service;

import pl.mjuapps.flightplannerutil.web.model.FlightWeightDto;
import pl.mjuapps.flightplannerutil.web.rest.FlightController;

/**
 * Service for {@link FlightController} and {@link FlightWeightDto}
 */
public interface FlightWeightApiService {

    /**
     * Produce {@link FlightWeightDto} for given criteria
     * @param identifier flight identifier (so called flight numnber)
     * @param dateString flight date represented by {@link String} with pattern {@link }
     * @param unitSymbol unit in which {@link FlightWeightDto} should be created
     * @return created {@link FlightWeightDto}
     */
    FlightWeightDto weightDto(Integer identifier, String dateString, String unitSymbol);
}
