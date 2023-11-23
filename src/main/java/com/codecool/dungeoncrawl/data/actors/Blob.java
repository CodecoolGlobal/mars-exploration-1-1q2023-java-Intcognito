package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Blob extends Actor{
  public Blob(Cell cell) {
    super(cell, 3);
  }
  int cycle = 0;

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

  @Override
  public String getTileName() {
    return "blob";
  }
}
