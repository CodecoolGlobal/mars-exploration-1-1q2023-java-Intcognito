package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.Movement;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Set;

import java.util.stream.Collectors;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;
    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private Movement movement;
    private double cameraX;
    private double cameraY;
  
    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
        this.movement = new Movement(logic);
        this.cameraX = logic.getMap().getPlayer().getX() * Tiles.TILE_WIDTH - canvas.getWidth() / 2;
        this.cameraY = logic.getMap().getPlayer().getY() * Tiles.TILE_WIDTH - canvas.getHeight() / 2;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }

        updateCamera();
        movement.moveNPCs();
        refresh();
    }
    public double getCameraX() {
        return this.cameraX;
    }
    public double getCameraY() {
        return this.cameraY;
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {

                    Tiles.drawTile(context, cell.getActor(), x, y, cameraX, cameraY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, cameraX, cameraY);
                } else {
                    Tiles.drawTile(context, cell, x, y, cameraX, cameraY);

                }
            }
        }

        mainStage.setHealthLabelText(logic.getPlayerHealth());
        mainStage.setItemLabelText(String.join(", ", logic.getPlayerItems()));
        mainStage.setDamageValueLabelText(logic.getPlayerDamage());
    }
    private void updateCamera() {
        Player player = logic.getMap().getPlayer();
        if (player != null) {
            cameraX = Math.max(0, Math.min(cameraX, logic.getMapWidth() * Tiles.TILE_WIDTH - canvas.getWidth()));
            cameraY = Math.max(0, Math.min(cameraY, logic.getMapHeight() * Tiles.TILE_WIDTH - canvas.getHeight()));
            cameraX = logic.getMap().getPlayer().getX()* Tiles.TILE_WIDTH - canvas.getWidth() / 2;
            cameraY = logic.getMap().getPlayer().getY() * Tiles.TILE_WIDTH - canvas.getHeight() / 2;
        }
    }
}
