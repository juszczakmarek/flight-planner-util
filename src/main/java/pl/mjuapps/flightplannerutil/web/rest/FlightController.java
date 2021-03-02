package pl.mjuapps.flightplannerutil.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.utils.MassUnitFunctions;
import pl.mjuapps.flightplannerutil.web.model.FlightWeightDto;
import pl.mjuapps.flightplannerutil.web.service.impl.FlightWeightApiServiceImpl;

/**
 * Controller for fetching resources dependent on {@link Flight}
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightWeightApiServiceImpl flightApiService;

    /**
     * Respond with {@link FlightWeightDto} representing calculated weight for given flight number and given UTC date.
     *
     * @param flightNumber referenced flight number
     * @param date UTC date
     * @param unit unit in which calculation will be performed. Accepted values {@link MassUnitFunctions#ALLOWED_UNITS}
     *
     * @return calculated weights in given unit
     */
    @GetMapping("/{flightNumber}/weight")
    public ResponseEntity<FlightWeightDto> getFlightWeightForNumberAndUtcDate(@PathVariable Integer flightNumber,
               @RequestParam String date, @RequestHeader("Accept-Measure-Unit") String unit) {
        return ResponseEntity.ok(flightApiService.weightDto(flightNumber, date, unit));
    }

}
