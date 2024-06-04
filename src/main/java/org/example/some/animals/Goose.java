package org.example.some.animals;


import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Goose extends AbstractAnimal{


    public Goose(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/firstLevel/animals/GooseRight.png",
                "file:src/main/resources/images/firstLevel/animals/GooseLeft.png",
                "src/main/resources/sound/geese.mp3",
                "file:src/main/resources/images/firstLevel/products/egg.png"
        ) ;

    }


    @Override
    public void giveProduct() {
        if (FirstLevel.countGoose >= 1) {
            if (hungerLvl > 25) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 25) {

                            ImageView productView = new ImageView(product);
                            productView.setFitWidth(40);
                            productView.setFitHeight(40);
                            productView.setX(animalView.getLayoutX() + 30);
                            productView.setY(animalView.getLayoutY() + 30);
                            productView.setCursor(Cursor.HAND);

                            productView.setOnMouseClicked(event -> {
                                AbstractAnimal.root.getChildren().remove(productView);
                                storage.addProduct1();
                            });

                            Platform.runLater(() -> AbstractAnimal.root.getChildren().add(1, productView));
                        } else {
                            timer.cancel();
                        }
                    }
                };


                timer.scheduleAtFixedRate(task, 0, 30000);
            }
        }
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 55;
            cost += 50;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 120;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

}
