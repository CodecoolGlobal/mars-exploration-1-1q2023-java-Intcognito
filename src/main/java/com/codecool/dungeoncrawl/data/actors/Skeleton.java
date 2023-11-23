package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import com.codecool.dungeoncrawl.data.Interactable;

import java.util.Optional;

public class Skeleton extends Actor implements Interactable {

    public Skeleton(Cell cell) {
        super(cell, 2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


    @Override
    public void performAction() {
        fightIfNearActor();
    }
}
