package com.github.dstaflund.nts.model;

import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class SearchRequest implements Serializable {

    @QueryParam("name")
    @Length(min = 2, max = 6, message = "Name must be between 2 to 6 characters in length")
    @Pattern(regexp = "^\\s*[0-9]{1,3}[a-pA-P]([01]?[0-9])?\\s*$", message = "Name must have the format of known NTS Series, NTS Area, or NTS Sheet names (ex:  75P14)")
    private String name;

    @QueryParam("snippet")
    @Length(max = 40, message = "Snippet cannot be longer than 40 characters")
    private String snippet;

    @QueryParam("north")
    @DecimalMin(value = "40.25", message = "North must not have a value smaller than 40.25")
    @DecimalMax(value = "87", message = "North must not have a value larger than 87")
    private Float north;

    @QueryParam("south")
    @DecimalMin(value = "40", message = "South must not have a value smaller than 40")
    @DecimalMax(value = "86.25", message = "South must not have a value larger than 86.25")
    private Float south;

    @QueryParam("east")
    @DecimalMin(value = "-141", message = "East must not have a value smaller than -141")
    @DecimalMax(value = "-48", message = "East must not have a value larger than -48")
    private Float east;

    @QueryParam("west")
    @DecimalMin(value = "-144", message = "West must not have a value smaller than -144")
    @DecimalMax(value = "-48.5", message = "West must not have a value larger than -48.5")
    private Float west;

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

    public Float getNorth() {
        return north;
    }

    public void setNorth(Float north) {
        this.north = north;
    }

    public Float getSouth() {
        return south;
    }

    public void setSouth(Float south) {
        this.south = south;
    }

    public Float getEast() {
        return east;
    }

    public void setEast(Float east) {
        this.east = east;
    }

    public Float getWest() {
        return west;
    }

    public void setWest(Float west) {
        this.west = west;
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

    public void setSort(String sortBy) {
        this.sort = sortBy;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filterBy) {
        this.filter = filterBy;
    }

    @Override
    public String toString() {
        return String.format(
                "SearchRequest(name=<%s>, snippet=<%s>, north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>, parent=<%s>, limit=<%d>, offset=<%d>, sort=<%s>, filter=<%s>)",
                name,
                snippet,
                north,
                south,
                east,
                west,
                parent,
                limit,
                offset,
                sort,
                filter
        );
    }
}
