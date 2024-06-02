package org.example.some;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tasks {
    private Pane root;
    private AnchorPane main;
    private ImageView menuView;
    private double x;
    private double y;


    public Tasks(AnchorPane main) {
        this.main = main;
        createMenu();
    }

    public void createMenu() {
        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        menuView.setFitWidth(280);
        menuView.setFitHeight(250);

        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        Label taskLabel = new Label("Завдання 1: «Досвідчений м’ясник»\n" +
                "Продати м’ясо свині: 10 шматків\n" +
                "Продати м’ясо кролів: 15 шматків");
        taskLabel.setLayoutX(20);
        taskLabel.setLayoutY(20);

        Label taskLabel2 = new Label("Завдання 2: «Молоко, любов і гуси»\n" +
                "Зібрати яйця: 30 штук\n" +
                "Продати молоко: 15 галонів");
        taskLabel2.setLayoutX(20);
        taskLabel2.setLayoutY(80);

        Label taskLabel3 = new Label("Завдання 3: «До зими готовий!»\n" +
                "Зібрати хутро: 20 штук");
        taskLabel3.setLayoutX(20);
        taskLabel3.setLayoutY(140);

        Label taskLabel4 = new Label("Завдання 4: «Туди сюди і мільйонер»\n" +
                "Заробити 2000 монет");
        taskLabel4.setLayoutX(20);
        taskLabel4.setLayoutY(190);

        Button close = new Button("×");
        close.setLayoutX(240);
        close.setLayoutY(10);
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });

        root = new Pane();
        root.setTranslateX(this.x + 880);
        root.setTranslateY(this.y + 100);
        root.getChildren().addAll(menuView, taskLabel, taskLabel2, taskLabel3, taskLabel4, close);
    }

    public Pane getRoot() {
        return root;
    }
}

