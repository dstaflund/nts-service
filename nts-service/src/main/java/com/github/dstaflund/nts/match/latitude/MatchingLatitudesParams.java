package com.github.dstaflund.nts.match.latitude;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingLatitudesParams implements Serializable {

    @QueryParam("latitude")
    @Length(min = 1, max = 7, message = "Latitude must be between 1 to 7 characters in length")
    @Pattern(regexp = "^\\s*[+]?(\\d{1,3})?\\.?(\\d{0,2})?\\s*$", message = "Latitude mut be able to match on values between 40.00 and 87.99")
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return String.format("MatchingLatitudeParams(latitude=<%s>", latitude);
    }
}
