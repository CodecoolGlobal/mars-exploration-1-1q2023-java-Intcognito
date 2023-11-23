package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Cow extends Actor {
  public Cow(Cell cell) {
    super(cell, 1);
  }
  @Override
  public String getTileName() {
    return "cow";
  }
}
