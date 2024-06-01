package org.example.some.animals;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Emotion {
    private ImageView emojiView;
    private ImageView thoughtView;
    private Pane root;

    public Emotion(Pane root) {
        this.root = root;

        this.thoughtView = new ImageView();
        this.thoughtView.setFitWidth(80);
        this.thoughtView.setFitHeight(70);
        this.emojiView = new ImageView();
        this.emojiView.setFitWidth(30);
        this.emojiView.setFitHeight(30);
    }

    public void showEmoji(double x, double y, String emojiImagePath) {
        thoughtView.setImage(new Image("file:src/main/resources/images/emotions/reaction.png"));
        thoughtView.setX(x);
        thoughtView.setY(y);
        emojiView.setImage(new Image(emojiImagePath));
        emojiView.setX(x+20);
        emojiView.setY(y+15);
        emojiView.setVisible(true);
        this.root.getChildren().addAll(thoughtView, emojiView);

        // Hide the emoji after a short period
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> this.root.getChildren().removeAll(thoughtView, emojiView));
        pause.play();
    }

    /*public void angry(){
        showEmotion("file:src/main/resources/images/emotions/angry.png");
    }

    public void confused(){
        showEmotion("file:src/main/resources/images/emotions/confused.png");
    }

    public void dissatisfied(){
        showEmotion("file:src/main/resources/images/emotions/dissatisfied.png");
    }

    public void fed(){
        showEmotion("file:src/main/resources/images/emotions/fed.png");
    }

    public void frustrated(){
        showEmotion("file:src/main/resources/images/emotions/frustrated.png");
    }

    public void sad(){
        showEmotion("file:src/main/resources/images/emotions/sad.png");
    }

    public void crying(){
        showEmotion("file:src/main/resources/images/emotions/crying.png");
    }

    public void thirsty(){
        showEmotion("file:src/main/resources/images/emotions/thirsty.png");
    }

    public void wellFed(){
        showEmotion("file:src/main/resources/images/emotions/wellFed.png");
    }*/

    public Pane getRoot() {
        return root;
    }
}