package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.Feeder;
import org.example.some.animals.Sheep;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class FirstLevel implements javafx.fxml.Initializable{

    public AnchorPane anchorPane;
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
    Well well;
    Feeder feeder;
    Wallet wallet;

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

    double progress = 1.0;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterBar.setStyle("-fx-accent: #4392FF;");

        //додана овечка на основну панель
        Sheep sheep = new Sheep(300,  200,500,330, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(sheep.getSheepView());
        sheep.play();

    }

    public void setWater() {
        if (progress < 0.1) {
            progress = 1.0;
        }
        waterBar.setProgress(progress);
    }


}
