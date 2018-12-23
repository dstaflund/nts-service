package com.github.dstaflund.nts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="nts_maps", schema="public")
public class NtsMap implements Serializable {

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
