package org.example.some.animals;

import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Monkey extends AbstractAnimal{


    public Monkey(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage, String imagePath, String imagePathLeft, String soundFile, String recourseFile) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/monkey.png",
                imagePathLeft,
                soundFile,
                "file:src/main/resources/images/secondLevel/products/banana.png");
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
