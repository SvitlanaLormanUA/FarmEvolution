package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.some.Wallet;
import org.example.some.Well;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Sheep implements Animal {

    ImageView sheepView;
    int worldStartX;
    int worldStartY;
    int worldEndX;
    int worldEndY;
    private TranslateTransition translateTransition;
    private Random random = new Random();
    private Pane root;
    private boolean openedMenu;
    private int hungerLvl;
    private int thirstLvl;
    private int cost;
    private Well well;
    private Wallet wallet;
    private Feeder feeder;

    AnimalMenu animalMenu;
    public Sheep(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Wallet wallet, Well well, Feeder feeder){
        Image image = new Image("file:src/main/resources/images/sheep.png");
        this.sheepView = new ImageView(image);
        this.worldStartX = worldStartX;
        this.worldStartY = worldStartY;
        this.worldEndX = worldEndX;
        this.worldEndY = worldEndY;
        this.root = root;
        this.openedMenu = false;
        this.thirstLvl = 100;
        this.hungerLvl = 100;
        this.cost = 100;
        this.well = well;
        this.wallet = wallet;
        this.feeder = feeder;
        sheepView.setFitWidth(100);
        sheepView.setFitHeight(100);

        int x = random.nextInt(worldEndX - worldStartX) + worldStartX;
        int y = random.nextInt(worldEndY - worldStartY) + worldStartY;
        sheepView.setX(x);
        sheepView.setY(y);

        movement();
        thirst();
        hunger();
    }

    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(sheepView);
        setRandomDirection();

        translateTransition.setCycleCount(1);
        translateTransition.setOnFinished(event -> {
            setRandomDirection();
            translateTransition.playFromStart();
        });

        sheepView.setOnMouseClicked(this::handleMouseClicked);
        sheepView.setOnMouseDragged(this::handleMouseDragged);
        sheepView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = sheepView.getX();
        double y = sheepView.getY();

        double deltaX = random.nextInt(150) - 75; // Рандомне переміщення по X в діапазоні [-100, 100]
        double deltaY = random.nextInt(120) - 60; // Рандомне переміщення по Y в діапазоні [-100, 100]

        if (x + deltaX < worldStartX || x + deltaX > worldEndX - 100) { // Враховуємо ширину зображення
            deltaX = -deltaX;
        }
        if (y + deltaY < worldStartY || y + deltaY > worldEndY - 100) { // Враховуємо висоту зображення
            deltaY = -deltaY;
        }

        translateTransition.setToX(deltaX);
        translateTransition.setToY(deltaY);
    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        double newX = event.getX() - 50;
        double newY = event.getY() - 50;

        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - 100) newX = worldEndX - 100;
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - 100) newY = worldEndY - 100;

        sheepView.setX(newX);
        sheepView.setY(newY);
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = sheepView.getX();
        double y = sheepView.getY();
        boolean outOfBounds = false;

        if (x < worldStartX) {
            x = worldStartX;
            outOfBounds = true;
        } else if (x > worldEndX - 100) {
            x = worldEndX - 100;
            outOfBounds = true;
        }

        if (y < worldStartY) {
            y = worldStartY;
            outOfBounds = true;
        } else if (y > worldEndY - 100) {
            y = worldEndY - 100;
            outOfBounds = true;
        }

        if (outOfBounds) {
            TranslateTransition transitionBack = new TranslateTransition(Duration.millis(500), sheepView);
            transitionBack.setToX(x);
            transitionBack.setToY(y);
            transitionBack.play();
        }
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

            animalMenu = new AnimalMenu(this, x, y);
            root.getChildren().add(animalMenu.getRoot());
            translateTransition.pause();
            openedMenu = true;
        } else {
            removeMenu();
        }
    }

    @Override
    public void removeMenu() {
        root.getChildren().remove(animalMenu.getRoot());
        openedMenu = false;
        translateTransition.play();
    }

    @Override
    public void play() {
        translateTransition.play();
    }

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    double k = hungerLvl;
                    cost = (int) (100 * (k/100));
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 5 секунд (5000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    @Override
    public void thirst() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (thirstLvl > 0) {
                    thirstLvl--;
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 5 секунд (5000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 50;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 100;
            }
            wallet.expense(7);
        }
    }

    @Override
    public void drink() {
        if (thirstLvl<100) {
            thirstLvl += 50;
            if (thirstLvl > 100) {
                thirstLvl = 100;
            }
            well.getWater();
        }
    }

    @Override
    public void giveProduct() {

    }

    @Override
    public void sell() {

    }

    @Override
    public int getHungerLvl() {
        return hungerLvl;
    }

    @Override
    public int getThirstLvl() {
        return thirstLvl;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public ImageView getSheepView() {
        return sheepView;
    }
}
