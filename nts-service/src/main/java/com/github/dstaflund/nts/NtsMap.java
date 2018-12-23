package com.github.dstaflund.nts;

import java.io.Serializable;
import java.util.Objects;

public class NtsMap implements Serializable {
    private String name;
    private String snippet;
    private float north;
    private float south;
    private float east;
    private float west;
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
