package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    public void enterShop(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shop.fxml")));
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
        if (Sheep.isOnScreen) {
            Sheep sheep = new Sheep(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(sheep.getAnimalView());
        }
    }
    public void addGoose() {
        //додаємо гуся
        if (Goose.isOnScreen) {
            Goose goose = new Goose(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(goose.getAnimalView());
        }
    }
    public void addPig() {
        //додаємо свинку
        if (Pig.isOnScreen) {
            Pig pig = new Pig(250, 200, 1000, 630, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(pig.getAnimalView());
        }
    }
    public void addRabbit() {
        if (Rabbit.isOnScreen) {
            Rabbit rabbit = new Rabbit(250, 250, 1000, 630, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(2, rabbit.getAnimalView());
        }
    }
    public void addDonkey() {
        if (Donkey.isOnScreen) {
            Donkey donkey = new Donkey(650, 200, 1000, 430, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(donkey.getAnimalView());
        }
    }

    public void addCow() {
        if (Cow.isOnScreen) {
            Cow cow = new Cow(150, 200, 300, 530, anchorPane, wallet, well, feeder);
            anchorPane.getChildren().add(cow.getAnimalView());
        }
    }


    public void setAnimals() {
        addSheep();
        addGoose();
        addPig();
        addRabbit();
        addDonkey();
        addCow();
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
            out.writeBoolean(Sheep.isOnScreen);
            out.writeBoolean(Goose.isOnScreen);
            out.writeBoolean(Pig.isOnScreen);
            out.writeBoolean(Rabbit.isOnScreen);
            out.writeBoolean(Donkey.isOnScreen);
            out.writeBoolean(Cow.isOnScreen);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            coins = in.readInt();
           /* Sheep.isOnScreen = in.readBoolean();
            Goose.isOnScreen = in.readBoolean();
            Pig.isOnScreen = in.readBoolean();
            Rabbit.isOnScreen = in.readBoolean();
            Donkey.isOnScreen = in.readBoolean();
            Cow.isOnScreen = in.readBoolean();


            Sheep.setIsOnScreen(Sheep.isOnScreen);
            Goose.setIsOnScreen(Goose.isOnScreen);
            Pig.setIsOnScreen(Pig.isOnScreen);
            Rabbit.setIsOnScreen(Rabbit.isOnScreen);
            Donkey.setIsOnScreen(Donkey.isOnScreen);
            Cow.setIsOnScreen(Cow.isOnScreen);


            */
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
