package org.example.some.animals;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Cow extends AbstractAnimal {

    private boolean movingForward = true;

    private boolean hasProduct = true;
    private boolean emotionIsShown = false;

    ImageView productView;

    public Cow(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/firstLevel/animals/cowRight.png",
                "file:src/main/resources/images/firstLevel/animals/cowLeft.png",
                "src/main/resources/sound/cow.mp3",
                "file:src/main/resources/images/fullReaction.png"
        );

        if(hasProduct){
            giveProduct();
        }
        animalView.setFitWidth(130);
        animalView.setFitHeight(120);
    }

    @Override
    public void feed() {
        if (hungerLvl < 100) {
            if(AbstractAnimal.feeder.haveFood()) {
                hungerLvl += 50;
            }

            if (hungerLvl > 100) {
                hungerLvl = 100;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
        if (FirstLevel.countSheep >= 1) {
            if (hungerLvl > 20 && !emotionIsShown) {
                // Створення таймера (завдання, яке виконується через певний час
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (hungerLvl > 20) {
                            Platform.runLater(() -> {
                                if (productView == null) {
                                    productView = new ImageView(product);
                                    productView.setId("product");
                                    productView.setFitWidth(50);
                                    productView.setFitHeight(50);
                                    productView.setCursor(Cursor.HAND);

                                    // Set initial position
                                    updateProductViewPosition();

                                    // Set mouse click handler
                                    productView.setOnMouseClicked(event -> {
                                        AbstractAnimal.root.getChildren().remove(productView);
                                        storage.addMilk();
                                        productView = null; // Reset the product view
                                    });

                                    AbstractAnimal.root.getChildren().add(1, productView);
                                } else {
                                    updateProductViewPosition(); // Update position if product already exists
                                }
                            });
                        } else {
                            timer.cancel();
                        }
                    }
                };

                // Запуск завдання з інтервалом 35 секунд (70000 мілісекунд)
                timer.scheduleAtFixedRate(task, 0, 70000);
            }
        }
    }

    private void updateProductViewPosition() {
        if (productView != null) {
            if(movingForward){
                productView.setX(animalView.getLayoutX() + animalView.getFitWidth());
                productView.setY(animalView.getLayoutY()-15);
            } else {
                productView.setX(animalView.getLayoutX()+50);
                productView.setY(animalView.getLayoutY()-35);
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
    }

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (500 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 4000);
    }
}
