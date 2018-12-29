package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.search.coordinate.validator.CoordinateDefined;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@CoordinateDefined(message = "Latitude and Longitude must both be defined")
public class CoordinateSearchParams implements Serializable {

    @QueryParam("lat")
    @DecimalMin(value = "40", message = "Latitude must not have a value smaller than 40")
    @DecimalMax(value = "87", message = "Latitude must not have a value larger than 87")
    private Float latitude;

    @QueryParam("lng")
    @DecimalMin(value = "-144", message = "Longitude must not have a value smaller than -144")
    @DecimalMax(value = "-48", message = "Longitude must not have a value larger than -48")
    private Float longitude;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format(
            "CoordinateSearchParams(latitude=<%.2f>, longitude=<%.2f>)",
            latitude,
            longitude
        );
    }
}
