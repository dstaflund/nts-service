package com.github.dstaflund.nts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="nts_maps", schema="public")
@NamedQueries({
    @NamedQuery(
        name = "NtsMap.MatchingNames",
        query = "   SELECT DISTINCT m.name"
            + "     FROM NtsMap m"
            + "    WHERE m.searchName LIKE :query"
            + " ORDER BY m.name ASC"
    ),
    @NamedQuery(
        name = "NtsMap.MatchingSnippets",
        query = "   SELECT DISTINCT m.snippet"
            + "     FROM NtsMap m"
            + "    WHERE m.snippet LIKE :query"
            + " ORDER BY m.snippet ASC"
    )
})

// These are native queries since HQL doesn't support UNION yet
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "NtsMap.MatchingLatitudes",
        query = " SELECT DISTINCT x.latitude"
            + "     FROM ("
            + "             SELECT north AS latitude"
            + "               FROM nts_maps"
            + "              WHERE search_north LIKE ?"
            + "              UNION"
            + "             SELECT south AS latitude"
            + "               FROM nts_maps"
            + "              WHERE search_south LIKE ?"
            + "          ) x"
            + " ORDER BY x.latitude ASC"
    ),
    @NamedNativeQuery(
        name = "NtsMap.MatchingLongitudes",
        query = " SELECT DISTINCT x.longitude"
            + "     FROM ("
            + "             SELECT east AS longitude"
            + "               FROM nts_maps"
            + "              WHERE search_east LIKE ?"
            + "              UNION"
            + "             SELECT west AS longitude"
            + "               FROM nts_maps"
            + "              WHERE search_west LIKE ?"
            + "          ) x"
            + " ORDER BY x.longitude ASC"
    )
})
public class NtsMap implements Serializable {

    public static class MatchingNamesContract {
        public static final String QUERY_NAME = "NtsMap.MatchingNames";
        public static final String PARAM_QUERY = "query";
    }

    public static class MatchingSnippetsContract {
        public static final String QUERY_NAME = "NtsMap.MatchingSnippets";
        public static final String PARAM_QUERY = "query";
    }

    public static class MatchingLatitudesContract {
        public static final String QUERY_NAME = "NtsMap.MatchingLatitudes";
    }

    public static class MatchingLongitudesContract {
        public static final String QUERY_NAME = "NtsMap.MatchingLongitudes";
    }

    @Id
    @Column(name = "name", unique = true, nullable = false, insertable = false, updatable = false, length = 6)
    private String name;

    @Column(name = "snippet", insertable = false, updatable = false, length = 40)
    private String snippet;

    @Column(name = "parent", insertable = false, updatable = false, length = 6)
    private String parent;

    @Column(name = "north", insertable = false, updatable = false, precision = 5, scale = 2)
    private float north;

    @Column(name = "south", insertable = false, updatable = false, precision = 5, scale = 2)
    private float south;

    @Column(name = "east", insertable = false, updatable = false, precision = 5, scale = 2)
    private float east;

    @Column(name = "west", insertable = false, updatable = false, precision = 5, scale = 2)
    private float west;

    @Column(name = "search_name", unique = true, insertable = false, updatable = false, length = 6)
    private String searchName;

    @Column(name = "search_parent", insertable = false, updatable = false, length = 4)
    private String searchParent;

    @Column(name = "search_north", insertable = false, updatable = false, length = 7)
    private String searchNorth;

    @Column(name = "search_south", insertable = false, updatable = false, length = 7)
    private String searchSouth;

    @Column(name = "search_east", insertable = false, updatable = false, length = 7)
    private String searchEast;

    @Column(name = "search_west", insertable = false, updatable = false, length = 7)
    private String searchWest;

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

    public float getNorth() {
        return north;
    }

    public void setNorth(float north) {
        this.north = north;
    }

    public float getSouth() {
        return south;
    }

    public void setSouth(float south) {
        this.south = south;
    }

    public float getEast() {
        return east;
    }

    public void setEast(float east) {
        this.east = east;
    }

    public float getWest() {
        return west;
    }

    public void setWest(float west) {
        this.west = west;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchParent() {
        return searchParent;
    }

    public void setSearchParent(String searchParent) {
        this.searchParent = searchParent;
    }

    public String getSearchNorth() {
        return searchNorth;
    }

    public void setSearchNorth(String searchNorth) {
        this.searchNorth = searchNorth;
    }

    public String getSearchSouth() {
        return searchSouth;
    }

    public void setSearchSouth(String searchSouth) {
        this.searchSouth = searchSouth;
    }

    public String getSearchEast() {
        return searchEast;
    }

    public void setSearchEast(String searchEast) {
        this.searchEast = searchEast;
    }

    public String getSearchWest() {
        return searchWest;
    }

    public void setSearchWest(String searchWest) {
        this.searchWest = searchWest;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (! (obj instanceof NtsMap)) return false;
        NtsMap that = (NtsMap) obj;
        if (this.name == null || that.name == null) return false;
        return this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return String.format(
            "NtsMap(name=<%s>, snippet=<%s>, parent=<%s>, north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>)",
            name,
            snippet,
            parent,
            north,
            south,
            east,
            west
        );
    }
}
