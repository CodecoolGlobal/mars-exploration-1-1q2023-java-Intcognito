package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(31, 3));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("bazooka", new Tile(11, 31));
        tileMap.put("buffedPlayer", new Tile(25,3));
        tileMap.put("tree", new Tile(4, 2));
        tileMap.put("closedDoor", new Tile(8, 9));
        tileMap.put("openedDoor", new Tile(9, 9));
        tileMap.put("cow", new Tile(27, 7));
        tileMap.put("grass", new Tile(5 ,0));
        tileMap.put("cheese", new Tile(18, 28));
        tileMap.put("blob", new Tile(27,8));
        tileMap.put("crown", new Tile(12, 24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, double cameraX, double cameraY) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                (x * TILE_WIDTH - cameraX), (y * TILE_WIDTH - cameraY), TILE_WIDTH, TILE_WIDTH);    
    }
}
