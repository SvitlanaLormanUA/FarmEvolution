package org.example.some.animals;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Rabbit extends AbstractAnimal implements AnimalMeat{

    private int productCost;
    private int amountOfMeals;
    private AnimalMeatMenu animalMeatMenu;
    private boolean openedMeatMenu;
    private boolean enoughFood;
    private Path path;
    private double currX;
    private double currY;

    public Rabbit(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/firstLevel/animals/rabbitRight.png",
                "file:src/main/resources/images/firstLevel/animals/rabbitLeft.png",
                "src/main/resources/sound/jumpRabbit.mp3",
                "file:src/main/resources/images/firstLevel/products/meat.png"
        );
        this.amountOfMeals = 0;
        this.productCost = 0;
        this.openedMeatMenu = false;
        this.enoughFood = false;
        animalView.setFitWidth(80);
    }


    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(600));
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
    public void handleMouseClicked(MouseEvent event) {
        super.handleMouseClicked(event);

        if(amountOfMeals>=3){
            enoughFood = true;
        }

        if(!openedMeatMenu && enoughFood){
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

        playSound();
    }

    @Override
    public void play() {
        translateTransition.play();
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 100;
            cost += 20;
            amountOfMeals++;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 20;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {
        if (FirstLevel.countRabbit >= 1) {
            List<ImageView> productViews = new ArrayList<>();

            if (amountOfMeals >= 3) {
                ImageView productView1 = createProductView(animalView.getLayoutX() + 50, animalView.getLayoutY() + 30);
                productViews.add(productView1);
            }
            if (amountOfMeals >= 6) {
                ImageView productView2 = createProductView(animalView.getLayoutX() - 50, animalView.getLayoutY() - 30);
                productViews.add(productView2);
            }
            if (amountOfMeals >= 9) {
                ImageView productView3 = createProductView(animalView.getLayoutX(), animalView.getLayoutY() + 20);
                productViews.add(productView3);
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
        }
    }

    private ImageView createProductView(double x, double y) {
        ImageView productView = new ImageView(product);
        productView.setFitWidth(30);
        productView.setFitHeight(30);
        productView.setLayoutX(x);
        productView.setLayoutY(y);
        productView.setCursor(Cursor.HAND);

        productView.setOnMouseClicked(event -> {
            AbstractAnimal.root.getChildren().remove(productView);
            storage.addProduct5();
        });

        return productView;
    }

    @Override
    public void addMeatMenu(double x, double y) {
        animalMeatMenu = new AnimalMeatMenu(this, x, y);
        root.getChildren().add(animalMeatMenu.getRoot());
        openedMeatMenu = true;
    }

    @Override
    public void removeMeatMenu() {
        root.getChildren().remove(animalMeatMenu.getRoot());
        openedMeatMenu = false;
    }


    @Override
    public int getProductCost() {
        if(amountOfMeals>=3 && amountOfMeals<6){

            productCost=40;

        } else if(amountOfMeals>=6 && amountOfMeals<9){

            productCost=80;

        } else if(amountOfMeals>=9){

            productCost=120;

        }

        return productCost;
    }


}
