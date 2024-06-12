package org.example.some.animals;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;
import java.util.Timer;
import java.util.TimerTask;

public class Gnome extends AbstractAnimal{
    public Gnome(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/gnomeRight.png",
                "file:src/main/resources/images/thirdLevel/animals/gnomeLeft.png",
                "src/main/resources/sound/dragonfly.mp3",
                "file:src/main/resources/images/thirdLevel/products/pouch.png");
        animalView.setFitHeight(110);
        animalView.setFitWidth(80);
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
        if (hungerLvl > 0) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (hungerLvl > 0) {

                        ImageView productView = new ImageView(product);
                        productView.setFitWidth(35);
                        productView.setFitHeight(35);
                        productView.setX(animalView.getLayoutX() + 30);
                        productView.setY(animalView.getLayoutY() + 30);
                        productView.setCursor(Cursor.HAND);

                        productView.setOnMouseClicked(event -> {
                            AbstractAnimal.root.getChildren().remove(productView);
                            storage.addPouch();
                        });

                        Platform.runLater(() -> AbstractAnimal.root.getChildren().add(productView));
                    } else {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };


            timer.scheduleAtFixedRate(task, 0, 70000);
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
                    cost = (int) (850 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }
}
