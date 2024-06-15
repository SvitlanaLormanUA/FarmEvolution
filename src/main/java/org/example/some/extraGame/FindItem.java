package org.example.some.extraGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import org.example.some.SecondLevel;
import org.example.some.ThirdLevel;

import java.util.HashSet;
import java.util.Set;

import static org.example.some.FirstLevel.coins;

public class FindItem extends Application {

    private int foundAnimals = 0;
    private final int totalAnimals = 12; // Кількість предметів
    private Set<Circle> foundCircles = new HashSet<>();
    private static final int COINS = 125;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(t -> {
            FirstLevel.saveState();
            SecondLevel.saveState();
            ThirdLevel.saveState();
            // System.out.println("closed");
            try {
                stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // Завантаження головного зображення
        Image mainImage = new Image("file:src/main/resources/images/extraGame/FindItem.png");
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(800);
        mainImageView.setFitHeight(600);

        // Створення Pane для зображення
        Pane pane = new Pane(mainImageView);

        // Координати
        double[][] coordinates = {
                {440, 450}, // годинник
                {515, 565}, // барабан
                {770, 340}, // кролик
                {440, 60}, // замок
                {695, 315}, // книга
                {690, 440}, // ключ
                {420, 510}, // кулька
                {760, 230}, // лампа
                {710, 530}, // пісочні
                {550, 510}, // маска
                {680, 120}, // шляпа
                {560, 185}, // бант
        };

        // Додавання обробника кліків миші
        pane.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();

            // Перевірка, чи клік зроблено на будь-який предмет (в межах радіусу 20 пікселів, наприклад)
            for (double[] coords : coordinates) {
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
                        Circle circle = new Circle(coords[0], coords[1], 25, Color.YELLOW);
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
        primaryStage.setTitle("Знайди предмети");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane createInstructionOverlay(Pane mainPane) {
        // Створення прозорого фону
        Rectangle bg = new Rectangle(800, 600, Color.BLACK);
        bg.setOpacity(0.8);

        // Текст інструкції
        Label instructions = new Label("Інструкція:\n\n" +
                "1. Натисніть на предмет, щоб відмітити його.\n" +
                "Знайдені будуть відмічені напівпрозорим колом.\n" +
                "2. Коли всі предмети будуть знайдені, з'явиться повідомлення.\n" +
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

        Label message = new Label("Всі предмети знайдені!");
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
