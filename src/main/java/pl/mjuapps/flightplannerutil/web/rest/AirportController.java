package pl.mjuapps.flightplannerutil.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mjuapps.flightplannerutil.web.model.AirportFlightsDto;
import pl.mjuapps.flightplannerutil.web.service.FlightsForAirportApiService;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final FlightsForAirportApiService flightsForAirportApiService;

    @GetMapping("/{airportCode}/flights")
    public ResponseEntity<AirportFlightsDto> getFlightWeightForNumberAndUtcDate(@PathVariable String airportCode,
            @RequestParam String date) {
        return ResponseEntity.ok(flightsForAirportApiService.apply(airportCode, date));
    }

}
