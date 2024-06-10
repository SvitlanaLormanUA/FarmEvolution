package org.example.some.animals;

import javafx.scene.layout.AnchorPane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

public class Peacock extends AbstractAnimal{
    public Peacock(int worldStartX, int worldStartY, int worldEndX, int worldEndY, AnchorPane anchorPane, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, anchorPane, well, feeder, storage,
                "file:src/main/resources/images/secondLevel/animals/peacock.png",
                "src/main/resources/sound/peacock.mp3",
                "file:src/main/resources/images/secondLevel/products/feather.png"
        );
        animalView.setFitWidth(130);
        animalView.setFitHeight(120);
    }
    @Override
    public void feed() {

    }

    @Override
    public void giveProduct() {

    }
}
