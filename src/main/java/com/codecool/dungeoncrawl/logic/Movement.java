package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Movement {

  private final GameLogic logic;
  
  public Movement(GameLogic logic) {
    this.logic = logic;
  }

    public boolean checkIfMoveIsValid(Cell cell) {
        return cell.getActor() == null && cell.getItem() == null && cell.getType().isPassable();
    }

  public void moveNPCs() {
    moveSkeletons();
    moveBlobs();
  }

  private void moveSkeletons() {
    List<Actor> skeletons = collectSkeletons();
    for (Actor skeleton : skeletons) {
      Cell enemyPosition = skeleton.getCell();
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

  private void moveBlobs() {
    List<Actor> blobs = collectBlobs();
    for (Actor blob : blobs) {
      Cell enemyPosition = blob.getCell();
      int moveCycle = blob.getCycle();
      if (moveCycle < 2) {
        if (enemyPosition.getNeighbor(-1, 0).getTileName().equals("floor")) {
          blob.move(-1, 0);
        }
        blob.setCycle();
      } else {
        if (enemyPosition.getNeighbor(1, 0).getTileName().equals("floor")) {
          blob.move(1, 0);
        }
        blob.setCycle();
      }
    }
  }

  private List<Actor> collectSkeletons() {
    List<Actor> skeletons = new ArrayList<>();
    for (int x = 0; x < logic.getMapWidth(); x++) {
      for (int y = 0; y < logic.getMapHeight(); y++) {
        Cell cell = logic.getCell(x, y);
        if (cell.getActor() != null && cell.getActor().getTileName().equals("skeleton")) {
          skeletons.add(cell.getActor());
        }
      }
    }
    return skeletons;
  }

  private List<Actor> collectBlobs() {
    List<Actor> blobs = new ArrayList<>();
    for (int x = 0; x < logic.getMapWidth(); x++) {
      for (int y = 0; y < logic.getMapHeight(); y++) {
        Cell cell = logic.getCell(x, y);
        if (cell.getActor() != null && cell.getActor().getTileName().equals("blob")) {
          blobs.add(cell.getActor());
        }
      }
    }
    return blobs;
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
