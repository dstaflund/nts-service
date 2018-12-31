package com.github.dstaflund.nts.match.parent;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingParentsParams implements Serializable {

    @QueryParam("parent")
    @Length(min = 1, max = 4, message = "Parent must be between 1 to 4 characters in length")
    @Pattern(regexp = "^\\s*([0-9]{1,3})?[a-pA-P]?\\s*$", message = "Parent must be able to match on known NTS Series or NTS Area names (ex:  75P)")
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("MatchingParentsParams(parent=<%s>", parent);
    }
}
