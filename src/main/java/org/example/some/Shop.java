package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.otherGameObjects.Wallet;

import java.io.IOException;
import java.util.Objects;

import static org.example.some.FirstLevel.saveState;

public class Shop {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public Wallet wallet;
    public AnchorPane anchorPane;

    private static String currentLevel;

    @FXML
    public void backToGame(ActionEvent event) {
        try {
            saveState();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(currentLevel)));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setCurrentLevel(String level) {
        currentLevel = level;
    }

    public static String getCurrentLevel() {
        return currentLevel;
    }
}
