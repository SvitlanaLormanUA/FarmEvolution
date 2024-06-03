package org.example.some.otherGameObjects;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Instr {
    private Pane root;
    private AnchorPane main;
    private ImageView imageView;
    private double x;
    private double y;

    public Instr(AnchorPane main) {
        this.main = main;
        createImagePane();
    }

    public void createImagePane() {
        Image image = new Image("file:src/main/resources/images/instrPicture.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(600);

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

        root = new Pane();
        root.setTranslateX(this.x + 720);
        root.setTranslateY(this.y + 30);
        root.getChildren().addAll(imageView, close);
    }

    public Pane getRoot() {
        return root;
    }
}
