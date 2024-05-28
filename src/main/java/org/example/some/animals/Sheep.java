package org.example.some.animals;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;




    public class Sheep extends AbstractAnimal {

        public Sheep(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Wallet wallet, Well well, Feeder feeder) {
            super(worldStartX, worldStartY, worldEndX, worldEndY, root, wallet, well, feeder,
                    "file:src/main/resources/images/sheep.png",
                    "file:src/main/resources/sound/sheepmp3.mp3",
                    "file:src/main/resources/images/wool.png");
        }

        @Override
        public void death() {
            // Implement specific behavior for sheep's death if necessary
        }

    }