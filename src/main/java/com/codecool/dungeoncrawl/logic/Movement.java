package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Movement {
  public Movement(GameLogic logic) {
    this.logic = logic;
  }

    public boolean checkIfMoveIsValid(Cell cell) {
        return cell.getActor() == null && cell.getTileName().equals("floor") || cell.getType().equals(CellType.OPENED_DOOR);
    }


  public void moveNPCs() {
    List<Actor> enemies = collectEnemies();
    for (Actor enemy : enemies) {
      Cell enemyPosition = enemy.getCell();
      while (true) {
        int[] movement = randomMovement();
        if (checkIfMoveIsValid(enemyPosition.getNeighbor(movement[0], movement[1]))) {
          if (!checkIfNearPlayer(enemyPosition)) {
            enemyPosition.getActor().move(movement[0], movement[1]);
          }
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

  private boolean checkIfNearPlayer(Cell enemyPosition) {
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (enemyPosition.getNeighbor(i, j).containsPlayer()) {
          return true;
        }
      }
    }
    return false;
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
