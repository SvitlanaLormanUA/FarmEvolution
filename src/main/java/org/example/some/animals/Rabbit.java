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

    public Rabbit(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder,
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
        animalView.setLayoutX(300);
        animalView.setLayoutY(300);

        currX = animalView.getLayoutX();
        currY = animalView.getLayoutY();
    }


    @Override
    public void movement() {
        directionRight = true;
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1500));
        pathTransition.setNode(animalView);

        setRandomDirection();

        pathTransition.setOnFinished(event -> {
            animalView.setLayoutX(currX);
            animalView.setLayoutY(currY);

            // Скидання translateX і translateY перед встановленням нової траєкторії
            animalView.setTranslateX(0);
            animalView.setTranslateY(0);

            // Встановлення нової траєкторії
            setRandomDirection();

            // Створення нової PathTransition для перезапуску анімації
            PathTransition newPathTransition = new PathTransition();
            newPathTransition.setDuration(Duration.millis(1500));
            newPathTransition.setNode(animalView);
            newPathTransition.setPath(path);
            newPathTransition.setOrientation(PathTransition.OrientationType.NONE);
            newPathTransition.setOnFinished(this.pathTransition.getOnFinished());
            newPathTransition.play();

            // Оновлення посилання на поточну PathTransition
            this.pathTransition = newPathTransition;
        });

        pathTransition.play();

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();

        path = new Path();
        path.getElements().add(new MoveTo(x, y));

        if (directionRight) {
            deltaX = random.nextInt(10, 50);
        } else {
            deltaX = random.nextInt(-50, -10);
        }
        deltaY = random.nextInt(120) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX || newX > worldEndX) {
            deltaX = -deltaX;
            directionRight = !directionRight;
            animalView.setImage(new Image(directionRight ? imagePath : imagePathLeft));
        }

        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        currX = x + deltaX;
        currY = y + deltaY;

        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setX(currX);
        quadCurveTo.setY(currY);
        quadCurveTo.setControlX(x + deltaX / 2);
        quadCurveTo.setControlY(y - 75);

        path.getElements().add(quadCurveTo);

        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
    }


    @Override
    public void handleMouseDragged(MouseEvent event) {
        pathTransition.stop();
        double newX = event.getSceneX() - animalView.getFitWidth() / 2;
        double newY = event.getSceneY() - animalView.getFitHeight() / 2;

        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - animalView.getFitWidth()) newX = worldEndX - animalView.getFitWidth();
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - animalView.getFitHeight()) newY = worldEndY - animalView.getFitHeight();

        animalView.setLayoutX(newX);
        animalView.setLayoutY(newY);
        playSound();
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();
        boolean outOfBounds = false;

        if (x < worldStartX) {
            x = worldStartX;
            outOfBounds = true;
        } else if (x > worldEndX - animalView.getFitWidth()) {
            x = worldEndX - animalView.getFitWidth();
            outOfBounds = true;
        }

        if (y < worldStartY) {
            y = worldStartY;
            outOfBounds = true;
        } else if (y > worldEndY - animalView.getFitHeight()) {
            y = worldEndY - animalView.getFitHeight();
            outOfBounds = true;
        }

        if (outOfBounds) {
            TranslateTransition transitionBack = new TranslateTransition(Duration.millis(500), animalView);
            transitionBack.setToX(x);
            transitionBack.setToY(y);
            transitionBack.play();
        }
        movement();
        playSound();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {
        if (!openedMenu) {
            double x = event.getSceneX();
            double y = event.getSceneY();

            // Перевірка, щоб меню не виходило за межі вікна
            double menuWidth = 200;
            double menuHeight = 200;
            if (x + menuWidth > root.getWidth()) {
                x = root.getWidth() - menuWidth;
            }
            if (y + menuHeight > root.getHeight()) {
                y = root.getHeight() - menuHeight;
            }
            pathTransition.stop();
            addMenu(x, y);

        } else {
            removeMenu();
        }

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
    public void addMenu(double x, double y) {
        animalMenu = new AnimalMenu(this, x, y);
        root.getChildren().add(animalMenu.getRoot());
        openedMenu = true;
//        pathTransition.pause();
    }

    @Override
    public void removeMenu() {
        root.getChildren().remove(animalMenu.getRoot());
        openedMenu = false;
        pathTransition.play();
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
            FirstLevel.wallet.income(40);
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
