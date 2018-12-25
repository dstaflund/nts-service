package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.search.area.validator.AreaDefined;
import com.github.dstaflund.nts.search.area.validator.EastGreaterThanWest;
import com.github.dstaflund.nts.search.area.validator.NorthGreaterThanSouth;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@AreaDefined(message = "North, South, East, and West must all be defined")
@EastGreaterThanWest(message = "East must be greater than West")
@NorthGreaterThanSouth(message = "North must be greater than South")
public class AreaSearchParams implements Serializable {

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

    @QueryParam("n")
    @DecimalMin(value = "40", message = "North must not have a value smaller than 40")
    @DecimalMax(value = "87", message = "North must not have a value larger than 87")
    private Float north;

    @QueryParam("s")
    @DecimalMin(value = "40", message = "South must not have a value smaller than 40")
    @DecimalMax(value = "87", message = "South must not have a value larger than 87")
    private Float south;

    @QueryParam("e")
    @DecimalMin(value = "-144", message = "East must not have a value smaller than -144")
    @DecimalMax(value = "-48", message = "East must not have a value larger than -48")
    private Float east;

    @QueryParam("w")
    @DecimalMin(value = "-144", message = "West must not have a value smaller than -144")
    @DecimalMax(value = "-48", message = "West must not have a value larger than -48")
    private Float west;

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
            "AreaSearchParams(name=<%s>, snippet=<%s>, parent=<%s>, north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>, limit=<%d>, offset=<%d>, sort=<%s>, filter=<%s>)",
            name,
            snippet,
            parent,
            north,
            south,
            east,
            west,
            limit,
            offset,
            sort,
            filter
        );
    }
}
