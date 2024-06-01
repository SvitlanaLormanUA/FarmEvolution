package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

public class Donkey extends AbstractAnimal{



    public Donkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,  well, feeder,
                "file:src/main/resources/images/firstLevel/animals/Donkey.png",
                "src/main/resources/sound/donkey.mp3",
                "file:src/main/resources/images/firstLevel/products/wool.png") ;
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 50;
            if (hungerLvl >= 100) {
                hungerLvl = 100;
                cost = 100;
                Emotion emotion = new Emotion();
                emotion.wellFed(animalView.getX() + animalView.getFitWidth(), animalView.getY() - animalView.getFitHeight()/2 - 5); ;

            }

            AbstractAnimal.feeder.getFood();
        }
    }
    @Override
    public void giveProduct() {

    }
    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1500)); // Faster duration
        translateTransition.setNode(animalView);
        setRandomDirection();
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            setRandomDirection();
            translateTransition.play();
        });

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getX();
        double y = animalView.getY();

        // Ensure primarily right-left direction with faster speed
        double deltaX = random.nextInt(200) - 100; // Larger variation in x-direction
        double deltaY = random.nextInt(20) - 10;   // Small variation in y-direction

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX || newX > worldEndX - animalView.getFitWidth()) {
            deltaX = -deltaX;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setToX(deltaX);
        translateTransition.setToY(deltaY);
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

        animalView.setX(newX);
        animalView.setY(newY);
        playSound();
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = animalView.getX();
        double y = animalView.getY();
        boolean outOfBounds = false;

        if (x < worldStartX) {
            x = worldStartX;
            outOfBounds = true;
        } else if (x > worldEndX - animalView.getFitWidth()) {
            x = worldEndX - animalView.getFitWidth();
            outOfBounds = true;
        }

        if (y < worldStartY) {
            y = worldStartY;
            outOfBounds = true;
        } else if (y > worldEndY - animalView.getFitHeight()) {
            y = worldEndY - animalView.getFitHeight();
            outOfBounds = true;
        }

        if (outOfBounds) {
            TranslateTransition transitionBack = new TranslateTransition(Duration.millis(500), animalView);
            transitionBack.setToX(x);
            transitionBack.setToY(y);
            transitionBack.play();
        }
        translateTransition.play();
        playSound();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {
        if (!openedMenu) {
            double x = event.getSceneX();
            double y = event.getSceneY();

            // Ensure the menu does not go out of the window
            double menuWidth = 200;
            double menuHeight = 200;
            if (x + menuWidth > root.getWidth()) {
                x = root.getWidth() - menuWidth;
            }
            if (y + menuHeight > root.getHeight()) {
                y = root.getHeight() - menuHeight;
            }
            addMenu(x, y);

        } else {
            removeMenu();
        }
        playSound();
    }



}
