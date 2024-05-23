package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class FirstLevel {

    @FXML
    private Button buttonMenu;
    @FXML
    private Button buttonInfo;

    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonTasks;
    @FXML
    private Button buttonExtraTasks;

    @FXML
    public ProgressBar waterBar;







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

    public void showInfo(ActionEvent event) {

    }
    public void showSettings(ActionEvent event) {

    }
    public void showTasks(ActionEvent event) {

    }
    public void showExtraTasks(ActionEvent event) {

    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    public int getCoins() {
        return coins;
    }

    //The BigDecimal class gives its user complete control over rounding behavior
    /* BigDecimal progress = new BigDecimal(String.format("%.2f", 1.0));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterBar.setStyle("-fx-accent: #4392FF;");

    }

    public void decreaseProgress() {
        if (progress.doubleValue() < 0.1) {
            progress = new BigDecimal(String.format("%.2f", 1.1));
        }
        progress = new BigDecimal(String.format("%.2f", progress.doubleValue() - 0.1));

        System.out.println(progress);
        waterBar.setProgress(progress.doubleValue());
    }*/


}
