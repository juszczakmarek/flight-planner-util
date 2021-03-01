package pl.mjuapps.flightplannerutil.web.model;

import lombok.Builder;
import lombok.Data;
import pl.mjuapps.flightplannerutil.domain.Flight;

/**
 * Dto for representing calculated weights for {@link Flight}
 */
@Data
@Builder
public class FlightWeightDto {

    /**
     * Total weight of cargo
     */
    private Double cargoTotalWeight = 0D;

    /**
     * Total weight of baggage
     */
    private Double baggageTotalWeight = 0D;

    /**
     * Sum of {@link FlightWeightDto#getCargoTotalWeight()} and {@link FlightWeightDto#getBaggageTotalWeight()}
     */
    private Double totalWeight = 0D;

    /**
     * Unit in which weight calculation was performed
     */
    private String unit;

}
