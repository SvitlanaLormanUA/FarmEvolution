package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.File;
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
    private MediaView mediaView;
    File file;
    Media media;
    MediaPlayer mediaPlayer;

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
        this.mediaView = new MediaView();
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

        translateTransition.setOnFinished(event -> {
            setRandomDirection();
            translateTransition.play();
        });

        sheepView.setOnMouseClicked(this::handleMouseClicked);
        sheepView.setOnMouseDragged(this::handleMouseDragged);
        sheepView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = sheepView.getX();
        double y = sheepView.getY();

        double deltaX = random.nextInt(120) - 75;
        double deltaY = random.nextInt(100) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX || newX > worldEndX - sheepView.getFitWidth()) {
            deltaX = -deltaX;
        }
        if (newY < worldStartY || newY > worldEndY - sheepView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setToX(deltaX);
        translateTransition.setToY(deltaY);
    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        translateTransition.pause();
        double newX = event.getSceneX() - sheepView.getFitWidth() / 2;
        double newY = event.getSceneY() - sheepView.getFitHeight() / 2;

        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - sheepView.getFitWidth()) newX = worldEndX - sheepView.getFitWidth();
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - sheepView.getFitHeight()) newY = worldEndY - sheepView.getFitHeight();

        sheepView.setX(newX);
        sheepView.setY(newY);
        playSound();
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = sheepView.getX();
        double y = sheepView.getY();
        boolean outOfBounds = false;

        if (x < worldStartX) {
            x = worldStartX;
            outOfBounds = true;
        } else if (x > worldEndX - sheepView.getFitWidth()) {
            x = worldEndX - sheepView.getFitWidth();
            outOfBounds = true;
        }

        if (y < worldStartY) {
            y = worldStartY;
            outOfBounds = true;
        } else if (y > worldEndY - sheepView.getFitHeight()) {
            y = worldEndY - sheepView.getFitHeight();
            outOfBounds = true;
        }

        if (outOfBounds) {
            TranslateTransition transitionBack = new TranslateTransition(Duration.millis(500), sheepView);
            transitionBack.setToX(x);
            transitionBack.setToY(y);
            transitionBack.play();
        }
        translateTransition.play();
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
        translateTransition.pause();
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
            feeder.getFood();
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
        wallet.income(cost);
        removeMenu();
        root.getChildren().remove(sheepView);
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
    public void playSound() {
        file = new File("src/main/resources/sound/sheepmp3.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.33);
        root.getChildren().add(mediaView);
        mediaPlayer.play();

        root.getChildren().remove(mediaView);
    }


    @Override
    public ImageView getAnimalView() {
        return sheepView;
    }
}
