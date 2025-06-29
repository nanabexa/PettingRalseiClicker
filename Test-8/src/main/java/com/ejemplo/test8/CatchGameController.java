package com.ejemplo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class CatchGameController {

    @FXML
    private Pane rootPane;  // El pane principal (la raíz del fxml)

    @FXML
    private Label scoreLabel;

    private Circle circle;
    private int score = 0;
    private Random random = new Random();

    @FXML
    public void initialize() {
        // Crear el círculo rojo
        circle = new Circle(20, Color.RED);

        // Añadir círculo al pane
        rootPane.getChildren().add(circle);

        // Mover el círculo a una posición inicial aleatoria
        moveCircleToRandomPosition();

        // Manejar click sobre el círculo
        circle.setOnMouseClicked(this::handleCircleClick);
    }

    private void handleCircleClick(MouseEvent event) {
        score++;
        scoreLabel.setText("Puntaje: " + score);
        moveCircleToRandomPosition();
    }

    private void moveCircleToRandomPosition() {
        double x = 20 + random.nextDouble() * (rootPane.getWidth() - 40);
        double y = 20 + random.nextDouble() * (rootPane.getHeight() - 40);
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
}
