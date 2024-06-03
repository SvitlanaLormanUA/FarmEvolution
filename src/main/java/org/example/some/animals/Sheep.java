package org.example.some.animals;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;


public class Sheep extends AbstractAnimal {

    public Sheep(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root,  Well well, Feeder feeder) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder,
                "file:src/main/resources/images/firstLevel/animals/sheepRight.png",
                "file:src/main/resources/images/firstLevel/animals/sheepLeft.png",
                "src/main/resources/sound/sheepmp3.mp3",
                "file:src/main/resources/images/firstLevel/products/wool.png"
        );

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
                        if (hungerLvl > 20) {

                            ImageView productView = new ImageView(product);
                            productView.setFitWidth(40);
                            productView.setFitHeight(40);
                            productView.setX(animalView.getLayoutX() + 30);
                            productView.setY(animalView.getLayoutY() + 30);
                            productView.setCursor(Cursor.HAND);

                            productView.setOnMouseClicked(event -> {
                                AbstractAnimal.root.getChildren().remove(productView);
                                FirstLevel.wallet.income(8);
                            });

                            Platform.runLater(() -> AbstractAnimal.root.getChildren().add(1, productView));
                        } else {
                            timer.cancel();
                        }
                    }
                };

                // Запуск завдання з інтервалом 5 секунд (5000 мілісекунд)
                timer.scheduleAtFixedRate(task, 0, 10000);
            }
        }
    }

    @Override
    public void feed() {
        if (hungerLvl < 100) {
            hungerLvl += 50;
            cost += 50;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 100;
            }
            AbstractAnimal.feeder.getFood();
        }
    }
}


