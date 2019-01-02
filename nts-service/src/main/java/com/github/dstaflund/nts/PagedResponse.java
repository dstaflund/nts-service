package com.github.dstaflund.nts;

import java.io.Serializable;

public class PagedResponse<T> implements Serializable {
    private Integer limit;
    private Integer offset;
    private String sort;
    private Integer totalCount;
    private T data;

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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format(
            "PagedResponse(limit=<%d>, offset=<%d>, sort=<%s>, totalCount=<%d>, data=<%s>)",
            limit,
            offset,
            sort,
            totalCount,
            data
        );
    }

    public static <T> PagedResponse<T> newInstance(PagingParams paging, Integer count, T data){
        PagedResponse<T> res = new PagedResponse<>();
        paging.setLimit(paging.getLimit());
        paging.setOffset(paging.getOffset());
        paging.setSort(paging.getSort());
        res.setTotalCount(count);
        res.setData(data);
        return res;
    }
}
