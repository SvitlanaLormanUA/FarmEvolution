package org.example.some.animals;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

abstract class AbstractAnimal implements Animal {

    public static Pane root;

    protected int cost;
    protected int hungerLvl;
    protected int thirstLvl;
    protected static Feeder feeder;
    protected TranslateTransition translateTransition;
    protected PathTransition pathTransition;

    int worldStartX;
    int worldStartY;
    int worldEndX;
    int worldEndY;
    protected Random random = new Random();

    protected boolean openedMenu;
    private Well well;
    private MediaView mediaView;
    private File file;

    ImageView animalView;
    Media media;
    MediaPlayer mediaPlayer;
    Image product;

    AnimalMenu animalMenu;

    public AbstractAnimal(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, String imagePath, String soundFile, String recourseFile) {
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


        this.feeder = feeder;
        this.mediaView = new MediaView();
        animalView.setFitWidth(100);
        animalView.setFitHeight(100);


        int x = random.nextInt(worldStartX, worldEndX);
        int y = random.nextInt(worldStartY, worldEndY - 100);
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
        playSound();
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
                    cost = (int) (100 * (k / 100));
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
    public void drink() {
        System.out.println(FirstLevel.countCow);
        if (thirstLvl < 100) {
            thirstLvl += 50;
            if (thirstLvl > 100) {
                thirstLvl = 100;
            }
            well.getWater();
        }
    }

    @Override
    public void emotions() {

    }

    @Override
    public void sell() {
        FirstLevel.wallet.income(cost);
        removeMenu();
        root.getChildren().remove(animalView);
        String animalType = this.getClass().getSimpleName();
        FirstLevel.decreaseAnimalCount(animalType);
        mediaPlayer.stop();
        System.out.println(FirstLevel.countCow);
    }

    @Override
    public void death() {
        if (this.hungerLvl == 0 || this.thirstLvl == 0) {
            FirstLevel.wallet.expense(63);
            removeMenu();
            root.getChildren().remove(animalView);
            String animalType = this.getClass().getSimpleName();
            FirstLevel.decreaseAnimalCount(animalType);
            mediaPlayer.stop();
        }
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
    public ImageView getAnimalView() {
        return animalView;
    }

}


