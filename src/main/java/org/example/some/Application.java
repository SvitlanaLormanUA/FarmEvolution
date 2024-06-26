package org.example.some;
/**
 * "Farm Evolution Game"
 * @authors Svitlana Lorman, Alona Dmytrokina, Dariia Shyn
 * @version 1.3
 * */
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
            SecondLevel.saveState();
            ThirdLevel.saveState();
            Platform.exit();
            System.exit(0);

        });
    }

    public static void main(String[] args) {
        launch();
    }
}