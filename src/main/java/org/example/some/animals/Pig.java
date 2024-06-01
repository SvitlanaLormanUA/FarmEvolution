package org.example.some.animals;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;


public class Pig extends AbstractAnimal{


    private int amountOfMeals;
    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super ( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,
                well, feeder,
                "file:src/main/resources/images/firstLevel/animals/pig.png",
                "src/main/resources/sound/pigSound.mp3",
                "file:src/main/resources/images/pigMeat.png") ;
        this.amountOfMeals = 0;
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {
        super.handleMouseClicked(event);


    }

    @Override
    public void feed() {
        if(hungerLvl<100) {
            hungerLvl += 50;
            cost += 40;
            amountOfMeals++;
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




}
