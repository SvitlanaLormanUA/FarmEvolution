package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Unicorn extends AbstractAnimal implements AnimalMeat{

    private int productCost;
    public int amountOfMeals;
    private AnimalMeatMenu animalMeatMenu;
    private boolean openedMeatMenu;
    private int puposedAmount = 7;

    public Unicorn(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/UnicornRight.png",
                "file:src/main/resources/images/thirdLevel/animals/UnicornLeft.png",
                "src/main/resources/sound/dragonfly.mp3",
                "file:src/main/resources/images/thirdLevel/products/unicornBlood.png");
        animalView.setFitWidth(170);
        animalView.setFitHeight(140);
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
            deltaX = random.nextInt(70, 200);
        } else {
            deltaX = random.nextInt(-200, -70);
        }
        deltaY = random.nextInt(90) - 45;

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
        if(hungerLvl<100) {
            if(AbstractAnimal.feeder.haveFood()) {
                hungerLvl += 35;
                amountOfMeals++;
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
//        if (FirstLevel.countPig >= 1) {
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
                    AbstractAnimal.root.getChildren().add(productView);
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
        productView.setFitWidth(40);
        productView.setFitHeight(55);
        productView.setLayoutX(x);
        productView.setLayoutY(y);
        productView.setCursor(Cursor.HAND);

        productView.setOnMouseClicked(event -> {
            AbstractAnimal.root.getChildren().remove(productView);
            storage.addUnicornBlood();
        });

        return productView;
    }

    @Override
    public void addMeatMenu(double x, double y) {
        animalMeatMenu = new AnimalMeatMenu(this, x, y, amountOfMeals, puposedAmount);
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

            productCost=1600;

        } else if(amountOfMeals>=puposedAmount*2 && amountOfMeals<puposedAmount*3){

            productCost=3200;

        } else if(amountOfMeals>=puposedAmount*3){

            productCost=4800;

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

    public static void saveAmountOfMeals() {
        /*try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("amountOfMeals.dat"))) {
            oos.writeInt(amountOfMeals);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
