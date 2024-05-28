package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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

abstract class AbstractAnimal implements Animal {

    ImageView animalView;
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
    Image product;

    AnimalMenu animalMenu;
    public AbstractAnimal(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Wallet wallet, Well well, Feeder feeder, String imagePath, String soundFile, String recourseFile){
        file = new File(soundFile);
        product = new Image(recourseFile);
        this.animalView = new ImageView(new Image(imagePath));
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
        animalView.setFitWidth(100);
        animalView.setFitHeight(100);


        int x = random.nextInt(worldEndX - worldStartX) + worldStartX;
        int y = random.nextInt(worldEndY - worldStartY) + worldStartY;
        animalView.setX(x);
        animalView.setY(y);




        movement();
        thirst();
        hunger();
        giveProduct();
    }

    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(animalView);
        setRandomDirection();
        translateTransition.play();

        translateTransition.setOnFinished(event -> {
            setRandomDirection();
            translateTransition.play();
        });

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    @Override
    public void setRandomDirection() {
        double x = animalView.getX();
        double y = animalView.getY();

        double deltaX = random.nextInt(120) - 75;
        double deltaY = random.nextInt(100) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX || newX > worldEndX - animalView.getFitWidth()) {
            deltaX = -deltaX;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setToX(deltaX);
        translateTransition.setToY(deltaY);
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 20) {

                    ImageView productView = new ImageView(product);
                    productView.setFitWidth(40);
                    productView.setFitHeight(40);
                    productView.setX(animalView.getX() + 30);
                    productView.setY(animalView.getY() + 30);

                    productView.setOnMouseClicked(event -> {
                        wallet.income(8);
                        root.getChildren().remove(productView);
                    });

                    Platform.runLater(() -> root.getChildren().add(1, productView));
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 5 секунд (5000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 10000);
    }

    @Override
    public void sell() {
        wallet.income(cost);
        removeMenu();
        root.getChildren().remove(animalView);
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


    public void playSound() {

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
    public void death() {

    }


    @Override
    public ImageView getAnimalView() {
        return animalView;
    }
}
