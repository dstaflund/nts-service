package com.github.dstaflund.nts;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class PagingParams implements Serializable {

    @Min(value = 1, message = "Limit must be greater than 0")
    @Max(value = 256, message = "Limit can be no more than 256")
    @DefaultValue("256")
    @QueryParam("limit")
    private Integer limit;

    @Min(value = 0, message = "Offset cannot be negative")
    @DefaultValue("0")
    @QueryParam("offset")
    private Integer offset;

    @DefaultValue("name")
    @Pattern(
        regexp = "^(name|snippet|parent|north|south|east|west)$",
        message = "SortField must be 'name', 'snippet', 'parent', 'north', 'south', 'east', or 'west'",
        flags = Pattern.Flag.CASE_INSENSITIVE
    )
    @QueryParam("sortField")
    private String sortField;

    @DefaultValue("1")
    @QueryParam("sortOrder")
    @Min(value = -1, message = "SortOrder must be -1 (desc) or 1 (asc)")
    @Max(value = 1, message = "SortOrder must be -1 (desc) or 1 (asc)")
    private Integer sortOrder;

    Integer getLimit() {
        return limit;
    }

    void setLimit(Integer limit) {
        this.limit = limit;
    }

    Integer getOffset() {
        return offset;
    }

    void setOffset(Integer offset) {
        this.offset = offset;
    }

    String getSortField() {
        return sortField;
    }

    void setSortField(String sortField) {
        this.sortField = sortField;
    }

    Integer getSortOrder() {
        return sortOrder;
    }

    void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return String.format(
            "PagingParams(limit=<%d>, offset=<%d>, sortField=<%s>, sortOrder=<%d>)",
            limit,
            offset,
            sortField,
            sortOrder
        );
    }
}
