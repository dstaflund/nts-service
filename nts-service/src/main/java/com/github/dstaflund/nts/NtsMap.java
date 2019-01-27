package com.github.dstaflund.nts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
        name = "NtsMap.Query.MatchingNames",
        query = "SELECT m.name FROM NtsMap m WHERE m.searchName LIKE :query ORDER BY m.name ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.MatchingTitles",
        query = "SELECT m.title FROM NtsMap m WHERE m.title LIKE :query ORDER BY m.title ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Count.NtsMaps",
        query = "SELECT COUNT(m) FROM NtsMap m"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.Name.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.name ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.Name.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.name DESC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.Title.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.title ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.Title.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.title DESC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.North.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.north ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.North.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.north DESC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.South.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.south ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.South.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.south DESC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.East.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.east ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.East.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.east DESC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.West.Asc",
        query = "SELECT m FROM NtsMap m ORDER BY m.west ASC"
    ),
    @NamedQuery(
        name = "NtsMap.Query.NtsMaps.West.Desc",
        query = "SELECT m FROM NtsMap m ORDER BY m.west DESC"
    )
})

// These are native queries since HQL doesn't support UNION yet
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "NtsMap.NativeQuery.MatchingLatitudes",
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
        name = "NtsMap.NativeQuery.MatchingLongitudes",
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

    @Id
    @Column(name = "name", unique = true, nullable = false, insertable = false, updatable = false, length = 6)
    private String name;

    @JsonProperty("title")
    @Column(name = "snippet", insertable = false, updatable = false, length = 40)
    private String title;

    @Column(name = "north", nullable = false, insertable = false, updatable = false, precision = 5, scale = 2)
    private float north;

    @Column(name = "south", nullable = false, insertable = false, updatable = false, precision = 5, scale = 2)
    private float south;

    @Column(name = "east", nullable = false, insertable = false, updatable = false, precision = 5, scale = 2)
    private float east;

    @Column(name = "west", nullable = false, insertable = false, updatable = false, precision = 5, scale = 2)
    private float west;

    @JsonIgnore
    @Column(name = "search_name", nullable = false, unique = true, insertable = false, updatable = false, length = 6)
    private String searchName;

    @JsonIgnore
    @Column(name = "search_north", nullable = false, insertable = false, updatable = false, length = 7)
    private String searchNorth;

    @JsonIgnore
    @Column(name = "search_south", nullable = false, insertable = false, updatable = false, length = 7)
    private String searchSouth;

    @JsonIgnore
    @Column(name = "search_east", nullable = false, insertable = false, updatable = false, length = 7)
    private String searchEast;

    @JsonIgnore
    @Column(name = "search_west", nullable = false, insertable = false, updatable = false, length = 7)
    private String searchWest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
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
            "NtsMap(name=<%s>, title=<%s>, north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>)",
            name,
            title,
            north,
            south,
            east,
            west
        );
    }
}
