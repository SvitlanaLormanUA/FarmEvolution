package org.example.some.animals;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class AnimalMeatMenu {

    private ImageView menuView;

    private double x;
    private double y;
    private Pane root;
    private AnimalMeat animal;
    private Button yes;
    private Button no;
    private Button close;
    private Label question;
    private Label feed;
    public AnimalMeatMenu(AnimalMeat animal, double x, double y, int amountOfFood, int purposedAmount){

        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");

        this.menuView = new ImageView(image);
        this.x = x;
        this.y = y;
        this.animal = animal;
        menuView.setFitWidth(180);
        menuView.setFitHeight(100);

        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        feed = new Label("Нагодовано: "+amountOfFood+"/"+purposedAmount);
        feed.setLayoutX(30);
        feed.setLayoutY(40);
        feed.setFont(new Font(16));

        question = new Label("Чи бажаєте ви отримати \n м'ясо на "+animal.getProductCost()+" монет?");
        question.setLayoutX(20);
        question.setLayoutY(20);

        yes = new Button("Так");
        yes.setLayoutX(40);
        yes.setLayoutY(60);
        yes.setCursor(Cursor.HAND);

        no = new Button("Ні");
        no.setLayoutX(80);
        no.setLayoutY(60);
        no.setCursor(Cursor.HAND);

        close = new Button("×");
        close.setLayoutX(150);
        close.setLayoutY(5);
        close.setCursor(Cursor.HAND);

        root = new Pane();
        root.setTranslateX(this.x-180);
        root.setTranslateY(this.y-20);

        if(amountOfFood>=purposedAmount) {
            root.getChildren().addAll(menuView, question, yes, no, close);
        } else {
            root.getChildren().addAll(menuView, feed, close);
        }

        close();
        yes();
        no();
    }

    private void close(){
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            animal.removeMeatMenu();
        });
    }

    private void yes(){
        yes.setStyle("-fx-background-color: #b6c55c; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; ");
        yes.setOnAction(event -> {
            animal.giveProduct();
            animal.removeMeatMenu();
        });
    }

    private void no(){
        no.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; ");
        no.setOnAction(event -> {
            animal.removeMeatMenu();
        });
    }

    public void update(){
        root.getChildren().clear();
        question.setText("Чи бажаєте ви отримати \n м'ясо на "+animal.getProductCost()+" монет?");
        root.getChildren().addAll(menuView, question, yes, no, close);
    }

    public Label getFeed() {
        return feed;
    }

    public Pane getRoot() {
        return root;
    }

}
