package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.File;
import java.util.Random;

public class Pig extends AbstractAnimal{



    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super ( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, wallet, well, feeder,
                "file:src/main/resources/images/pig.png",
                "src/main/resources/sound/pigSound.mp3",
                "file:src/main/resources/images/meat.png") ;
    }

    @Override
    public void feed() {
        if(AbstractAnimal.hungerLvl<100) {
            hungerLvl += 50;
            AbstractAnimal.cost += 40;
            if (hungerLvl > 100) {
                hungerLvl = 100;
                cost = 70;
            }
            AbstractAnimal.feeder.getFood();
        }

    }

    @Override
    public void giveProduct() {

    }

    @Override
    public void death() {

    }

}
