package org.example.some.animals;

import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Gnome extends AbstractAnimal{
    public Gnome(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/gnomeRight.png",
                "file:src/main/resources/images/thirdLevel/animals/gnomeLeft.png",
                "src/main/resources/sound/dragonfly.mp3",
                "file:src/main/resources/images/thirdLevel/products/pouch.png");
        animalView.setFitHeight(110);
        animalView.setFitWidth(80);
    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
