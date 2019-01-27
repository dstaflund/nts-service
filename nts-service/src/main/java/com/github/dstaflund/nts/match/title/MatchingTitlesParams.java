package com.github.dstaflund.nts.match.title;

import org.hibernate.validator.constraints.Length;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingTitlesParams implements Serializable {

    @QueryParam("title")
    @Length(min = 1, max = 40, message = "Title must be between 1 and 40 characters")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("MatchingTitleParams(title=<%s>", title);
    }
}
