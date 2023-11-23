package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Interactable;
import com.codecool.dungeoncrawl.data.ItemType;

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
        //fightEnemyIfPresent();
        fightIfNearActor();
        getSurroundingItem();
    }

    /*private Optional<Actor> getSurroundingEnemy() {
        Cell[] surroundingCells = getSurroundingCells();
        for (Cell cell : surroundingCells) {
            if (cell != null && cell.containsCharacter()) {
                System.out.println(cell.getActor().getTileName());
                return Optional.of(cell.getActor());
            }
        }
        return Optional.empty();
    }*/

    /*private void fightEnemyIfPresent() {
        if (getSurroundingEnemy().isPresent()) {
            Actor skeleton = getSurroundingEnemy().get();
            skeleton.takeDamage(this.getDamage());
            System.out.println(skeleton.getHealth());
            if (skeleton.getHealth() <= 0) {
                skeleton.getCell().setActor(null);
            }
        }
    }*/

    private void getSurroundingItem() {
        List<Cell>  surroundingCells = new ArrayList<>(List.of(getSurroundingCells()));
        surroundingCells.add(getCell());
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
        if (item.getType().equals(ItemType.WEAPON)) {
            setDamage(10);
        }
    }
}
