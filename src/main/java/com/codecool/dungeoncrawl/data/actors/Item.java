package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.Interactable;
import com.codecool.dungeoncrawl.data.ItemType;


import java.lang.reflect.Type;

public class Item implements Drawable, Interactable {
    private final Cell cell;
    private final ItemType type;
    private final String name;

    public Item(Cell cell, ItemType type, String name) {
        this.cell = cell;
        this.cell.setItem(this);
        this.type = type;
        this.name = name;
    }

    @Override
    public String getTileName() {
        return name;
    }

    @Override
    public void performAction() {
        this.cell.removeItem();
    }
    public String getName() {
        return this.name;
    }

    public ItemType getType() {
        return this.type;
    }
}
