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
import org.example.some.FirstLevel;

public class MenuExtraGame1 {
    private Pane root;
    private AnchorPane main;
    private ImageView imageView;
    private double x;
    private double y;

    public MenuExtraGame1(AnchorPane main) {
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

        Circle clickableArea = new Circle(125, 75, 30);
        clickableArea.setFill(Color.TRANSPARENT);
        clickableArea.setOnMouseClicked(event -> {
            FirstLevel.saveState();
            main.getChildren().remove(root);
            AnimalCountingGame animalGame = new AnimalCountingGame();
            Stage stage = new Stage();
            animalGame.start(stage);
        });

        Circle clickable = new Circle(125, 250, 100);
        clickable.setFill(Color.TRANSPARENT);
        clickable.setOnMouseClicked(event -> {
            FirstLevel.saveState();
            main.getChildren().remove(root);
            AppleGame appleGame = new AppleGame();
            Stage stage = new Stage();
            appleGame.start(stage);
        });

        root = new Pane();
        root.setTranslateX(this.x + 900);
        root.setTranslateY(this.y + 80);
        root.getChildren().addAll(imageView, close, clickableArea, clickable);
    }

    public Pane getRoot() {
        return root;
    }
}
