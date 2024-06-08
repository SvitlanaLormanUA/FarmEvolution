package org.example.some.extraGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class findAnimals extends Application {

    private int foundAnimals = 0;
    private final int totalAnimals = 11; // Кількість тварин
    private Set<Circle> foundCircles = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        // Завантаження головного зображення
        Image mainImage = new Image("file:src/main/resources/images/extraGame/findAnimals.png");
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(800);
        mainImageView.setFitHeight(600);

        // Створення Pane для зображення
        Pane pane = new Pane(mainImageView);

        // Координати тварин
        double[][] animalCoordinates = {
                {760, 60}, // координати верблюда
                {530, 40}, // лисиця
                {530, 165}, // заєць
                {720, 230}, // курка
                {760, 350}, // черепаха
                {450, 280}, // свиня
                {580, 350}, // їжак
                {480, 440}, // діно
                {440, 540}, // тигр
                {600, 550}, // пінгвін
                {720, 570}, // лось
        };

        // Додавання обробника кліків миші
        pane.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();

            // Перевірка, чи клік зроблено на будь-якій тварині (в межах радіусу 20 пікселів, наприклад)
            for (double[] coords : animalCoordinates) {
                if (Math.hypot(x - coords[0], y - coords[1]) <= 20) {
                    boolean alreadyFound = false;

                    // Перевірка, чи вже є кружечок на цих координатах
                    for (Circle circle : foundCircles) {
                        if (circle.getCenterX() == coords[0] && circle.getCenterY() == coords[1]) {
                            alreadyFound = true;
                            break;
                        }
                    }

                    if (!alreadyFound) {
                        Circle circle = new Circle(coords[0], coords[1], 20, Color.BLACK);
                        circle.setOpacity(0.3); // Напівпрозорість
                        pane.getChildren().add(circle);
                        foundCircles.add(circle);
                        foundAnimals++;
                        if (foundAnimals == totalAnimals) {
                            showAlert();
                        }
                    }
                    break;
                }
            }
        });

        // Налаштування сцени та етапу
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Знайди тварин");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Гру закінчено");
        alert.setHeaderText(null);

        Label message = new Label("Всі тварини знайдені!");
        Button closeButton = new Button("Закрити гру");
        closeButton.setOnAction(e -> Platform.exit());

        VBox vbox = new VBox(10, message, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 100);

        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
