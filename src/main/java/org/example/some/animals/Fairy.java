package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Fairy extends AbstractAnimal{


    public Fairy(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super( worldStartX,  worldStartY,  worldEndX,  worldEndY,  anchorPane,  well,  feeder,  storage,
                "file:src/main/resources/images/thirdLevel/animals/fairyLeft.png",
                "file:src/main/resources/images/thirdLevel/animals/fairy.png",
                "src/main/resources/sound/magicFairy.mp3",
                "file:src/main/resources/images/thirdLevel/products/fairyDust.png"
        );
        animalView.setFitHeight(110);
        animalView.setFitWidth(80);
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
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
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();


        if (directionRight) {
            deltaX = random.nextInt(100, 250);
        } else {
            deltaX = random.nextInt(-250, -100);
        }
        deltaY = random.nextInt(120) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX && !directionRight) {
            deltaX = -deltaX;
            animalView.setImage(new Image(imagePath));
            directionRight = !directionRight;
        } else if (newX > worldEndX && directionRight) {
            deltaX = -deltaX;
            if (imagePathLeft != null) {
                animalView.setImage(new Image(imagePathLeft));
            }
            directionRight = !directionRight;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);
    }
}
