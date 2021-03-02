package pl.mjuapps.flightplannerutil.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportFlightsDto {

    private Integer departingFlights;
    private Integer departingBaggage;
    private Integer arrivingFlights;
    private Integer arrivingBaggage;

}
