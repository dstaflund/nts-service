package com.github.dstaflund.nts.search;

import com.github.dstaflund.nts.PagingParams;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class SearchQueryBuilder {
    private static final Pattern sFullNamePattern = Pattern.compile("^\\s*([0-9]{1,3})?([A-P])?([01]?[0-9])?\\s*$");

    private boolean count;
    private PagingParams pagingParams;
    private SearchParams searchParams;

    private SearchQueryBuilder(){
    }

    SearchQueryBuilder count(boolean countInd){
        count = countInd;
        return this;
    }

    SearchQueryBuilder pagingParams(PagingParams params){
        pagingParams = params;
        return this;
    }

    SearchQueryBuilder searchParams(SearchParams params){
        searchParams = params;
        return this;
    }

    String build(){
        Objects.requireNonNull(searchParams);
        if (!count) Objects.requireNonNull(pagingParams);
        return new StringBuilder()
            .append(selectClause())
            .append(fromClause())
            .append(whereClause())
            .append(orderByClause())
            .toString();
    }

    private String selectClause(){
        return count ? " SELECT COUNT(m)" : "";
    }

    private String fromClause(){
        return " FROM NtsMap m";
    }

    private String whereClause(){
        return new StringBuilder()
            .append(" WHERE 1 = 1")
            .append(nameClause())
            .append(snippetClause())
            .append(northClause())
            .append(southClause())
            .append(eastClause())
            .append(westClause())
            .toString();
    }

    private String nameClause() {
        return searchParams.getName() == null
            ? ""
            : " AND m.searchName LIKE '" + scrubMapName(searchParams.getName()) + "%'";
    }

    private String snippetClause() {
        return searchParams.getSnippet() == null
            ? ""
            : " AND m.snippet LIKE '" + scrubSnippet(searchParams.getSnippet()) + "%'";
    }

    private String northClause() {
        return searchParams.getNorth() == null
            ? ""
            : " AND m.searchNorth LIKE '" + searchParams.getNorth() + "%'";
    }

    private String southClause() {
        return searchParams.getSouth() == null
            ? ""
            : " AND m.searchSouth LIKE '" + searchParams.getSouth() + "%'";
    }

    private String eastClause() {
        return searchParams.getEast() == null
            ? ""
            : " AND m.searchEast LIKE '" + searchParams.getEast() + "%'";
    }

    private String westClause() {
        return searchParams.getWest() == null
            ? ""
            : " AND m.searchWest LIKE '" + searchParams.getWest() + "%'";
    }

    private String orderByClause(){
        if (count) return "";
        return " ORDER BY "
             + (pagingParams.getSortField() == null ? " m.name" : "m." + pagingParams.getSortField())
             + " "
             + (pagingParams.getSortOrder() == null || pagingParams.getSortOrder() == 1 ? "ASC" : "DESC");
    }

    private static String scrubMapName(String value){
        if (value == null || value.trim().isEmpty()) return null;
        Matcher m = sFullNamePattern.matcher(value.trim().toUpperCase());
        if (! m.matches() || m.group(1) == null) return "X";
        return Integer.parseInt(m.group(1))
            + (m.group(2) == null ? "" : m.group(2))
            + (m.group(3) == null ? "" : (m.group(3).length() == 1 ? "0" + m.group(3) : m.group(3)));
    }

    private static String scrubSnippet(String value){
        if (value == null || value.trim().isEmpty()) return null;
        return value.trim().toUpperCase();
    }

    static SearchQueryBuilder newInstance(){
        return new SearchQueryBuilder();
    }
}
