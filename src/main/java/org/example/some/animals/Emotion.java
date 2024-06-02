package org.example.some.animals;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Emotion {
    private  ImageView emojiView;
    private  ImageView thoughtView;
    private Pane root;

    public Emotion() {

        thoughtView = new ImageView();
        this.thoughtView.setFitWidth(80);
        this.thoughtView.setFitHeight(70);
        this.emojiView = new ImageView();
        this.emojiView.setFitWidth(30);
        this.emojiView.setFitHeight(30);
    }

    public  void showEmoji(double x, double y, String emojiImagePath) {
        thoughtView.setImage(new Image("file:src/main/resources/images/emotions/reaction.png"));
        thoughtView.setX(x);
        thoughtView.setY(y);
        emojiView.setImage(new Image(emojiImagePath));
        emojiView.setX(x+25);
        emojiView.setY(y+20);
        emojiView.setVisible(true);
        AbstractAnimal.root.getChildren().addAll(thoughtView, emojiView);

        // Hide the emoji after a short period
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> AbstractAnimal.root.getChildren().removeAll(thoughtView, emojiView));
        pause.play();
    }

    public  void angry(double x, double y){
        showEmoji(x,y,"file:src/main/resources/images/emotions/angry.png");
    }

 /*   public void confused(){
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
    }*/

    public void wellFed(double x, double y){
        showEmoji(x,y,"file:src/main/resources/images/emotions/wellFed.png");
    }

    public Pane getRoot() {
        return root;
    }
    public void deleteEmotion(){
        AbstractAnimal.root.getChildren().removeAll(thoughtView, emojiView);
    }
}