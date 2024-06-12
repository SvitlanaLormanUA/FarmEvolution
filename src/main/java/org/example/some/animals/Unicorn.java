package org.example.some.animals;

import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Unicorn extends AbstractAnimal{

    public Unicorn(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/UnicornRight.png",
                "file:src/main/resources/images/thirdLevel/animals/UnicornLeft.png",
                "src/main/resources/sound/unicornSound.mp3",
                "file:src/main/resources/images/thirdLevel/products/unicornBlood.png");
    }

    @Override
    public void movement() {

    }

    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
