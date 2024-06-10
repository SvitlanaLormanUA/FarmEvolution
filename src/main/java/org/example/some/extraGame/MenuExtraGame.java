package org.example.some.extraGame;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MenuExtraGame {
    private Pane root;
    private AnchorPane main;
    private ImageView imageView;
    private double x;
    private double y;

    public MenuExtraGame(AnchorPane main) {
        this.main = main;
        createImagePane();
    }

    public void createImagePane() {
        Image image = new Image("file:src/main/resources/images/extraGame/Extra_game1.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(300);

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Button close = new Button("×");
        close.setLayoutX(25);
        close.setLayoutY(10);
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });


        Circle clickableArea = new Circle(125,75,30);
        clickableArea.setFill(Color.TRANSPARENT);
        clickableArea.setOnMouseClicked(event -> {
            main.getChildren().remove(root);
            AnimalCountingGame animalCountingGame = new AnimalCountingGame();
            Stage stage=new Stage();
            animalCountingGame.start(stage);
        });

        Circle clickable = new Circle(125,220,30);
        clickable.setFill(Color.TRANSPARENT);
        clickable.setOnMouseClicked(event -> {
            main.getChildren().remove(root);

        });

        root = new Pane();
        root.setTranslateX(this.x + 900);
        root.setTranslateY(this.y + 80);
        root.getChildren().addAll(imageView, close, clickableArea);
    }

    public Pane getRoot() {
        return root;
    }
}
