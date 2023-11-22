package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.Interactable;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor implements Interactable {
    private List<Item> inventory;

    public Player(Cell cell) {
        super(cell, 5);
        this.inventory = new ArrayList<>();
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void performAction() {
        fightEnemyIfPresent();
        getSurroundingItem();
    }

    private Optional<Actor> getSurroundingEnemy() {
        Cell[] surroundingCells = getSurroundingCells();
        for (Cell cell : surroundingCells) {
            if (cell != null && cell.containsSkeleton()) {
                System.out.println(cell.getActor().getTileName());
                return Optional.of(cell.getActor());
            }
        }
        return Optional.empty();
    }

    private void fightEnemyIfPresent() {
        if (getSurroundingEnemy().isPresent()) {
            Actor skeleton = getSurroundingEnemy().get();
            skeleton.takeDamage(this.getDamage());
            System.out.println(skeleton.getHealth());
            if (skeleton.getHealth() <= 0) {
                skeleton.getCell().setActor(null);
            }
        }
    }

    private Cell[] getSurroundingCells() {
        return new Cell[]{
                this.getCell().getNeighbor(-1, 0),
                this.getCell().getNeighbor(1, 0),
                this.getCell().getNeighbor(0, -1),
                this.getCell().getNeighbor(0, 1)
        };
    }

    private void getSurroundingItem() {
        Cell[] surroundingCells = getSurroundingCells();
        for (Cell cell : surroundingCells) {
            if (cell != null && cell.containsItem()) {
                System.out.println("Item obtained: " + cell.getItem().getName());
                pickupItemIfPresent(cell.getItem());
                cell.removeItem();
                break;
            }
        }
    }

    private void pickupItemIfPresent(Item item) {
        inventory.add(item);
    }
}
