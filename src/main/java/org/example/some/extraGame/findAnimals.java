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
import org.example.some.FirstLevel;

import java.util.HashSet;
import java.util.Set;

import static org.example.some.FirstLevel.coins;

public class findAnimals extends Application {

    private int foundAnimals = 0;
    private final int totalAnimals = 11; // Кількість тварин
    private Set<Circle> foundCircles = new HashSet<>();

    private static final int COINS = 90;

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
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Знайди тварин");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private StackPane createInstructionOverlay(Pane mainPane) {
        // Створення прозорого фону
        Rectangle bg = new Rectangle(800, 600, Color.BLACK);
        bg.setOpacity(0.8);

        // Текст інструкції
        Label instructions = new Label("Вітаємо в грі 'Find Animal'!\n\n" +
                "1. Натисніть на тварину, щоб знайти її.\n" +
                "Знайдена тварина буде відмічена напівпрозорим колом.\n" +
                "2. Коли всі тварини будуть знайдені, з'явиться повідомлення.\n" +
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


    private void showAlert(Stage primaryStage) {
        Stage alertStage = new Stage();

        Label message = new Label("Всі тварини знайдені!");
        Button closeButton = new Button("Закрити гру");
        closeButton.setOnAction(e -> {
            addCoins();
            alertStage.close();
            primaryStage.close();
        });

        VBox vbox = new VBox(10, message, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 100);

        Scene alertScene = new Scene(vbox);
        alertStage.setScene(alertScene);
        alertStage.setTitle("Гру закінчено");
        alertStage.show();
    }
    private void addCoins() {
        coins+=COINS;
        FirstLevel.wallet.income(COINS);
        FirstLevel.wallet.setCoins(coins);
        FirstLevel.saveCoins();
    }


}


