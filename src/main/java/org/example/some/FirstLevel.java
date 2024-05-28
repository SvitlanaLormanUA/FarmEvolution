package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.Pig;
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
    private Button homeButton;

    @FXML
    private ImageView wellView;
    @FXML
    public ProgressBar waterBar;

    @FXML
    private ImageView feederView;

    @FXML
    private ProgressBar foodBar;

    Well well;
    Feeder feeder;
    Wallet wallet;

    @FXML
    private Label amountOfCoins;
    private int coins;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
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
        foodBar.setStyle("-fx-accent: #f37a39;");

        addWallet();
        addFeeder();
        addWell();

        //додана овечка на основну панель
        Sheep sheep = new Sheep(250,  200,1000,630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(sheep.getAnimalView());


        //додаємо курочку
        Pig pig = new Pig(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(pig.getAnimalView());


    }

    private void addWallet(){
        wallet = new Wallet(50, 50);
        anchorPane.getChildren().add(wallet.getRoot());
    }

    private void addWell(){
        well = new Well(wellView, waterBar);
        anchorPane.getChildren().add(well.getWellView());
    }

    private void addFeeder(){
        feeder = new Feeder(feederView, foodBar, wallet);
        anchorPane.getChildren().add(feeder.getFoodView());
    }

    public void setWater() {
        if (progress < 0.1) {
            progress = 1.0;
        }
        waterBar.setProgress(progress);
    }


}
