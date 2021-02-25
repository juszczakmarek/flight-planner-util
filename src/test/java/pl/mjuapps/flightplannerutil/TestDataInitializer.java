package pl.mjuapps.flightplannerutil;

import pl.mjuapps.flightplannerutil.domain.Cargo;
import pl.mjuapps.flightplannerutil.domain.Flight;
import pl.mjuapps.flightplannerutil.domain.Load;
import pl.mjuapps.flightplannerutil.domain.Track;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataInitializer {

    public static final List<Unit<Mass>> DEFAULT_UNITS = Arrays.asList(Units.KILOGRAM, USCustomary.POUND, Units.KILOGRAM, USCustomary.POUND);
    public static final Integer DEFAULT_WEIGHT_VALUE = 10;
    public static final Quantity<Mass> DEFAULT_KILOGRAMS_WEIGHT = Quantities.getQuantity(DEFAULT_WEIGHT_VALUE, Units.KILOGRAM);
    public static final Quantity<Mass> DEFAULT_POUNDS_WEIGHT = Quantities.getQuantity(DEFAULT_WEIGHT_VALUE, USCustomary.POUND);
    public static final List<Load> TYPICAL_LOADS = typicalLoads();
    public static final String DUMMY = "dummy";
    public static final String DEFAULT_DEPARTURE_AIRPORT = "YYZ";
    public static final String DEFAULT_ARRIVAL_AIRPORT = "PPX";
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss ZZZZZ";
    public static final String DEFAULT_DEPARTURE_DATE_STRING = "2018-11-02T04:19:13 -01:00";
    public static final Instant DEFAULT_DEPARTURE_DATE_INSTANT = Instant.from(DateTimeFormatter.ofPattern(DATE_PATTERN).parse(DEFAULT_DEPARTURE_DATE_STRING));

    public static final Integer DEFAULT_ID = 2021;
    public static final Integer DEFAULT_IDENTIFIER = 20210402;

    public static List<Flight> typicalFlights() {
        return Arrays.asList(typicalFlight());
    }

    public static List<Cargo> typicalCargos(boolean minimumFlightDetails) {
        return Arrays.asList(typicalCargo(minimumFlightDetails, typicalFlight()));
    }

    public static Flight typicalFlight() {
        Flight flight = new Flight();
        flight.setTrack(typicalTrack());
        flight.setId(DEFAULT_ID);
        flight.setIdentifier(DEFAULT_IDENTIFIER);
        flight.setDepartureDate(DEFAULT_DEPARTURE_DATE_INSTANT);
        return flight;
    }

    public static Cargo typicalCargo(boolean minimumFlightDetails, Flight flight) {
        Cargo cargo = new Cargo();
        cargo.setCargo(new ArrayList<>(TYPICAL_LOADS));
        cargo.setBaggage(new ArrayList<>(TYPICAL_LOADS));

        Flight flightForCargo = flight;
        if (minimumFlightDetails) {
            flightForCargo = new Flight();
            flightForCargo.setId(flight.getId());
        }
        cargo.setFlight(flightForCargo);
        return cargo;
    }

    public static Track typicalTrack() {
        Track track = new Track();
        track.setArrivalAirport(DEFAULT_ARRIVAL_AIRPORT);
        track.setDepartureAirport(DEFAULT_DEPARTURE_AIRPORT);
        return track;
    }

    public static Load createLoad(int value) {
        Load load = new Load();
        load.setId(value);
        load.setPieces(value);
        load.setWeight(Quantities.getQuantity(value, DEFAULT_UNITS.get(value-1)));
        return load;
    }

    public static List<Load> typicalLoads() {
        List<Load> loads = new ArrayList<>();
        for (int i = 1; i <= DEFAULT_UNITS.size(); i++) {
            loads.add(createLoad(i));
        }
        return loads;
    }







}
