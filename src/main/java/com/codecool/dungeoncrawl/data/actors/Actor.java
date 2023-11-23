package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

import java.util.Optional;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int damage;
    private int cycle = 0;

    public Actor(Cell cell, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.damage = damage;
        this.cycle = cycle;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void takeDamage(int damageAmount) {
        this.health -= damageAmount;
    }
    public int getDamage() {
        return this.damage;
    }
    public void setDamage(int amount) {
        this.damage = amount;
    }

    public int getCycle() {
        return this.cycle;
    }
    public void setCycle() {
        if (cycle < 3) {
            cycle++;
        } else {
            cycle = 0;
        }
    }

    protected Cell[] getSurroundingCells() {
        return new Cell[]{
                this.getCell().getNeighbor(-1, 0),
                this.getCell().getNeighbor(1, 0),
                this.getCell().getNeighbor(0, -1),
                this.getCell().getNeighbor(0, 1),
        };
    }
    protected Optional<Actor> getSurroundingCharacter() {
        Cell[] surroundingCells = getSurroundingCells();
        for (Cell cell : surroundingCells) {
            if (cell != null && cell.containsEnemy()) {
                System.out.println(cell.getActor().getTileName());
                return Optional.of(cell.getActor());
            } else if (cell != null && cell.containsPlayer()) {
                return Optional.of(cell.getActor());
            }
        }
        return Optional.empty();
    }
    protected void fightIfNearActor() {
        if (getSurroundingCharacter().isPresent()) {
            Actor nearbyCharacter = getSurroundingCharacter().get();
            nearbyCharacter.takeDamage(this.getDamage());
            System.out.println(nearbyCharacter.getHealth());
            if (nearbyCharacter.getHealth() <= 0) {
                nearbyCharacter.getCell().setActor(null);
            }
            if (nearbyCharacter.getHealth() >= 0) {
                this.health -= nearbyCharacter.getDamage();
            }
        }
    }

}
