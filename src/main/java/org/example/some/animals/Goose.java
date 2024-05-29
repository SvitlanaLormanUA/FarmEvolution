package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Goose extends AbstractAnimal{


    public Goose(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,  well, feeder,
                "file:src/main/resources/images/Goose.png",
                "src/main/resources/sound/geese.mp3",
                "file:src/main/resources/images/egg.png") ;
    }


    @Override
    public void giveProduct() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 25) {

                    ImageView productView = new ImageView(product);
                    productView.setFitWidth(40);
                    productView.setFitHeight(40);
                    productView.setX(animalView.getX() + 30);
                    productView.setY(animalView.getY() + 30);

                    productView.setOnMouseClicked(event -> {
                        FirstLevel.wallet.income(8);
                        AbstractAnimal.root.getChildren().remove(productView);
                    });

                    Platform.runLater(() ->  AbstractAnimal.root.getChildren().add(1, productView));
                } else {
                    timer.cancel();
                }
            }
        };


        timer.scheduleAtFixedRate(task, 0, 30000);
    }

    @Override
    public void movement() {
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(animalView);
        translateTransition.setByX(10); // Мала амплітуда вбік
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Безкінечний цикл
        translateTransition.setAutoReverse(true); // Зворотній рух

        // Початок анімації
        translateTransition.play();

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void feed() {
        if(AbstractAnimal.hungerLvl<100) {
            hungerLvl += 55;
            AbstractAnimal.cost += 50;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 120;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

}
