package org.example.some.animals;

import javafx.scene.layout.AnchorPane;

import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;


public class Pig extends AbstractAnimal{



    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super ( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,
                well, feeder,
                "file:src/main/resources/images/pig.png",
                "src/main/resources/sound/pigSound.mp3",
                "file:src/main/resources/images/pigMeat.png") ;
    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 40;
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
