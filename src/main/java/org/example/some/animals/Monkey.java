package org.example.some.animals;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Monkey extends AbstractAnimal{


    public Monkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/monkey.png",
                "src/main/resources/sound/monkey.mp3",
                "file:src/main/resources/images/secondLevel/products/banana.png"
        ) ;
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}