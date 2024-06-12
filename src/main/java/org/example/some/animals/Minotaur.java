package org.example.some.animals;

import javafx.scene.layout.Pane;
import org.example.some.Storage;
import org.example.some.otherGameObjects.Well;

import java.util.Timer;
import java.util.TimerTask;

public class Minotaur extends AbstractAnimal{
    public Minotaur(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage) {
        super(worldStartX, worldStartY, worldEndX, worldEndY, root, well, feeder, storage,
                "file:src/main/resources/images/thirdLevel/animals/minotaurRight.png",
                "file:src/main/resources/images/thirdLevel/animals/minotaurLeft.png",
                "src/main/resources/sound/gallop.mp3",
                "file:src/main/resources/images/thirdLevel/products/horn.png");
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

    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (1050 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }
}
