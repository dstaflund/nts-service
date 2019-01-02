package com.github.dstaflund.nts;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class PagingParams implements Serializable {

    @NotNull(message = "Limit must be specified")
    @Min(value = 1, message = "Limit must be greater than 0")
    @Max(value = 256, message = "Limit can be no more than 256")
    @DefaultValue("256")
    @QueryParam("limit")
    private Integer limit;

    @NotNull(message = "Offset must be specified")
    @Min(value = 0, message = "Offset cannot be negative")
    @DefaultValue("0")
    @QueryParam("offset")
    private Integer offset;

    @DefaultValue("+name")
    @QueryParam("sort")
    private String sort;

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

    @Override
    public String toString() {
        return String.format(
            "PagingParams(limit=<%d>, offset=<%d>, sort=<%s>, data=<%s>)",
            limit,
            offset,
            sort
        );
    }
}
