package org.example.some.animals;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.ThirdLevel;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Mushroom extends AbstractAnimal {
    public Mushroom(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/mushroomR.png",
                "file:src/main/resources/images/thirdLevel/animals/mushroomL.png",
                "src/main/resources/sound/jumpRabbit.mp3",
                "file:src/main/resources/images/thirdLevel/products/miniMushroom.png");
        animalView.setFitHeight(90);
        animalView.setFitWidth(70);
        giveProduct();
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
        if (ThirdLevel.countMushroom >= 1) {
            if (hungerLvl > 70) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 20) {
                            Platform.runLater(() -> {
//                                if (productView == null) {
                                ImageView productView = new ImageView(product);
                                productView.setId("product");
                                productView.setFitWidth(40);
                                productView.setFitHeight(40);
                                productView.setX(animalView.getLayoutX() + 30);
                                productView.setY(animalView.getLayoutY() + 30);
                                productView.setCursor(Cursor.HAND);

                                productView.setOnMouseClicked(event -> {
                                    AbstractAnimal.root.getChildren().remove(productView);
                                    storage.addMiniMushrooms();
                                });

                                AbstractAnimal.root.getChildren().add(1, productView);
//                                }
                            });
                        } else {
                            timer.cancel();
                        }
                    }
                };

                timer.scheduleAtFixedRate(task, 0, 50000);
            }
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
                    cost = (int) (1150 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }

}
