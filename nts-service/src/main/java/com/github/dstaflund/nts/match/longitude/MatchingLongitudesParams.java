package com.github.dstaflund.nts.match.longitude;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingLongitudesParams implements Serializable {

    @QueryParam("longitude")
    @Length(min = 1, max = 7, message = "Longitude must be between 1 to 7 characters in length")
    @Pattern(regexp = "^\\s*[-](\\d{1,3})?\\.?(\\d{0,2})?\\s*$", message = "Latitude mut be able to match on values between -144.00 and -48.00")
    private String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("MatchingLongitudeParams(longitude=<%s>", longitude);
    }

}
