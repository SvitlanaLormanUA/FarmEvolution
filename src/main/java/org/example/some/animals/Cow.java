package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Cow extends AbstractAnimal {

    private boolean movingForward = true;



    ImageView productView;

    public Cow(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/firstLevel/animals/cowRight.png",
                "file:src/main/resources/images/firstLevel/animals/cowLeft.png",
                "src/main/resources/sound/cow.mp3",
                "file:src/main/resources/images/fullReaction.png"
        );

    }

    @Override
    public void feed() {
        if (hungerLvl < 100) {
            hungerLvl += 50;
            cost += 80;

            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 2000;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
        if (FirstLevel.countSheep >= 1) {
            if (hungerLvl > 20) {
                // Створення таймера (завдання, яке виконується через певний час
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 70) {
                            productView = new ImageView(product);
                            productView.setFitWidth(60);
                            productView.setFitHeight(50);
                            productView.setCursor(Cursor.HAND);

                            // Initial positioning
                            updateProductViewPosition();

                            productView.setOnMouseClicked(event -> {
                                AbstractAnimal.root.getChildren().remove(productView);
                                FirstLevel.wallet.income(80);
                            });

                            Platform.runLater(() -> AbstractAnimal.root.getChildren().add(1, productView));
                        } else {
                            timer.cancel();
                        }
                    }
                };

                // Запуск завдання з інтервалом 35 секунд (5000 мілісекунд)
                timer.scheduleAtFixedRate(task, 0, 70000);
            }
        }
    }

    private void updateProductViewPosition() {
        if (productView != null) {
            productView.setX(animalView.getLayoutX() + animalView.getFitWidth());
            productView.setY(animalView.getLayoutY() + 10);
        }
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();

        deltaX = 2; // Small step forward
        deltaY = random.nextInt(10) - 5; // Slight variation in y-direction

        // Check if the cow has reached the end of the movement range
        if (movingForward && (x + deltaX > worldEndX - animalView.getFitWidth())) {
            animalView.setImage(new Image(imagePathLeft));
            movingForward = false; // Switch to moving backward
        } else if (!movingForward && (x - deltaX < worldStartX)) {
            animalView.setImage(new Image(imagePath));
            movingForward = true; // Switch to moving forward
        }

        // Adjust deltaX based on the current direction
        if (!movingForward) {
            deltaX = -deltaX;
        }

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);

        updateProductViewPosition();
    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        translateTransition.pause();
        double newX = event.getSceneX() - animalView.getFitWidth() / 2;
        double newY = event.getSceneY() - animalView.getFitHeight() / 2;

        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - animalView.getFitWidth()) newX = worldEndX - animalView.getFitWidth();
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - animalView.getFitHeight()) newY = worldEndY - animalView.getFitHeight();

        animalView.setLayoutX(newX);
        animalView.setLayoutY(newY);

        updateProductViewPosition();
        playSound();
    }
}
