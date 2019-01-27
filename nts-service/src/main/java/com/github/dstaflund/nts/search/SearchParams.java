package com.github.dstaflund.nts.search;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class SearchParams implements Serializable {

    @QueryParam("name")
    @Pattern(
        regexp = "^\\s*([0-9]{0,3})?[a-pA-P]?([01]?[0-9])?\\s*$",
        message = "Name must have the format of known NTS Series, NTS Area, or NTS Sheet names (ex:  75P14)"
    )
    private String name;

    @QueryParam("title")
    @Pattern(
        regexp = "^\\s*.{0,40}\\s*$",
        message = "Title cannot be longer than 40 characters"
    )
    private String title;

    @QueryParam("north")
    @Pattern(
        regexp = "^\\s*([0-9]{0,2})?\\.?([0-9]{0,2})?\\s*$",
        message = "North cannot be more than 5 characters long and must "
    )
    private String north;

    @QueryParam("south")
    @Pattern(
        regexp = "^\\s*([0-9]{0,2})?\\.?([0-9]{0,2})?\\s*$",
        message = "South cannot be more than 5 characters long and must "
    )
    private String south;

    @QueryParam("east")
    @Pattern(
        regexp = "^\\s*-?([0-9]{0,2})?\\.?([0-9]{0,2})?\\s*$",
        message = "East cannot be more than 6 characters long and must "
    )
    private String east;

    @QueryParam("west")
    @Pattern(
        regexp = "^\\s*-?([0-9]{0,2})?\\.?([0-9]{0,2})?\\s*$",
        message = "West cannot be more than 6 characters long and must "
    )
    private String west;

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

    String getNorth() {
        return north;
    }

    void setNorth(String north) {
        this.north = north;
    }

    String getSouth() {
        return south;
    }

    void setSouth(String south) {
        this.south = south;
    }

    String getEast() {
        return east;
    }

    void setEast(String east) {
        this.east = east;
    }

    String getWest() {
        return west;
    }

    void setWest(String west) {
        this.west = west;
    }

    @Override
    public String toString() {
        return String.format(
            "SearchParams(name=<%s>, title=<%s>, north=<%s>, south=<%s>, east=<%s>, west=<%s>)",
            name,
            title,
            north,
            south,
            east,
            west
        );
    }
}
