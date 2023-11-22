package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.Interact;
import com.codecool.dungeoncrawl.logic.GameLogic;

import java.util.Optional;

public class Player extends Actor implements Interact {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void performAction() {
        if (checkSurroundingCells().isPresent()) {
            Actor skeleton = checkSurroundingCells().get();
            skeleton.takeDamage(2);
            System.out.println(skeleton.getHealth());
            if (skeleton.getHealth() <= 0) {
                skeleton.getCell().setActor(null);
            }
        }
    }
    public Optional<Actor> checkSurroundingCells() {
        Cell[] cells = {
                this.getCell().getNeighbor(-1, 0),
                this.getCell().getNeighbor(1, 0),
                this.getCell().getNeighbor(0, -1),
                this.getCell().getNeighbor(0, 1)
        };
        for (Cell cell : cells) {
            if (cell != null && containsSkeleton(cell)) {
                System.out.println(cell.getActor().getTileName());
                return Optional.of(cell.getActor());
            }
        }
        return Optional.empty();
    }

}
