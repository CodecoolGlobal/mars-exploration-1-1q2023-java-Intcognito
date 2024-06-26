package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.Movement;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Left implements KeyHandler {
    public static final KeyCode code = KeyCode.LEFT;
    private final GameLogic logic = new GameLogic();
    private final Movement movement = new Movement(logic);

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode()) &&
                movement.checkIfMoveIsValid(map.getPlayer().getCell().getNeighbor(-1, 0)))

            map.getPlayer().move(-1, 0);
    }
}
