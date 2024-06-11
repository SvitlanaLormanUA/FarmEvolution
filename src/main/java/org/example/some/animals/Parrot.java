package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Parrot extends AbstractAnimal{

    private boolean hasProduct;
    public Parrot(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/parrotRight.png",
                "file:src/main/resources/images/secondLevel/animals/parrotLeft.png",
                "src/main/resources/sound/parrots.mp3",
                "file:src/main/resources/images/secondLevel/products/nut.png"
        );

        animalView.setFitWidth(100);
        animalView.setFitHeight(110);

        giveProduct();
    }

    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(800));
        translateTransition.setNode(animalView);
        directionRight = true;
        translateTransition.setOnFinished(event -> {
            animalView.setLayoutX(animalView.getLayoutX()+deltaX);
            animalView.setLayoutY(animalView.getLayoutY()+deltaY);

            // Скидання translateX і translateY
            animalView.setTranslateX(0);
            animalView.setTranslateY(0);

            setRandomDirection();
            translateTransition.play();
        });
        setRandomDirection();
        translateTransition.play();

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();

        if (directionRight) {
            deltaX = random.nextInt(10, 30);
        } else {
            deltaX = random.nextInt(-30, -10);
        }
        deltaY = random.nextInt(100) - 50;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX && !directionRight) {
            deltaX = -deltaX;
            animalView.setImage(new Image(imagePath));
            directionRight = !directionRight;
        } else if (newX > worldEndX && directionRight) {
            deltaX = -deltaX;
            if (imagePathLeft != null) {
                animalView.setImage(new Image(imagePathLeft));
            }
            directionRight = !directionRight;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            if(AbstractAnimal.feeder.haveFood()) {
                hungerLvl += 40;
            }
            if (hungerLvl > 100) {
                hungerLvl = 100;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
        if (hungerLvl > 40) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (hungerLvl > 40) {
                        ImageView productView = new ImageView(product);
                        productView.setFitWidth(20);
                        productView.setFitHeight(15);
                        productView.setX(animalView.getLayoutX());
                        productView.setY(animalView.getLayoutY() + 50);
                        productView.setCursor(Cursor.HAND);

                        productView.setOnMouseClicked(event -> {
                            AbstractAnimal.root.getChildren().remove(productView);
                            storage.addNut();
                        });

                        Platform.runLater(() -> AbstractAnimal.root.getChildren().add(productView));
                    } else {
                        timer.cancel();
                    }
                }
            };


            timer.scheduleAtFixedRate(task, 0, 60000);
        }
    }

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (450 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}
