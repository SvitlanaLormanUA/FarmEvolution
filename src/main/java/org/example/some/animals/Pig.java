package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.File;
import java.util.Random;

public class Pig implements Animal{
    ImageView pigView;
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

    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Wallet wallet, Well well, Feeder feeder){


        Image image = new Image("file:src/main/resources/images/pig.png");
        this.pigView = new ImageView(image);
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


        pigView.setFitWidth(100);
        pigView.setFitHeight(100);


        int x = random.nextInt(worldEndX - worldStartX) + worldStartX;
        int y = random.nextInt(worldEndY - worldStartY) + worldStartY;
        pigView.setX(x);
        pigView.setY(y);



    }

    @Override
    public void movement() {

    }

    @Override
    public void setRandomDirection() {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {

    }

    @Override
    public void handleMouseReleased(MouseEvent event) {

    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void addMenu(double x, double y) {

    }

    @Override
    public void removeMenu() {

    }

    @Override
    public void play() {
        translateTransition.play();

    }

    @Override
    public void hunger() {

    }

    @Override
    public void thirst() {

    }

    @Override
    public void feed() {

    }

    @Override
    public void drink() {

    }

    @Override
    public void giveProduct() {

    }

    @Override
    public void sell() {

    }

    @Override
    public int getHungerLvl() {
        return 0;
    }

    @Override
    public int getThirstLvl() {
        return 0;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public void playSound() {

    }

    @Override
    public void death() {

    }

    public ImageView getAnimalView() {
        return pigView;
    }
}
