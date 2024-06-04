package org.example.some.animals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.SecondLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

import static org.example.some.SecondLevel.wallet;

public class Monkey extends AbstractAnimal{
    public  ImageView productView;
    private boolean movingForward = true;

    private boolean nearLian = false;
    private static KeyFrame kf;
    private static   KeyValue kv;


    public Monkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/monkey.png",
                "src/main/resources/sound/monkey.mp3",
                "file:src/main/resources/images/secondLevel/bananaThoughts.png"
        ) ;

        kv = new KeyValue(animalView.layoutXProperty(), 30);
        kf = new KeyFrame(Duration.millis(500), kv);
    }

    @Override
    public void feed() {

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
            if (imagePathLeft!= null) {
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

        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
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
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(animalView);
        directionRight = false;

        translateTransition.setOnFinished(event -> {
            Timeline timeline = new Timeline();

            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                animalView.setTranslateX(0);
                animalView.setTranslateY(0);
                nearLian = true;
            });

            timeline.play();
        });

        translateTransition.play();
    }
    public void goUp() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(animalView);
        directionRight = false;

        translateTransition.setOnFinished(event -> {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                animalView.setTranslateX(0);
                animalView.setTranslateY(0);
                nearLian = true;
            });

            timeline.play();
        });

        translateTransition.play();
    }
}