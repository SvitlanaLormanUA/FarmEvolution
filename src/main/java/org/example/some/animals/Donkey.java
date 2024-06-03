package org.example.some.animals;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

public class Donkey extends AbstractAnimal{



    public Donkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane,  Well well, Feeder feeder) {
        super( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,  well, feeder,
                "file:src/main/resources/images/firstLevel/animals/DonkeyRight.png",
                "file:src/main/resources/images/firstLevel/animals/DonkeyLeft.png",
                "src/main/resources/sound/donkey.mp3"
        ) ;
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 50;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 100;
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

        // Ensure primarily right-left direction with faster speed
        deltaX = random.nextInt(200) - 100; // Larger variation in x-direction

        if (directionRight) {
            deltaX = random.nextInt(0, 100);
        } else {
            deltaX = random.nextInt(-100, 0);
        }
        deltaY = random.nextInt(20) - 10;   // Small variation in y-direction

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX) {
            deltaX = -deltaX;
            animalView.setImage(new Image(imagePath));
            directionRight = !directionRight;
        } else if (newX > worldEndX) {
            deltaX = -deltaX;
            animalView.setImage(new Image(imagePathLeft));
            directionRight = !directionRight;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);
    }
}
