package com.codecool.dungeoncrawl.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;


public class GameWinMessage extends StackPane {
    public GameWinMessage() {
        Label label = new Label("You win! :)");
        getChildren().add(label);
    }
}
