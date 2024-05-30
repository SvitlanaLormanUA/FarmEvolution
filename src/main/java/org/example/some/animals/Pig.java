package org.example.some.animals;

import javafx.scene.layout.AnchorPane;

import org.example.some.FirstLevel;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;


public class Pig extends AbstractAnimal{


    public static boolean isOnScreen = true;

    public Pig(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Wallet wallet, Well well, Feeder feeder) {
        super ( worldStartX, worldStartY, worldEndX, worldEndY, anchorPane,
                well, feeder,
                "file:src/main/resources/images/firstLevel/animals/pig.png",
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
    public void sell() {
        FirstLevel.wallet.income(cost);
        removeMenu();
        root.getChildren().remove(animalView);
        isOnScreen = false;
    }

    @Override
    public void giveProduct() {

    }

    @Override
    public boolean whetherIsOnScreen() {
        return isOnScreen;
    }


    public static void setIsOnScreen(boolean isOnScreen) {
        Pig.isOnScreen = isOnScreen;
    }
    @Override
    public void death() {
        if (this.hungerLvl == 0 || this.thirstLvl == 0) {
            FirstLevel.wallet.expense(63);
            removeMenu();
            root.getChildren().remove(animalView);
            isOnScreen = false;

        }
    }



}
