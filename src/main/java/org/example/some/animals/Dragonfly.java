package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Dragonfly extends AbstractAnimal implements AnimalMeat{

    private int productCost;
    private int amountOfMeals;
    private AnimalMeatMenu animalMeatMenu;
    private boolean openedMeatMenu;

    public Dragonfly(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/dragonflyRight.png",
                "file:src/main/resources/images/secondLevel/animals/dragonflyLeft.png",
                "src/main/resources/sound/monkey.mp3",
                "file:src/main/resources/images/secondLevel/bananaThoughts.png"
        );
        animalView.setFitWidth(70);
        animalView.setFitHeight(50);
        this.amountOfMeals = 0;
        this.productCost = 0;
        this.openedMeatMenu = false;
        giveProduct();
    }

    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
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

    @Override
    public void handleMouseClicked(MouseEvent event) {
        super.handleMouseClicked(event);

        if(!openedMeatMenu && openedMenu){
            double x = event.getSceneX();
            double y = event.getSceneY();

            double menuWidth = 180;
            double menuHeight = 100;
            if (x + menuWidth > root.getWidth()) {
                x = root.getWidth() - menuWidth;
            }
            if (y + menuHeight > root.getHeight()) {
                y = root.getHeight() - menuHeight;
            }

            addMeatMenu(x, y);
        }
    }

    @Override
    public void feed() {
        /*if(hungerLvl<100) {
            hungerLvl += 50;
            amountOfMeals++;
            if(amountOfMeals<7) {
                animalMeatMenu.getFeed().setText("Нагодовано: " + amountOfMeals + "/" + 7);
            } else {
                animalMeatMenu.update();
            }
            if (hungerLvl > 100) {
                hungerLvl = 100;
            }
            AbstractAnimal.feeder.getFood();
        }*/
    }


    @Override
    public void giveProduct() {
//        if (FirstLevel.countGoose >= 1) {
            List<ImageView> productViews = new ArrayList<>();

            if (amountOfMeals >= 7) {
                ImageView productView1 = createProductView(animalView.getLayoutX() + 50, animalView.getLayoutY() + 30);
                productViews.add(productView1);
            }

            Platform.runLater(() -> {
                for (ImageView productView : productViews) {
                    AbstractAnimal.root.getChildren().add(1, productView);
                }
            });

            if(openedMenu) {
                removeMenu();
            }
            root.getChildren().remove(this.animalView);
//        }
    }

    private ImageView createProductView(double x, double y) {
        ImageView productView = new ImageView(product);
        productView.setFitWidth(50);
        productView.setFitHeight(50);
        productView.setLayoutX(x);
        productView.setLayoutY(y);
        productView.setCursor(Cursor.HAND);

        productView.setOnMouseClicked(event -> {
            AbstractAnimal.root.getChildren().remove(productView);
//            storage.addProduct4();
        });

        return productView;
    }

    @Override
    public void addMeatMenu(double x, double y) {
        animalMeatMenu = new AnimalMeatMenu(this, x, y, amountOfMeals, 7);
        root.getChildren().add(animalMeatMenu.getRoot());
        openedMeatMenu = true;
    }

    @Override
    public void removeMenu() {
        root.getChildren().remove(animalMenu.getRoot());
        openedMenu = false;
        removeMeatMenu();
        translateTransition.play();
    }

    @Override
    public void removeMeatMenu() {
        root.getChildren().remove(animalMeatMenu.getRoot());
        openedMeatMenu = false;
    }


    @Override
    public int getProductCost() {
        if(amountOfMeals>=7 && amountOfMeals<14){

            productCost=140;

        } else if(amountOfMeals>=14 && amountOfMeals<21){

            productCost=280;

        } else if(amountOfMeals>=21){

            productCost=420;

        }

        return productCost;
    }


    @Override
    public void hunger(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (350 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }
}
