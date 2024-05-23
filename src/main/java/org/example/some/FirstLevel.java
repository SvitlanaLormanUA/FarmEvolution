package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class FirstLevel {

    @FXML
    private Button buttonMenu;

    @FXML
    private Label amountOfCoins;
    private int coins;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void backToMainMenu(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAmountOfCoins() {
        amountOfCoins.setText(String.valueOf(coins));
    }

}
