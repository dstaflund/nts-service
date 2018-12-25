package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.search.name.validator.NameSnippetParentDefined;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@NameSnippetParentDefined(message = "Name, Snippet, or Parent must be defined")
public class NameSearchParams implements Serializable {

    @QueryParam("name")
    @Length(min = 2, max = 6, message = "Name must be between 2 to 6 characters in length")
    @Pattern(regexp = "^\\s*[0-9]{1,3}[a-pA-P]([01]?[0-9])?\\s*$", message = "Name must have the format of known NTS Series, NTS Area, or NTS Sheet names (ex:  75P14)")
    private String name;

    @QueryParam("snippet")
    @Length(max = 40, message = "Snippet cannot be longer than 40 characters")
    private String snippet;

    @QueryParam("parent")
    @Length(min = 2, max = 4, message = "Parent must be between 2 to 4 characters in length")
    @Pattern(regexp = "^\\s*[0-9]{1,3}[a-pA-P]\\s*$", message = "Parent must have the format of known NTS Series or NTS Area names (ex:  75P)")
    private String parent;

    @DefaultValue("100")
    @QueryParam("limit")
    private Integer limit;

    @DefaultValue("0")
    @QueryParam("offset")
    private Integer offset;

    @DefaultValue("+name")
    @QueryParam("sort")
    private String sort;

    @QueryParam("filter")
    private String filter;

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return String.format(
            "NameSearchParams(name=<%s>, snippet=<%s>, parent=<%s>, limit=<%d>, offset=<%d>, sort=<%s>, filter=<%s>)",
            name,
            snippet,
            parent,
            limit,
            offset,
            sort,
            filter
        );
    }
}
