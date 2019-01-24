package com.github.dstaflund.nts;

import com.github.dstaflund.nts.search.SearchParams;

import java.io.Serializable;

public class PagedResponse<T> implements Serializable {
    private PagingParams pagingParams;
    private SearchParams searchParams;
    private Long numberOfMatches;
    private T searchResults;

    public PagingParams getPagingParams() {
        return pagingParams;
    }

    public void setPagingParams(PagingParams pagingParams) {
        this.pagingParams = pagingParams;
    }

    public SearchParams getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(SearchParams searchParams) {
        this.searchParams = searchParams;
    }

    public Long getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(Long numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public T getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(T searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public String toString() {
        return String.format(
            "PagedResponse(pagingParams=<%s>, searchParams=<%s>, numberOfMatches=<%d>, searchResults=<%s>)",
            pagingParams,
            searchParams,
            numberOfMatches,
            searchResults
        );
    }

    public static <T> PagedResponse<T> newInstance(PagingParams paging, SearchParams searchParams, Long count, T data){
        PagedResponse<T> res = new PagedResponse<>();
        res.setPagingParams(paging);
        res.setSearchParams(searchParams);
        res.setNumberOfMatches(count);
        res.setSearchResults(data);
        return res;
    }
}
