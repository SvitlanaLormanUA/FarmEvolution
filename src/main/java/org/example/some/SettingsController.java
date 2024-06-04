package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SettingsController {

    @FXML
    private Slider musicSlider;
    @FXML
    private Slider seSlider;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        musicSlider.setValue(50);
        seSlider.setValue(50);
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
