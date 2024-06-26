package org.example.some;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.some.animals.*;
import org.example.some.extraGame.MenuExtraGame1;
import org.example.some.otherGameObjects.Instr;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.example.some.SettingsMenu.restart;

/**
 * First level class
 * */
public class FirstLevel extends LevelMusicBack implements javafx.fxml.Initializable, Serializable {



    public static int WIDTH = 1255;
    public static int HEIGHT = 707;
    public static int countCow = 1;
    public static int countSheep = 1;
    public static int countGoose = 1;
    public static int countPig = 1;
    public static int countRabbit = 1;

    public static double walletX = 260;
    public static double walletY = 32;

    SettingsMenu settingsMenu;


    public double progress = 1.0;
    public  AnchorPane anchorPane;

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

    static Well well;
    static Feeder feeder;
    public static Storage storage;
    public static Wallet wallet ;
    String fxmlFile;

    @FXML

    public static int coins;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public static ArrayList<Sheep> sheepArrayList = new ArrayList<>();
    public static ArrayList<Goose> gooseArrayList = new ArrayList<>();
    public static ArrayList<Cow> cowArrayList = new ArrayList<>();
    public static ArrayList<Pig> pigArrayList = new ArrayList<>();
    public static ArrayList<Rabbit> rabbitArrayList = new ArrayList<>();


    Sheep sheep;
    Goose goose;
    Cow cow;
      static Pig pig;
    Rabbit rabbit;

    public FinishLevel finishLevel;
    public Tasks tasksWindow;


   /**
    * method to enter shop and change scenes to it
    * */
    @FXML
    public void enterShop(ActionEvent event) {
        try {
            deleteAllObjects();
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

    /***
     * method to go to the tasks menu when user click on the button
     * */

    @FXML
    public void showTasks(ActionEvent event) {
        tasksWindow = new Tasks(anchorPane, 1, storage);
        // tasksWindow = new Tasks(anchorPane, 1);
        tasksWindow.createMenu("Завдання 1: «Досвідчений м’ясник»\n" +
                "Продати м’ясо свині: "+storage.getSoldPig()+"/"+storage.getSoldPigP()+" шматків\n" +
                "Продати м’ясо кролів: "+storage.getSoldRabbit()+"/"+storage.getSoldRabbitP()+" шматків", "Завдання 2: «Молоко, любов і гуси»\n" +
                "Зібрати яйця: "+storage.getnEggs()+"/"+storage.getnEggsP()+" штук\n" +
                "Продати молоко: "+storage.getSoldMilk()+"/"+storage.getSoldMilkP()+" галонів", "Завдання 3: «До зими готовий!»\n" +
                "Зібрати хутро: "+storage.getnWool()+"/"+storage.getnWoolP()+" штук", "Завдання 4: «Туди сюди і мільйонер»\n" +
                "Заробити монети: "+tasksWindow.nCoins +"/200");
        anchorPane.getChildren().add(tasksWindow.getRoot());


        new Thread(() -> {
            while (!tasksWindow.isNextLvL()) {
                try {
                    Thread.sleep(100); // Check every 100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                if (tasksWindow.isNextLvL()) {
                    deleteObj();
                    Thread.currentThread().interrupt();
                    finishLevel = new FinishLevel();
                    finishLevel.createImagePane(anchorPane, 1);
                }
            });
        }).start();
    }

    /**
     * method to go open storage when user click on the button
     * */

    @FXML
    void addStorageMenu(MouseEvent event) {
        StorageMenu storageMenu = new  StorageMenu(storage, wallet, WIDTH/2-140, HEIGHT/4-100, anchorPane);
        storageMenu.firstLvl();
        anchorPane.getChildren().add(storageMenu.getRoot());
    }


    public void addSheep() {
        //додана овечка на основну панель
        for (int i = 0; i < countSheep; i++) {
             sheep = new Sheep(250, 300, 1000, 630, anchorPane, well, feeder,storage);
            anchorPane.getChildren().add(sheep.getAnimalView());
            sheepArrayList.add(sheep);
        }

    }
    public void addGoose() {
        //додаємо гуся
        for (int i = 0; i < countGoose; i++) {
             goose = new Goose(250, 300, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(goose.getAnimalView());
            gooseArrayList.add(goose);
        }
    }
    public void addPig() {
        //додаємо свинку
        for (int i = 0; i < countPig; i++) {
             pig = new Pig(250, 300, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(pig.getAnimalView());
            pigArrayList.add(pig);

        }


    }
    //додано кролика
    public void addRabbit() {
        for (int i = 0; i < countRabbit; i++) {
             rabbit = new Rabbit(250, 250, 1000, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(2, rabbit.getAnimalView());
            rabbitArrayList.add(rabbit);
        }
    }

    public void addCow() {
        for (int i = 0; i < countCow; i++) {
             cow = new Cow(250, 200, 1000, 630, anchorPane, well, feeder, storage);
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

    public static void deleteAllObjects(){
        if (sheepArrayList!= null) {
            for (int i = 0; i < sheepArrayList.size(); i++) {
                if (sheepArrayList.get(i) != null) {
                    sheepArrayList.get(i).delete();
                    sheepArrayList.set(i, null);
                }
            }
        }
        if (gooseArrayList!= null) {
            for (int i = 0; i < gooseArrayList.size(); i++) {
                if (gooseArrayList.get(i)!=null) {
                    gooseArrayList.get(i).delete();
                    gooseArrayList.set(i, null);
                }
            }
        }
        if(cowArrayList!=null) {
            for (int i = 0; i < cowArrayList.size(); i++) {
                if (cowArrayList.get(i)!=null) {
                    cowArrayList.get(i).delete();
                    cowArrayList.set(i, null);
                }
            }
        }
        if (pigArrayList!= null) {
            for (int i = 0; i < pigArrayList.size(); i++) {
                if (pigArrayList.get(i)!=null) {
                    pigArrayList.get(i).delete();
                    pigArrayList.set(i, null);
                }
            }
        }
        if (rabbitArrayList!=null) {
            for (int i = 0; i < rabbitArrayList.size(); i++) {
                if (rabbitArrayList.get(i) != null) {
                    rabbitArrayList.get(i).delete();
                    rabbitArrayList.set(i, null);
                }
            }
        }
    }


    private void deleteObj(){
       // storage = null;
        well = null;
        feeder = null;
        foodBar = null;
        waterBar = null;
    }


    private void addWallet(){


        wallet = new Wallet(walletX, walletY, 100);
       loadCoins();
        anchorPane.getChildren().add(wallet.getRoot());
    }

    private void addWell(){
        well = new Well(wellView, waterBar);
        anchorPane.getChildren().add(well.getWellView());
    }

    private void addFeeder(){
        feeder = new Feeder(feederView, foodBar, wallet, 54, anchorPane);
        anchorPane.getChildren().add(feeder.getFoodView());
    }


    private void addStorage(){
        Image egg = new Image("file:src/main/resources/images/firstLevel/products/egg.png");
        Image wool = new Image("file:src/main/resources/images/firstLevel/products/wool.png");
        Image milk = new Image("file:src/main/resources/images/firstLevel/products/milkStorage.png");
        Image meatP = new Image("file:src/main/resources/images/firstLevel/products/meat.png");
        Image meatR = new Image("file:src/main/resources/images/firstLevel/products/meat.png");
        storage = new Storage(storageView, wallet, egg, wool, milk, meatP, meatR);
        storage.lvl1();

        if (restart) {
            storage.reset();
            restart = false;
        }
        anchorPane.getChildren().add(storage.getRoot());
    }

    /**
     * method to handle the water level in the well
     * */
    public void setWater() {
        if (progress < 0.1) {
            progress = 1.0;
        }
        waterBar.setProgress(progress);
    }


/**
 *
 * load coins from the file
 * */
    public static void saveCoins() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("coins.ser"))) {
            if (wallet!=null) {
                coins = wallet.getCoins();
                out.writeInt(wallet.getCoins());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * load coins from the file
     * */
    public static void loadCoins(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("coins.ser"))) {
            coins = in.readInt();
            setCoins(coins);
            wallet.setCoins(coins);
            wallet.nCoins.setText(String.valueOf(coins));
        } catch (IOException e) {
            coins = 100; // Default value if there's an error or the file doesn't exist
            System.out.println("Error loading game state: " + e.getMessage());
        }
    }


    /**
     *
     *
     * save game state to the file
     * */
    public static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("levelOne.ser"))) {


            out.writeInt(countCow);
            out.writeInt(countSheep);
            out.writeInt(countGoose);
            out.writeInt(countPig);
            out.writeInt(countRabbit);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * load game state from the file
     * */
    public static void loadState(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("levelOne.ser"))) {



            countCow = in.readInt();
            countSheep = in.readInt();
            countGoose = in.readInt();
            countPig = in.readInt();
            countRabbit = in.readInt();
           // pig.amountOfMeals = in.readInt();


        } catch (IOException e) {
            coins = 100; // Default value if there's an error or the file doesn't exist
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
            saveCoins();
            storage.reset();
            deleteAllObjects();
            deleteObj();

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
        MenuExtraGame1 gameMenu = new MenuExtraGame1(anchorPane);
        gameMenu.createImagePane();
        anchorPane.getChildren().add(gameMenu.getRoot());
    }

    public void showSettings(ActionEvent event) {
        settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterBar.setStyle("-fx-accent: #4392FF;");
        foodBar.setStyle("-fx-accent: #f37a39;");




        addWallet();
        loadState();

        if (SettingsMenu.start) {
            deleteAllObjects();
            coins = 100;
            wallet.setCoins(100);
            countGoose = 1;
            countCow = 1;
            countSheep = 1;
            countPig = 1;
            countRabbit = 1;
            if (rabbit!=null) {
                rabbit.amountOfMeals = 0;
            }
            if (pig!=null) {
                pig.amountOfMeals = 0;
            }
            SettingsMenu.start = false;

        }
        addFeeder();
        addWell();
        addStorage();
        setAnimals();
        saveState();
        addMusic();



    }

    public Parent getRoot() {
        return root;
    }


}