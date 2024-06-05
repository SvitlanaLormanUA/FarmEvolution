package org.example.some;

import javafx.scene.layout.Pane;
import org.example.some.animals.AbstractAnimal;
import org.example.some.animals.Feeder;
import org.example.some.otherGameObjects.Well;

public class Mushroom extends AbstractAnimal {
    public Mushroom(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage, String imagePath, String soundFile, String recourseFile) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage, imagePath, soundFile, recourseFile);
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
