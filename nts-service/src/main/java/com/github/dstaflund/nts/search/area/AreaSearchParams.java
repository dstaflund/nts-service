package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.search.area.validator.AreaDefined;
import com.github.dstaflund.nts.search.area.validator.EastGreaterThanWest;
import com.github.dstaflund.nts.search.area.validator.NorthGreaterThanSouth;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@AreaDefined(message = "North, South, East, and West must all be defined")
@EastGreaterThanWest(message = "East must be greater than West")
@NorthGreaterThanSouth(message = "North must be greater than South")
public class AreaSearchParams implements Serializable {

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

    @DefaultValue("256")
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
            "AreaSearchParams(north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>, limit=<%d>, offset=<%d>, sort=<%s>, filter=<%s>)",
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
