package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty", false),
    FLOOR("floor", true),
    WALL("wall", false),
    GRASS("grass", true),
    CHEESE("cheese", true),
    CLOSED_DOOR("closedDoor", false),
    OPENED_DOOR("openedDoor", true);


    private final String tileName;
    private final boolean canStepOn;

    CellType(String tileName, boolean canStepOn) {
        this.tileName = tileName;
        this.canStepOn = canStepOn;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isPassable() {
        return canStepOn;
    }
}
