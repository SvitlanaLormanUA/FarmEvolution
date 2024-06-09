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
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController {
    public AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button beginButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    SettingsMenu settingsMenu;

   public void onAction(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
          System.out.println(e);
        }
   }

   @FXML
    public void showSettings() {
        settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());
    }

}