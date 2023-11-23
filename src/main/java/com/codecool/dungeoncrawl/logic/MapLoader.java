package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.ItemType;
import com.codecool.dungeoncrawl.data.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String text) {
        InputStream is = MapLoader.class.getResourceAsStream(text);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Item(cell, ItemType.KEY, "key");
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Item(cell, ItemType.WEAPON, "bazooka");
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Item(cell, ItemType.TREE, "tree");
                            break;
                        case 'c':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Cow(cell);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS);
                            break;
                        case 'S':
                            cell.setType(CellType.CHEESE);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new Blob(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Item(cell, ItemType.CROWN, "crown");
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
