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
    int amountOfMeals;
    private MilkEmotion milkEmotion;

    public Cow(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder,
                "file:src/main/resources/images/firstLevel/animals/cowRight.png",
                "file:src/main/resources/images/firstLevel/animals/cowLeft.png",
                "src/main/resources/sound/cow.mp3",
                "file:src/main/resources/images/firstLevel/products/milk.png"
        );
        //this.amountOfMeals = 0;
        giveProduct();

    }

    public  void addProduct() {
        ImageView productView = new ImageView(product);
        productView.setFitWidth(40);
        productView.setFitHeight(40);
        productView.setX(animalView.getLayoutX() + 30);
        productView.setY(animalView.getLayoutY() + 30);
        productView.setCursor(javafx.scene.Cursor.HAND);
        productView.setOnMouseClicked(event -> {
            FirstLevel.wallet.income(15);
            AbstractAnimal.root.getChildren().remove(productView);
        });
        Platform.runLater(() -> AbstractAnimal.root.getChildren().add(1, productView));
    }

    @Override
    public void feed() {
        if (hungerLvl < 100) {
            hungerLvl += 50;
            cost += 50;
            amountOfMeals++;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = hungerLvl;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
        if (FirstLevel.countCow >= 1) {
            if (amountOfMeals > 3) {
                double x = animalView.getLayoutX() + animalView.getFitWidth();
                double y = animalView.getLayoutY() + 10;
                milkEmotion = new MilkEmotion(this, x, y);
                milkEmotion.addEmotion();
                amountOfMeals = 0;
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

}