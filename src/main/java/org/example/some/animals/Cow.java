package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Well;

public class Cow extends AbstractAnimal {

    private boolean movingForward = true;
    private int amountOfMeals;
    private MilkEmotion milkEmotion;

    public Cow(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder,
                "file:src/main/resources/images/firstLevel/animals/cowRight.png",
                "file:src/main/resources/images/firstLevel/animals/cowLeft.png",
                "src/main/resources/sound/cow.mp3",
                "file:src/main/resources/images/firstLevel/products/milk.png"
        );
        this.amountOfMeals = 0;
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
        if (FirstLevel.countCow >= 1) {
            if (hungerLvl > 55) {
                double x = animalView.getLayoutX() + animalView.getFitWidth();
                double y = animalView.getLayoutY() + 10;
                milkEmotion = new MilkEmotion(x, y);
            }
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
        if (milkEmotion != null) {
            milkEmotion.updatePosition(newX + animalView.getFitWidth(), newY + 10);
        }
        playSound();
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();
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
}
