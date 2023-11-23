package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Movement {
    public Movement(GameLogic logic) {
        this.logic = logic;
    }
    private GameLogic logic;

    public boolean checkIfMoveIsValid(Cell cell) {
        return cell.getActor() == null && cell.getTileName().equals("floor");
    }

    public void moveNPCs() {
        List<Actor> enemies = collectEnemies();
        for (Actor enemy : enemies) {
            Cell enemyPosition = enemy.getCell();
            while (true) {
                int[] movement = randomMovement();
                if (checkIfMoveIsValid(enemyPosition.getNeighbor(movement[0], movement[1]))) {
                    enemyPosition.getActor().move(movement[0], movement[1]);
                    break;
                }
            }
        }
    }

    private List<Actor> collectEnemies() {
        List<Actor> enemies = new ArrayList<>();
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null && cell.getActor().getTileName().equals("skeleton")) {
                    enemies.add(cell.getActor());
                }
            }
        }
        return enemies;
    }

    public int[] randomMovement() {
        int[] stepLeft = {-1, 0};
        int[] stepUp = {0, -1};
        int[] stepRight = {1, 0};
        int[] stepDown = {0, 1};
        List<int[]> possibleSteps = new ArrayList<>();
        possibleSteps.add(stepLeft);
        possibleSteps.add(stepUp);
        possibleSteps.add(stepRight);
        possibleSteps.add(stepDown);
        int[] chosenStep = possibleSteps.get((int) (Math.random() * 4));
        return chosenStep;
    }

}
