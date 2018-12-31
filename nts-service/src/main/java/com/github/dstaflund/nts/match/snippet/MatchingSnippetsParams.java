package com.github.dstaflund.nts.match.snippet;

import org.hibernate.validator.constraints.Length;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class MatchingSnippetsParams implements Serializable {

    @QueryParam("snippet")
    @Length(min = 1, max = 40, message = "Snippet must be between 1 and 40 characters")
    private String snippet;

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return String.format("MatchingSnippetParams(snippet=<%s>", snippet);
    }
}
