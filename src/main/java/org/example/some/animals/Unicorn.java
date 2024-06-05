package org.example.some.animals;

import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Unicorn extends AbstractAnimal{

    public Unicorn(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage, String imagePath, String soundFile, String recourseFile) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage, imagePath, soundFile, recourseFile);
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
