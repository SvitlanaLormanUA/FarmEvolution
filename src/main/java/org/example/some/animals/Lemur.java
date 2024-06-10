package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Lemur extends AbstractAnimal{

    public Lemur(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/lemurRight.png",
                "file:src/main/resources/images/secondLevel/animals/lemurLeft.png",
                "src/main/resources/sound/jumpRabbit.mp3",
                "file:src/main/resources/images/secondLevel/products/mango.png"
        );

        animalView.setFitWidth(110);
        animalView.setFitHeight(120);

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
    public void feed() {
        if(hungerLvl<100) {
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
            if (hungerLvl > 50) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 50) {

                            ImageView productView = new ImageView(product);
                            productView.setFitWidth(30);
                            productView.setFitHeight(35);
                            productView.setX(animalView.getLayoutX() + 40);
                            productView.setY(animalView.getLayoutY() + 40);
                            productView.setCursor(Cursor.HAND);

                            productView.setOnMouseClicked(event -> {
                                AbstractAnimal.root.getChildren().remove(productView);
                                storage.addMango();
                            });

                            Platform.runLater(() -> AbstractAnimal.root.getChildren().add(productView));
                        } else {
                            timer.cancel();
                        }
                    }
                };


                timer.scheduleAtFixedRate(task, 0, 70000);
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
                    cost = (int) (850 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5000);
    }

}
