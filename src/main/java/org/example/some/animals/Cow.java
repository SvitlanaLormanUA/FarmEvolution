package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

public class Cow extends AbstractAnimal{

    private boolean movingForward = true;


    public Cow(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,  well, feeder,
                "file:src/main/resources/images/firstLevel/animals/cowRight.png",
                "file:src/main/resources/images/firstLevel/animals/cowLeft.png",
                "src/main/resources/sound/cow.mp3",
                "file:src/main/resources/images/firstLevel/products/milk.png");
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
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

}
