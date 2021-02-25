package pl.mjuapps.flightplannerutil.domain;

import lombok.Data;

/**
 * Representation of Airport. Normally, this class should containt much more fields, but for the sake of this task it is
 * enough just to have {@link String} as id
 */
@Data
public class Airport {

    private String id;

}
