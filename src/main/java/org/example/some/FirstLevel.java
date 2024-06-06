package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.*;
import org.example.some.otherGameObjects.Instr;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class FirstLevel  implements javafx.fxml.Initializable, Serializable {
    public static boolean firstLevel = true;


    public static int WIDTH = 1255;
    public static int HEIGHT = 707;
    public static int countCow = 1;
    public static int countSheep = 1;
    public static int countGoose = 1;
    public static int countPig = 1;
    public static int countRabbit = 1;
    public static int donkeyCount = 1;


    public static double walletX = 263;
    public static double walletY = 32;



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
    private ImageView storageView;

    @FXML
    private ProgressBar foodBar;

    Well well;
    Feeder feeder;
    public static Storage storage;
    public static Wallet wallet ;

    @FXML

    private static int coins;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public static ArrayList<Sheep> sheepArrayList = new ArrayList<>();
    public static ArrayList<Goose> gooseArrayList = new ArrayList<>();
    public static ArrayList<Cow> cowArrayList = new ArrayList<>();
    public static ArrayList<Pig> pigArrayList = new ArrayList<>();
    public static ArrayList<Rabbit> rabbitArrayList = new ArrayList<>();


    public static void decreaseAnimalCount(String animalType) {
        switch (animalType) {
            case "Cow":
                countCow--;
                break;
            case "Sheep":
                countSheep--;
                break;
            case "Goose":
                countGoose--;
                break;
            case "Pig":
                countPig--;
                break;
            case "Rabbit":
                countRabbit--;
                break;
            default:
                break;
        }
    }



    @FXML
    public void backToMainMenu(ActionEvent event) {
        try {
            saveState();
            ShopFirstLevel.setCurrentLevel("infoFirst.fxml");

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
            ShopFirstLevel.setCurrentLevel("firstLevel.fxml");

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopFirstLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showTasks(ActionEvent event) {
        Tasks tasksWindow = new Tasks(anchorPane);
        tasksWindow.createMenu();
        anchorPane.getChildren().add(tasksWindow.getRoot());
    }

    @FXML
    void addStorageMenu(MouseEvent event) {
        StorageMenu storageMenu = new  StorageMenu(storage, wallet, WIDTH/2-140, HEIGHT/4-100, anchorPane);
        anchorPane.getChildren().add(storageMenu.getRoot());
    }


    public void addSheep() {
        //додана овечка на основну панель
        for (int i = 0; i < countSheep; i++) {
            Sheep sheep = new Sheep(250, 300, 1000, 630, anchorPane, well, feeder,storage);
            anchorPane.getChildren().add(sheep.getAnimalView());
            sheepArrayList.add(sheep);
        }

    }
    public void addGoose() {
        //додаємо гуся
        for (int i = 0; i < countGoose; i++) {
            Goose goose = new Goose(250, 300, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(goose.getAnimalView());
            gooseArrayList.add(goose);
        }
    }
    public void addPig() {
        //додаємо свинку
        for (int i = 0; i < countPig; i++) {
            Pig pig = new Pig(250, 300, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(pig.getAnimalView());
            pigArrayList.add(pig);
        }


    }
    public void addRabbit() {
        for (int i = 0; i < countRabbit; i++) {
            Rabbit rabbit = new Rabbit(250, 250, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(2, rabbit.getAnimalView());
            rabbitArrayList.add(rabbit);
        }
    }

    public void addCow() {
        for (int i = 0; i < countCow; i++) {
            Cow cow = new Cow(250, 200, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(cow.getAnimalView());
            cowArrayList.add(cow);
        }
    }


    public void setAnimals() {
        addSheep();
        addGoose();
        addPig();
        addRabbit();
        addCow();
    }

    public void deleteAllObjects(){
        for(int i=0; i<sheepArrayList.size(); i++){
            sheepArrayList.get(i).delete();
            sheepArrayList.set(i, null);
        }
        for(int i=0; i<gooseArrayList.size(); i++){
            gooseArrayList.get(i).delete();
            gooseArrayList.set(i, null);
        }
        for(int i=0; i<cowArrayList.size(); i++){
            cowArrayList.get(i).delete();
            cowArrayList.set(i, null);
        }
        for(int i=0; i<pigArrayList.size(); i++){
            pigArrayList.get(i).delete();
            pigArrayList.set(i, null);
        }
        for(int i=0; i<rabbitArrayList.size(); i++){
            rabbitArrayList.get(i).delete();
            rabbitArrayList.set(i, null);
        }

        storage = null;
        well = null;
        feeder = null;
        foodBar = null;
        waterBar = null;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterBar.setStyle("-fx-accent: #4392FF;");
        foodBar.setStyle("-fx-accent: #f37a39;");


        addWallet();
        loadState();
        addFeeder();
        addWell();
        addStorage();
        setAnimals();
        saveState();
    }

    private void addWallet(){

        wallet = new Wallet(walletX, walletY, 100);
        anchorPane.getChildren().add(wallet.getRoot());
    }

    private void addWell(){
        well = new Well(wellView, waterBar);
        anchorPane.getChildren().add(well.getWellView());
    }

    private void addFeeder(){
        feeder = new Feeder(feederView, foodBar, wallet, anchorPane);
        anchorPane.getChildren().add(feeder.getFoodView());
    }

    private void addStorage(){
        Image egg = new Image("file:src/main/resources/images/firstLevel/products/egg.png");
        Image wool = new Image("file:src/main/resources/images/firstLevel/products/wool.png");
        Image milk = new Image("file:src/main/resources/images/firstLevel/products/milkStorage.png");
        Image meatP = new Image("file:src/main/resources/images/firstLevel/products/meat.png");
        Image meatR = new Image("file:src/main/resources/images/firstLevel/products/meat.png");
        storage = new Storage(storageView, wallet, egg, wool, milk, meatP, meatR);
        anchorPane.getChildren().add(storage.getRoot());
    }

    public void setWater() {
        if (progress < 0.1) {
            progress = 1.0;
        }
        waterBar.setProgress(progress);
    }


    static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser"))) {
            if (wallet!=null) {
                out.writeInt(wallet.getCoins());
            }
            out.writeInt(countCow);
            out.writeInt(donkeyCount);
            out.writeInt(countSheep);
            out.writeInt(countGoose);
            out.writeInt(countPig);
            out.writeInt(countRabbit);
            out.writeBoolean(firstLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            coins = in.readInt();
            countCow = in.readInt();
            donkeyCount = in.readInt();
            countSheep = in.readInt();
            countGoose = in.readInt();
            countPig = in.readInt();
            countRabbit = in.readInt();
            firstLevel = in.readBoolean();

            setCoins(coins);
            wallet.setCoins(coins);
            wallet.nCoins.setText(String.valueOf(coins));
        } catch (IOException e) {
            coins = 0; // Default value if there's an error or the file doesn't exist
            System.out.println("Error loading game state: " + e.getMessage());
        }
    }

    public static void setCoins(int coins) {
        FirstLevel.coins = coins;
    }


    @FXML
    public void nextLevel(ActionEvent event) {
        try {
            saveState();

            Stage stage  = (Stage) ((Button) event.getSource()).getScene().getWindow();
            anchorPane.getChildren().clear();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("secondLevel.fxml")));
            deleteAllObjects();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    public int getCoins() {
        return coins;
    }

    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
    }

    public void showExtraTasks(ActionEvent event) {
    }

    public void showSettings(ActionEvent event) {
    }


}