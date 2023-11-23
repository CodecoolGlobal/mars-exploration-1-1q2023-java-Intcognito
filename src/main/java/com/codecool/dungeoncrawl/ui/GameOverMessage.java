package com.codecool.dungeoncrawl.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class GameOverMessage extends StackPane {
    public GameOverMessage() {
        Label label = new Label("Game over :(");
        getChildren().add(label);
    }
}
