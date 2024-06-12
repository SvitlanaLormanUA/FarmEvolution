package org.example.some.extraGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashSet;
import java.util.Set;

public class FindDifferences extends Application {

    private int foundAnimals = 0;
    private final int totalAnimals = 7; // Кількість тварин
    private Set<Circle> foundCircles = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        // Завантаження головного зображення
        Image mainImage = new Image("file:src/main/resources/images/extraGame/FindDifferences.png");
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(550);
        mainImageView.setFitHeight(700);

        // Створення Pane для зображення
        Pane pane = new Pane(mainImageView);

        // Координати тварин
        double[][] animalCoordinates = {
                {80, 445},
                {210, 410},
                {400, 410},
                {350, 440},
                {190, 630},
                {180, 560},
                {250, 580},
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
                        Circle circle = new Circle(coords[0], coords[1], 30, Color.RED);
                        circle.setOpacity(0.4); // Напівпрозорість
                        pane.getChildren().add(circle);
                        foundCircles.add(circle);
                        foundAnimals++;
                        if (foundAnimals == totalAnimals) {
                            showAlert(primaryStage);
                        }
                    }
                    break;
                }
            }
        });

        // Додавання інструкції
        StackPane instructionOverlay = createInstructionOverlay(pane);

        // Додавання інструкції та гри в головний StackPane
        StackPane root = new StackPane(pane, instructionOverlay);
        root.setAlignment(Pos.CENTER);

        // Налаштування сцени та етапу
        Scene scene = new Scene(root, 550, 700);
        primaryStage.setTitle("Знайди 7 відмінностей");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane createInstructionOverlay(Pane mainPane) {
        // Створення прозорого фону
        Rectangle bg = new Rectangle(550, 700, Color.BLACK);
        bg.setOpacity(0.8);

        // Текст інструкції
        Label instructions = new Label("Інструкція 'Find 7 Differences':\n\n" +
                "1. Знайдені відмінності треба відмічати на зображені знизу.\n" +
                "Для цього просто натисніть на той об'єкт, що відрізняється від верхньої картинки.\n" +
                "2. Коли всі відмінності будуть знайдені, з'явиться повідомлення.\n" +
                "3. Натисніть кнопку 'Почати', щоб розпочати гру.");
        instructions.setTextFill(Color.WHITE);
        instructions.setStyle("-fx-font-size: 16px;");
        instructions.setWrapText(true);

        VBox instructionBox = new VBox(instructions);
        instructionBox.setAlignment(Pos.CENTER);
        instructionBox.setPadding(new Insets(20));
        instructionBox.setSpacing(10);

        StackPane instructionOverlay = new StackPane(bg, instructionBox);
        instructionOverlay.setAlignment(Pos.CENTER);

        // Кнопка для закриття інструкції
        Button closeButton = new Button("Почати");
        closeButton.setOnAction(e -> instructionOverlay.setVisible(false));
        VBox closeBox = new VBox(closeButton);
        closeBox.setAlignment(Pos.BOTTOM_CENTER);
        closeBox.setPadding(new Insets(10));
        StackPane.setAlignment(closeButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(closeButton, new Insets(20));

        instructionOverlay.getChildren().add(closeBox);

        return instructionOverlay;
    }

    private void showAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Гру закінчено");
        alert.setHeaderText(null);

        Label message = new Label("Всі тварини знайдені!");
        Button closeButton = new Button("Закрити гру");

        VBox vbox = new VBox(10, message, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 100);

        alert.getDialogPane().setContent(vbox);

        closeButton.setOnAction(e -> {
            alert.hide();
            stage.close();
        });

        alert.show();
    }
}
