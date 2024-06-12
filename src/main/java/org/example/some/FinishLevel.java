package org.example.some;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.some.FirstLevel.deleteAllObjects;
import static org.example.some.FirstLevel.storage;

public class FinishLevel {
    private Pane root;
    private AnchorPane main;
    private ImageView imageView;
    private double x;
    private double y;

    private Stage stage;
    private Scene scene;
    String fxmlFile;

    public FinishLevel(AnchorPane main, int level) {
        this.main = main;
        createImagePane(level);
    }

    public void createImagePane(int level) {
        Image image = new Image("file:src/main/resources/images/FinishLevel.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(370);
        imageView.setFitHeight(200);

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Button close = new Button("  Ок  ");
        close.setLayoutX(170);
        close.setLayoutY(160);
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
            try {
                //saveState();
                storage.reset();
                deleteAllObjects();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                main.getChildren().clear();
                if(level == 1)
                    fxmlFile = "chooseSecondLevel.fxml";
                else if(level == 2)
                    fxmlFile = "chooseThirdLevel.fxml";
                else
                    fxmlFile = "menu.fxml";

                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource( fxmlFile )));

                deleteAllObjects();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        });

        root = new Pane();
        root.setTranslateX(this.x + 480);
        root.setTranslateY(this.y + 180);
        root.getChildren().addAll(imageView, close);
        main.getChildren().add(root);
    }



    public Pane getRoot() {
        return root;
    }
}