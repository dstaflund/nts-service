package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.search.name.validator.NameSnippetParentDefined;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@NameSnippetParentDefined(message = "Name, Snippet, or Parent must be defined")
public class NameSearchParams implements Serializable {

    @QueryParam("name")
    @Pattern(
        regexp = "^\\s*([0-9]{0,3})?[a-pA-P]?([01]?[0-9])?\\s*$",
        message = "Name must have the format of known NTS Series, NTS Area, or NTS Sheet names (ex:  75P14)"
    )
    private String name;

    @QueryParam("snippet")
    @Pattern(
        regexp = "^(.*){0,40}.*?\\s*$",
        message = "Snippet cannot be longer than 40 characters"
    )
    private String snippet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return String.format(
            "NameSearchParams(name=<%s>, snippet=<%s>)",
            name,
            snippet
        );
    }
}
