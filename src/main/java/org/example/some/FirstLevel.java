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
import org.example.some.animals.*;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class FirstLevel implements javafx.fxml.Initializable, Serializable {

    public double progress = 1.0;
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
    public static Wallet wallet ;

    @FXML

    private static int coins;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void backToMainMenu(ActionEvent event) {
            try {
                saveState();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info.fxml")));
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }




    public void showInfo(ActionEvent event) {

    }
    public void showSettings(ActionEvent event) {

    }
    public void showTasks(ActionEvent event) {


    }
    public void showExtraTasks(ActionEvent event) {

    }


    public int getCoins() {
        return coins;
    }
    public void addSheep() {
        //додана овечка на основну панель
        Sheep sheep = new Sheep(250,  200,1000,630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(sheep.getAnimalView());
    }
    public void addGoose() {
        //додаємо гуся
        Goose goose = new Goose(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(goose.getAnimalView());
    }
    public void addPig() {
        //додаємо свинку
        Pig pig = new Pig(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(pig.getAnimalView());
    }
    public void addRabit() {
        Rabbit rabbit = new Rabbit(250, 250, 1000, 630, anchorPane, wallet, well, feeder);
        anchorPane.getChildren().add(2, rabbit.getAnimalView());
    }


    public void setAnimals() {
        addSheep();
        addGoose();
        addPig();
        addRabit();
    }




    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterBar.setStyle("-fx-accent: #4392FF;");
        foodBar.setStyle("-fx-accent: #f37a39;");


        addWallet();
        loadState();
        addFeeder();
        addWell();
        setAnimals();
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

    static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser"))) {
            out.writeInt(wallet.getCoins());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            coins = in.readInt();
            setCoins(coins);
            wallet.setCoins(coins);
            wallet.nCoins.setText(String.valueOf(coins));
        } catch (IOException e) {
            coins = 0; // Default value if there's an error or the file doesn't exist
        }
    }
    public static void setCoins(int coins) {
        FirstLevel.coins = coins;
    }


}
