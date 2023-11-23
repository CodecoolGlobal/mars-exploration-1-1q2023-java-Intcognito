package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    GRASS("grass"),
    CHEESE("cheese"),
    CLOSED_DOOR("closedDoor"),
    OPENED_DOOR("openedDoor");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
