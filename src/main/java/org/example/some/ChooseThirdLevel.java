package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChooseThirdLevel {
    public AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private SettingsMenu settingsMenu;

    @FXML

    public void continueGame(ActionEvent event) {
        try {
            SecondLevel.saveState();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showSettings(ActionEvent event) {
        settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());

        //settingsMenu.applyBlur(true,  anchorPane);

    }
}
