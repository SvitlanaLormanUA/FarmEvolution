package org.example.some;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  1255,  707);
        stage.setTitle("Farm Evolution!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(t -> {
            FirstLevel.saveState();
            //SecondLevel.saveState();
            //ThirdLevel.saveState();
            Platform.exit();
            System.exit(0);

        });
    }

    public static void main(String[] args) {
        launch();
    }
}