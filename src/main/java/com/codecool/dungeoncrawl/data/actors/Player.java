package com.codecool.dungeoncrawl.data.actors;


import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Interactable;
import com.codecool.dungeoncrawl.data.ItemType;

import com.codecool.dungeoncrawl.data.*;


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
        openDoor();
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

        if (item.getType().equals(ItemType.WEAPON)) {
            setDamage(10);
        }
    }

    private void openDoor() {
        Cell[] surroundingCells = getSurroundingCells();
        for (Cell cell : surroundingCells) {
            if (cell.getType().equals(CellType.CLOSED_DOOR) && hasKey()) {
                cell.setType(CellType.OPENED_DOOR);
            }
        }
    }

    private boolean hasKey() {
        for (Item item : inventory) {
            if (item.getType().equals(ItemType.KEY)) {
                return true;
            }
        }
        return false;
    }

}
