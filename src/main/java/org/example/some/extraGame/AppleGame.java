package org.example.some.extraGame;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class AppleGame {
    private static final int WIDTH = 580;
    private static final int HEIGHT = 700;
    private static final int BASKET_WIDTH = 110;
    private static final int BASKET_HEIGHT = 90;
    private static final int APPLE_SIZE = 40;
    private static final int APPLE_DROP_INTERVAL = 2000; // milliseconds

    private Image redAppleImage;
    private Image greenAppleImage;
    private Image basketImage;
    private Image backgroundImage;
    private double basketX;
    private double basketY;
    private boolean moveLeft;
    private boolean moveRight;

    private List<Apple> apples = new ArrayList<>();
    private Random random = new Random();
    private long lastAppleDropTime = 0;

    private int score = 0;
    private int redAppleCount = 0;
    private boolean gameWon = false;
    private AnimationTimer gameLoop;

    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        StackPane mainPane = new StackPane(root);
        mainPane.getChildren().add(createInstructionOverlay(mainPane));

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Apple Game");
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Load images
        redAppleImage = new Image("file:src/main/resources/images/extraGame/red_apple.png");
        greenAppleImage = new Image("file:src/main/resources/images/extraGame/green_apple.png");
        basketImage = new Image("file:src/main/resources/images/extraGame/basket.png");
        backgroundImage = new Image("file:src/main/resources/images/extraGame/AppleGameBackgr.png");

        // Initialize basket position
        basketX = (double) (WIDTH - BASKET_WIDTH) / 2;
        basketY = HEIGHT - BASKET_HEIGHT - 20;

        // Handle keyboard input
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
        });

        // Game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameWon) {
                    return;
                }

                // Clear canvas
                gc.clearRect(0, 0, WIDTH, HEIGHT);

                // Draw background
                gc.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT);

                // Move basket
                if (moveLeft && basketX > 0) {
                    basketX -= 5;
                }
                if (moveRight && basketX < WIDTH - BASKET_WIDTH) {
                    basketX += 5;
                }

                // Draw basket
                gc.drawImage(basketImage, basketX, basketY, BASKET_WIDTH, BASKET_HEIGHT);

                // Drop apples
                if (now - lastAppleDropTime > APPLE_DROP_INTERVAL * 1_000_000) {
                    dropApple();
                    lastAppleDropTime = now;
                }

                // Move apples
                Iterator<Apple> iterator = apples.iterator();
                while (iterator.hasNext()) {
                    Apple apple = iterator.next();
                    apple.y += 5;

                    // Check if apple is caught by the basket
                    if (apple.y + APPLE_SIZE > basketY && apple.y < basketY + BASKET_HEIGHT &&
                            apple.x + APPLE_SIZE > basketX && apple.x < basketX + BASKET_WIDTH) {
                        if (apple.isRed) {
                            score++;
                            redAppleCount++;
                        } else {
                            score--;
                        }
                        iterator.remove();

                        // Check if the game is won
                        if (score >= 15) {
                            gameWon = true;
                            stop();
                            Platform.runLater(() -> showAlert());
                            return;
                        }
                    }

                    // Remove apples that fall off the screen
                    if (apple.y > HEIGHT) {
                        iterator.remove();
                    }

                    // Draw apple
                    if (apple.isRed) {
                        gc.drawImage(redAppleImage, apple.x, apple.y, APPLE_SIZE, APPLE_SIZE);
                    } else {
                        gc.drawImage(greenAppleImage, apple.x, apple.y, APPLE_SIZE, APPLE_SIZE);
                    }
                }

                // Display score
                gc.fillText("Score: " + score, 10, 20);
            }
        };

        gameLoop.start();
    }

    private void dropApple() {
        boolean isRed = random.nextBoolean();
        double x = random.nextDouble() * (WIDTH - APPLE_SIZE);
        apples.add(new Apple(x, 0, isRed));
    }

    private static class Apple {
        double x;
        double y;
        boolean isRed;

        Apple(double x, double y, boolean isRed) {
            this.x = x;
            this.y = y;
            this.isRed = isRed;
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Гру закінчено");
        alert.setHeaderText(null);

        Label message = new Label("Всі яблука зібрано!");
        Button closeButton = new Button("Закрити гру");
        closeButton.setOnAction(e -> Platform.exit());

        VBox vbox = new VBox(10, message, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 100);

        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }

    private StackPane createInstructionOverlay(Pane mainPane) {
        // Створення прозорого фону
        Rectangle bg = new Rectangle(580, 700, Color.BLACK);
        bg.setOpacity(0.8);

        // Текст інструкції
        Label instructions = new Label("Інструкція:\n" +
                "1. Використовуйте клавіші стрілок вліво і вправо, щоб переміщати кошик.\n" +
                "2. Ловіть червоні яблука, щоб заробити очки.\n" +
                "3. Уникайте зелених яблук, вони зменшують ваші очки.\n" +
                "4. Гра закінчується, коли ви зловите 15 червоних яблук.\n" +
                "Натисніть 'Почати' для початку гри.");
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
}