package org.example.some;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;



public class AskingMenu {

    private ImageView menuView;
    private String text;
    private double x;
    private double y;
    private Pane root;
    private Label question;
    private Button yes;
    private Button no;
    private Button close;

    public AskingMenu(String text, double x, double y){
        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        this.x = x;
        this.y = y;
        menuView.setFitWidth(300);
        menuView.setFitHeight(150);
        this.text = text;

        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        question = new javafx.scene.control.Label(text);
        question.setLayoutX(10);
        question.setLayoutY(50);

        yes = new Button("Так");
        yes.setLayoutX(110);
        yes.setLayoutY(100);
        yes.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; ");
        yes.setCursor(Cursor.HAND);

        no = new Button("Ні");
        no.setLayoutX(170);
        no.setLayoutY(100);
        no.setStyle("-fx-background-color: rgba(190,16,16,0.98); -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; ");
        no.setCursor(Cursor.HAND);

        close = new Button("×");
        close.setLayoutX(270);
        close.setLayoutY(2);
        close.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setCursor(Cursor.HAND);

        root = new Pane();
        root.setTranslateX(this.x);
        root.setTranslateY(this.y);
        root.getChildren().addAll(menuView, question, yes, no, close);
    }

    public Button getYes() {
        return yes;
    }
    public Button getNo() {
        return no;
    }

    public Button getClose() {
        return close;
    }

    public Pane getRoot() {
        return root;
    }
}
