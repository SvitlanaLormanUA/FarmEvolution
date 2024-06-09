package org.example.some.animals;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.SecondLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

import static org.example.some.SecondLevel.wallet;

public class Monkey extends AbstractAnimal {
    public ImageView productView;
    private boolean movingForward = true;

    private static KeyFrame kf;
    private static KeyValue kv;

    public Monkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/monkey.png",
                "src/main/resources/sound/monkey.mp3",
                "file:src/main/resources/images/secondLevel/bananaThoughts.png"
        );
    }

    @Override
    public void feed() {
        // Implement the feed method
    }

    @Override
    public void giveProduct() {
        if (SecondLevel.bananaIsAdded) {
            System.out.println("giveProduct method called");
            productView = new ImageView(product);
            productView.setFitWidth(60);
            productView.setFitHeight(50);
            productView.setCursor(Cursor.HAND);

            AbstractAnimal.root.getChildren().add(1, productView);

            productView.setOnMouseClicked(event -> {
                AbstractAnimal.root.getChildren().remove(productView);
                SecondLevel.productIsAdded = false;

                goForBanana();
            });
        }
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();

        deltaX = 2; // Small step forward
        deltaY = random.nextInt(10) - 5; // Slight variation in y-direction

        if (movingForward && (x + deltaX > worldEndX - animalView.getFitWidth())) {
            if (imagePathLeft != null) {
                animalView.setImage(new Image(imagePathLeft));
            }
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

        if (newY < worldStartY || newY > worldEndY - 2*animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);

        updateProductViewPosition();
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

        updateProductViewPosition();
        playSound();
    }

    public void updateProductViewPosition() {
        if (productView != null) {
            productView.setX(animalView.getLayoutX() + animalView.getFitWidth());
            productView.setY(animalView.getLayoutY() + 10);
        }
    }

    public void goForBanana() {
        animalView.setDisable(true);

        if (translateTransition != null) {
            translateTransition.stop();
        }

        double currentX = animalView.getLayoutX();
        double distance = 750 - currentX;
        if (distance <= 0) {
            goUp();
            return;
        }

        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(distance * 10)); // Adjust duration for smoothness
        translateTransition.setNode(animalView);
        translateTransition.setByX(distance); // Move right until x reaches 677

        translateTransition.setOnFinished(event -> {
            goUp();
        });

        translateTransition.play();
    }

    public void goUp() {
        if (translateTransition != null) {
            translateTransition.stop();
        }

        double currentY = animalView.getLayoutY();
        double distance = (-1) * (350 - currentY);
        if (distance <= 0) {
            goUp();
            return;
        }

        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(distance * 10)); // Adjust duration for smoothness
        translateTransition.setNode(animalView);
        translateTransition.setByY(-distance); // Move right until x reaches 677
        translateTransition.setOnFinished(event -> {
            takeBananaDown();
        });
        translateTransition.play();




    }

    private void takeBananaDown() {
        if (translateTransition != null) {
            translateTransition.stop();
        }

        double currentY = animalView.getLayoutY();
        double distance = 650 - currentY;

        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(distance * 10)); // Adjust duration for smoothness
        translateTransition.setNode(animalView);
        translateTransition.setByY(distance); // Move right until x reaches 677
        translateTransition.setOnFinished(event -> {
            // Set disable to false and reset flags
            animalView.setDisable(false);
            SecondLevel.bananaIsAdded = false;
            SecondLevel.productIsAdded = false;

            // Check if bananas are present and animate if necessary
            if (!(SecondLevel.bananas == null)) {
                SecondLevel.bananas.forEach(banana -> {
                    KeyValue kvY = new KeyValue(banana.getProductView().layoutYProperty(), 600);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kvY);
                    Timeline timeline = new Timeline(kf);
                    timeline.play();
                });
            }

            // Set additional translation for smooth leftward movement
            KeyValue kvX = new KeyValue(animalView.translateXProperty(), -200);
            //KeyValue kvY2 = new KeyValue(animalView.translateYProperty(), currentY - 450);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kvX);
            Timeline timeline2 = new Timeline(kf2);
            timeline2.play();
            SecondLevel.countBanana = 0;
            movement(); // Assuming this is your movement method

        });
        translateTransition.play();
    }


}
