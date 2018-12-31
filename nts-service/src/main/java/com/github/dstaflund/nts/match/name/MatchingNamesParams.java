package com.github.dstaflund.nts.match.name;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingNamesParams implements Serializable {

    @QueryParam("name")
    @Length(min = 1, max = 6, message = "Name must be between 1 to 6 characters in length")
    @Pattern(regexp = "^\\s*([0-9]{1,3})?[a-pA-P]?([01]?[0-9])?\\s*$", message = "Name must be able to match with known NTS Series, NTS Area, or NTS Sheet names (ex:  75P14)")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("MatchingNamesParams(name=<%s>", name);
    }
}
