package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

import java.util.Optional;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int damage;

    public Actor(Cell cell, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.damage = damage;
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
}
