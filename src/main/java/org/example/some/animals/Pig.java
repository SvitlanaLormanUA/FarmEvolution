package org.example.some.animals;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.example.some.FirstLevel;
import org.example.some.SecondLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.example.some.SettingsMenu.restart;


public class Pig extends AbstractAnimal implements AnimalMeat{

    public static int amountOfMeals;
    private int productCost;
    private AnimalMeatMenu animalMeatMenu;
    private boolean openedMeatMenu;
    private int puposedAmount = 5;
    private boolean enoughFood;
    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/firstLevel/animals/pigRight.png",
                "file:src/main/resources/images/firstLevel/animals/pigLeft.png",
                "src/main/resources/sound/pigSound.mp3",
                "file:src/main/resources/images/firstLevel/products/meat.png"
        ) ;
       FirstLevel.loadState();
        this.productCost = 0;
        this.openedMeatMenu = false;
        this.enoughFood = false;
        giveProduct();

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
        if(hungerLvl<100) {
            if(AbstractAnimal.feeder.haveFood()) {
                hungerLvl += 50;
                amountOfMeals++;
                SecondLevel.saveState();
            }
            if(amountOfMeals<puposedAmount) {
                animalMeatMenu.getFeed().setText("Нагодовано: " + amountOfMeals + "/" + puposedAmount);
            } else {
                animalMeatMenu.update();
            }
            if (hungerLvl > 100) {
                hungerLvl = 100;
            }
            AbstractAnimal.feeder.getFood();
        }
    }


    @Override
    public void giveProduct() {
        if (FirstLevel.countPig >= 1) {
            List<ImageView> productViews = new ArrayList<>();

            if (amountOfMeals >= puposedAmount) {
                ImageView productView1 = createProductView(animalView.getLayoutX() + 50, animalView.getLayoutY() + 30);
                productViews.add(productView1);

            }
            if (amountOfMeals >= puposedAmount*2) {
                ImageView productView2 = createProductView(animalView.getLayoutX() - 50, animalView.getLayoutY() - 30);
                productViews.add(productView2);

            }
            if (amountOfMeals >= puposedAmount*3) {
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
            FirstLevel.countPig--;
        }
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
            storage.addPigMeat();
        });

        return productView;
    }

    @Override
    public void addMeatMenu(double x, double y) {
        animalMeatMenu = new AnimalMeatMenu(this, x, y, amountOfMeals, 5);
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
        if(amountOfMeals>=puposedAmount && amountOfMeals<puposedAmount*2){

            productCost=80;

        } else if(amountOfMeals>=puposedAmount*2 && amountOfMeals<puposedAmount*3){

            productCost=160;

        } else if(amountOfMeals>=puposedAmount*3){

            productCost=240;

        }

        return productCost;
    }

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (265 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }
}
