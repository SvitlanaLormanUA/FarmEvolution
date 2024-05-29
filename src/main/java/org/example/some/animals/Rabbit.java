package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;


public class Rabbit extends AbstractAnimal{

    private double deltaX;
    private double deltaY;

    public Rabbit(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Wallet wallet, Well well, Feeder feeder) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder,
                "file:src/main/resources/images/rabbit.png",
                "src/main/resources/sound/sheepmp3.mp3",
                "file:src/main/resources/images/rabbitMeat.png");
        animalView.setFitWidth(80);
        animalView.setFitHeight(100);
    }

    @Override
    public void movement() {
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1500));
        pathTransition.setNode(animalView);
        deltaX = 0;
        deltaY = 0;
        setRandomDirection();
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            setRandomDirection();
            pathTransition.play();
        });

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getX();
        double y = animalView.getY();

        Path path = new Path();
        path.getElements().add(new MoveTo(x+deltaX, y+deltaY));

        deltaX = random.nextInt(120) - 75;
        deltaY = random.nextInt(100) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX || newX > worldEndX - animalView.getFitWidth()) {
            deltaX = -deltaX;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }
        path.getElements().add(new QuadCurveTo(x + deltaX / 2, y - 150, x + deltaX, y + deltaY));

        pathTransition.setPath(path);
        pathTransition.setOrientation(OrientationType.NONE);
    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        pathTransition.pause();
        double newX = event.getSceneX() - animalView.getFitWidth() / 2;
        double newY = event.getSceneY() - animalView.getFitHeight() / 2;

        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - animalView.getFitWidth()) newX = worldEndX - animalView.getFitWidth();
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - animalView.getFitHeight()) newY = worldEndY - animalView.getFitHeight();

        animalView.setX(newX);
        animalView.setY(newY);
        playSound();
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = animalView.getX();
        double y = animalView.getY();
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
            pathTransition.pause();
            addMenu(x, y);

        } else {
            removeMenu();
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
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 20;
            }
            AbstractAnimal.feeder.getFood();
        }
    }

    @Override
    public void giveProduct() {

    }
}
