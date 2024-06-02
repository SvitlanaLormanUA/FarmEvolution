package org.example.some.extraGame;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class AppleGame extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BASKET_WIDTH = 100;
    private static final int BASKET_HEIGHT = 50;
    private static final int APPLE_SIZE = 30;
    private static final int APPLE_DROP_INTERVAL = 1000; // milliseconds

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

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Apple Catch Game");
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Load images
        redAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/red_apple.png")));
        greenAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_apple.png")));
        basketImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/basket.png")));
        backgroundImage = new Image((getClass().getResourceAsStream("/images/appleGame.png")));

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
        new AnimationTimer() {
            @Override
            public void handle(long now) {
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
                        }
                        iterator.remove();
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
        }.start();
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

    public static void main(String[] args) {
        launch();
    }
}
