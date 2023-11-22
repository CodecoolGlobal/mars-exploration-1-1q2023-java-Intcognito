package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SpaceBar implements KeyHandler {
    public static final KeyCode code = KeyCode.SPACE;
    @Override
    public void perform(KeyEvent event, GameMap gameMap) {
        if (code.equals(event.getCode())) {
            gameMap.getPlayer().performAction();
        }
    }
}
