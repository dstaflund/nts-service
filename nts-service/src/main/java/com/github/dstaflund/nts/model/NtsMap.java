package com.github.dstaflund.nts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="nts_maps", schema="public")
@NamedQueries({
    @NamedQuery(
        name = "find_maps_by_coordinate",
        query = "  FROM NtsMap m"
            + " WHERE (:name IS NULL OR m.name = :name)"
            + "   AND (:snippet IS NULL OR m.snippet = :snippet)"
            + "   AND (:parent IS NULL OR m.parent = :parent)"
            + "   AND :latitude IS NOT NULL"
            + "   AND :longitude IS NOT NULL"
            + "   AND :latitude BETWEEN m.south AND m.north"
            + "   AND :longitude BETWEEN m.west AND m.east"
    ),
    @NamedQuery(
        name = "find_maps_by_area",
        query = "  FROM NtsMap m"
            + " WHERE (:name IS NULL OR m.name = :name)"
            + "   AND (:snippet IS NULL OR m.snippet = :snippet)"
            + "   AND (:parent IS NULL OR m.parent = :parent)"
            + "   AND :north IS NOT NULL"
            + "   AND :south IS NOT NULL"
            + "   AND :east IS NOT NULL"
            + "   AND :west IS NOT NULL"
            + "   AND m.south BETWEEN :south AND :north"
            + "   AND m.north BETWEEN :south AND :north"
            + "   AND m.west BETWEEN :west AND :east"
            + "   AND m.east BETWEEN :west AND :east"
    )
})
public class NtsMap implements Serializable {

    public static class AreaQueryContract {
        public static final String QUERY_NAME = "find_maps_by_area";

        public static final String PARAM_NAME = "name";
        public static final String PARAM_SNIPPET = "snippet";
        public static final String PARAM_PARENT = "parent";
        public static final String PARAM_NORTH = "north";
        public static final String PARAM_SOUTH = "south";
        public static final String PARAM_EAST = "east";
        public static final String PARAM_WEST = "west";
    }

    public static class CoordinateQueryContract {
        public static final String QUERY_NAME = "find_maps_by_coordinate";

        public static final String PARAM_NAME = "name";
        public static final String PARAM_SNIPPET = "snippet";
        public static final String PARAM_PARENT = "parent";
        public static final String PARAM_LATITUDE = "latitude";
        public static final String PARAM_LONGITUDE = "longitude";
    }

    @Id
    @Column(name = "name", unique = true, nullable = false, insertable = false, updatable = false, length = 6)
    private String name;

    @Column(name = "snippet", insertable = false, updatable = false, length = 40)
    private String snippet;

    @Column(name = "north", insertable = false, updatable = false, precision = 5, scale = 2)
    private float north;

    @Column(name = "south", insertable = false, updatable = false, precision = 5, scale = 2)
    private float south;

    @Column(name = "east", insertable = false, updatable = false, precision = 5, scale = 2)
    private float east;

    @Column(name = "west", insertable = false, updatable = false, precision = 5, scale = 2)
    private float west;

    @Column(name = "parent", insertable = false, updatable = false, length = 6)
    private String parent;

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
            "NtsMap(name=<%s>, snippet=<%s>, north=<%.2f>, south=<%.2f>, east=<%.2f>, west=<%.2f>, parent=<%s>)",
            name,
            snippet,
            north,
            south,
            east,
            west,
            parent
        );
    }
}
