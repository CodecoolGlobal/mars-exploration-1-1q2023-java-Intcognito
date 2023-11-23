package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Item;

import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public List<String> getPlayerItems() {
        return map.getPlayer().getInventory().stream().map(Item::getName).collect(Collectors.toList());
    }

    public String getPlayerDamage() {
        return Integer.toString(map.getPlayer().getDamage());
    }

    public GameMap getMap() {
        return map;
    }
}