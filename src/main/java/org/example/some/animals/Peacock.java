package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Peacock extends AbstractAnimal{
    public Peacock(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/peacock.png",
                "src/main/resources/sound/peacock.mp3",
                "file:src/main/resources/images/secondLevel/products/feather.png"
        );
        animalView.setFitWidth(150);
        animalView.setFitHeight(140);
        giveProduct();
    }
    @Override
    public void feed() {
        if (hungerLvl < 100) {
            if(AbstractAnimal.feeder.haveFood()) {
                hungerLvl += 30;
            }
            if (hungerLvl > 100) {
                hungerLvl = 100;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
//        if (FirstLevel.countGoose >= 1) {
            if (hungerLvl > 60) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 60) {
                            Platform.runLater(() -> {
//                                if (productView == null) {
                                ImageView productView = new ImageView(product);
                                productView.setId("product");
                                productView.setFitWidth(30);
                                productView.setFitHeight(75);
                                productView.setX(animalView.getLayoutX() + 30);
                                productView.setY(animalView.getLayoutY() + 30);
                                productView.setCursor(Cursor.HAND);

                                productView.setOnMouseClicked(event -> {
                                    AbstractAnimal.root.getChildren().remove(productView);
                                    storage.addFeather();
                                });

                                AbstractAnimal.root.getChildren().add(productView);
//                                }
                            });
                        } else {
                            timer.cancel();
                        }
                    }
                };


                timer.scheduleAtFixedRate(task, 0, 90000);
            }
//        }
    }

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (770 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(800));
        translateTransition.setNode(animalView);
        directionRight = true;  // Initial direction

        translateTransition.setOnFinished(event -> {
            animalView.setLayoutX(animalView.getLayoutX() + deltaX);

            // Reset translateX after movement
            animalView.setTranslateX(0);

            // Determine new direction and set deltaX
            setRandomDirection();

            // Play the transition again
            translateTransition.play();
        });

        // Start the movement
        setRandomDirection();
        translateTransition.play();

        // Set mouse event handlers
        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }


    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();

        deltaX = 7; // Small step forward
        deltaY = random.nextInt(10) - 5; // Slight variation in y-direction

        // Check if the cow has reached the end of the movement range
        if (directionRight && (x + deltaX > worldEndX - animalView.getFitWidth())) {
            directionRight = false; // Switch to moving backward
        } else if (!directionRight && (x - deltaX < worldStartX)) {
            directionRight = true; // Switch to moving forward
        }

        // Adjust deltaX based on the current direction
        if (!directionRight) {
            deltaX = -deltaX;
        }

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);

    }
}
