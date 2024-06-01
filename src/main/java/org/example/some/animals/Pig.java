package org.example.some.animals;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.util.ArrayList;
import java.util.List;


public class Pig extends AbstractAnimal implements AnimalMeat{

    private int productCost;
    private int amountOfMeals;
    private AnimalMeatMenu animalMeatMenu;
    private boolean openedMeatMenu;
    private boolean enoughFood;
    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super ( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,
                well, feeder,
                "file:src/main/resources/images/firstLevel/animals/pig.png",
                "src/main/resources/sound/pigSound.mp3",
                "file:src/main/resources/images/firstLevel/products/meat.png") ;
        this.amountOfMeals = 0;
        this.productCost = 0;
        this.openedMeatMenu = false;
        this.enoughFood = false;
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {
        super.handleMouseClicked(event);

        if(amountOfMeals>=5){
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
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 40;
            amountOfMeals++;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 70;
            }
            AbstractAnimal.feeder.getFood();
        }
    }


    @Override
    public void giveProduct() {
        if (FirstLevel.countGoose >= 1) {
            List<ImageView> productViews = new ArrayList<>();

            if (amountOfMeals >= 5) {
                ImageView productView1 = createProductView(animalView.getX() + 50, animalView.getY() + 30);
                productViews.add(productView1);
            }
            if (amountOfMeals >= 10) {
                ImageView productView2 = createProductView(animalView.getX() - 50, animalView.getY() - 30);
                productViews.add(productView2);
            }
            if (amountOfMeals >= 15) {
                ImageView productView3 = createProductView(animalView.getX(), animalView.getY() + 20);
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
        productView.setFitWidth(50);
        productView.setFitHeight(50);
        productView.setX(x);
        productView.setY(y);
        productView.setCursor(Cursor.HAND);

        productView.setOnMouseClicked(event -> {
            AbstractAnimal.root.getChildren().remove(productView);
            FirstLevel.wallet.income(80);
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
        if(amountOfMeals>=5 && amountOfMeals<10){

            productCost=80;

        } else if(amountOfMeals>=10 && amountOfMeals<15){

            productCost=160;

        } else if(amountOfMeals>=15){

            productCost=240;

        }

        return productCost;
    }
}
