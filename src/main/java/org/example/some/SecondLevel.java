package org.example.some;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.*;
import org.example.some.extraGame.MenuExtraGame2;
import org.example.some.extraGame.MenuExtraGame3;
import org.example.some.otherGameObjects.Instr;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;
import javafx.scene.control.ProgressBar;
import org.example.some.products.Banana;

import java.io.*;
import java.net.URL;
import java.util.*;

import static org.example.some.FirstLevel.coins;
import static org.example.some.FirstLevel.loadCoins;
import static org.example.some.SettingsMenu.restart;


public class SecondLevel  extends LevelMusicBack implements Initializable {

    public static int WIDTH = 1255;
    public static int HEIGHT = 707;

    public  Well well;
    public  Feeder feeder;
    @FXML
    private ImageView feeder2View;
    @FXML
    private ProgressBar foodBar;
    @FXML
    private ProgressBar waterBar;

    @FXML
    private ImageView well2View;


    public Storage storage;
    @FXML
    private ImageView storage2View;


    public static Wallet wallet;
    public  AnchorPane anchorPane;
    public ImageView lian;
    public ImageView background;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static SettingsMenu settingsMenu;

    public static int countMonkeys = 1;
    public static int countDragonflies = 1;
    public static int countLemurs = 1;
    public static int countPeacocks = 1;
    public static int countParrots = 1;

    public static ArrayList<Monkey> monkeyArrayList = new ArrayList<>();
    public static ArrayList<Dragonfly> dragonflyArrayList = new ArrayList<>();
    public static ArrayList<Lemur> lemurArrayList = new ArrayList<>();
    public static ArrayList<Peacock> peacockArrayList = new ArrayList<>();

    public static ArrayList<Parrot> parrotArrayList = new ArrayList<>();

    public static boolean bananaIsAdded = false;
    public static boolean productIsAdded = false;
    private static boolean settingsShown = false;

    public static int countBanana = 0;
    public static ArrayList<Banana> bananas = new ArrayList<>();

    private Monkey monkey;
    public  Dragonfly dragonfly;
    private Lemur lemur;
    private Peacock peacock;
    private Parrot parrot;

    private FinishLevel finishLevel;
    private Tasks tasksWindow;


    private void addAnimals(){
        addMonkey();
        addDragonfly();
        addLemur();
        addPeacock();
        addParrot();
    }

    //додаємо павичів
    private void addPeacock() {
        for (int i = 0; i < countPeacocks; i++) {
            peacock = new Peacock(300, 400, 700, 600, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(peacock.getAnimalView());
            peacockArrayList.add(peacock);
        }
    }


    public void addProducts(Monkey monkey) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (countBanana < 5) {
                        Banana  banana = new Banana(100);
                        anchorPane.getChildren().add(banana.getProductView());
                        bananaIsAdded = true;
                        bananas.add(banana);
                        banana.getProductView().setOnMouseClicked(event -> {
                            banana.getProductView().setDisable(true);
                            AbstractAnimal.root.getChildren().remove(monkey.productView);
                            SecondLevel.productIsAdded = false;

                            monkey.goForBanana();
                            ;
                        });

                        countBanana++;
                    } else if (countBanana == 5) {

                        timer.cancel();
                    }
                });

            }

        };
        timer.scheduleAtFixedRate(task, 0, 50000);



    }

    public void addThought(Monkey monkey) {
        if (bananaIsAdded && !productIsAdded) {
            monkey.giveProduct();
            monkey.updateProductViewPosition();
            productIsAdded = true;
        }
    }
    public void addWallet() {
        wallet = FirstLevel.wallet;
        loadCoins();
        wallet.setCoins(FirstLevel.wallet.getCoins());
        anchorPane.getChildren().add(wallet.getRoot());
    }

    private void addFeeder(){
        feeder = new Feeder(feeder2View, foodBar, wallet, 100, anchorPane);
        anchorPane.getChildren().add(feeder.getFoodView());
    }

    private void addWell(){
        well = new Well(well2View, waterBar);
        anchorPane.getChildren().add(well.getWellView());
    }

    public void addStorage(){
        Image banana = new Image("file:src/main/resources/images/secondLevel/products/banana.png");
        Image dragonflyPr = new Image("file:src/main/resources/images/secondLevel/products/dragonflyPr.png");
        Image feather = new Image("file:src/main/resources/images/secondLevel/products/feather.png");
        Image mango = new Image("file:src/main/resources/images/secondLevel/products/mango.png");
        Image nut = new Image("file:src/main/resources/images/secondLevel/products/nut.png");
        storage = new Storage(storage2View, wallet, banana, dragonflyPr, feather, mango, nut);
        storage.lvl2();
        if (restart) {
            storage.reset();
            restart = false;
        }
        anchorPane.getChildren().add(storage.getRoot());
    }

    public void addMonkey() {
        for (int i = 0; i < countMonkeys; i++) {
            monkey = new Monkey(250, 300, 700, 700, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(monkey.getAnimalView());
            addProducts(monkey);
            addThought(monkey);
            monkeyArrayList.add(monkey);
        }

    }

    public void addDragonfly() {
        for (int i = 0; i < countDragonflies; i++) {
            dragonfly = new Dragonfly(50, 50, 1100, 300, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(dragonfly.getAnimalView());
            dragonflyArrayList.add(dragonfly);
        }
    }

    public void addLemur(){
        for (int i = 0; i < countLemurs; i++) {
            lemur = new Lemur(80, 270, 700, 630, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(lemur.getAnimalView());
            lemurArrayList.add(lemur);
        }

    }

    public void addParrot(){
        for (int i = 0; i < countParrots; i++) {
            parrot = new Parrot(20, 90, 300, 400, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(parrot.getAnimalView());
            parrotArrayList.add(parrot);
        }
    }

    @FXML
    public void backToMainMenu(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chooseSecondLevel.fxml")));

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
            deleteAllObjects();
            saveState();
            ShopFirstLevel.setCurrentLevel("secondLevel.fxml");

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopSecondLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     *
     * додаємо меню до стораджа
     * */

    @FXML
    void addStorageMenu(MouseEvent event) {
        saveState();
        StorageMenu storageMenu = new  StorageMenu(storage, wallet, WIDTH/2-140, HEIGHT/4-100, anchorPane);
        storageMenu.secondLvl();
        anchorPane.getChildren().addLast(storageMenu.getRoot());
    }

    public static void deleteAllObjects(){
        for(int i=0; i<monkeyArrayList.size(); i++){
            if (monkeyArrayList.get(i) == null) continue;
            monkeyArrayList.get(i).delete();
            monkeyArrayList.set(i, null);
        }
        for(int i=0; i<dragonflyArrayList.size(); i++){
            if(dragonflyArrayList.get(i) == null) continue;
            dragonflyArrayList.get(i).delete();
            dragonflyArrayList.set(i, null);
        }
        for(int i=0; i<lemurArrayList.size(); i++){
            if(lemurArrayList.get(i) == null) continue;
            lemurArrayList.get(i).delete();
            lemurArrayList.set(i, null);
        }
        for(int i=0; i<peacockArrayList.size(); i++){
            if(peacockArrayList.get(i) == null) continue;
            peacockArrayList.get(i).delete();
            peacockArrayList.set(i, null);
        }
        for(int i=0; i<parrotArrayList.size(); i++){
            if(parrotArrayList.get(i) == null) continue;
            parrotArrayList.get(i).delete();
            parrotArrayList.set(i, null);
        }
    }

    /**
     * видалення обʼєктів (перед переходом на некст рівень)
     * */
    private void deleteObj(){
        well = null;
        feeder = null;
        foodBar = null;
        waterBar = null;
    }

    public void nextLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            deleteAllObjects();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void showTasks(ActionEvent event) {
        tasksWindow = new Tasks(anchorPane, 2, storage);
        tasksWindow.createMenu(
                "Завдання 1: «Недоспівала»\n" + "Засушити: " + storage.getDriedDragonfly() +"/" + storage.getDriedDragonflyP() + " бабок" ,
                "Завдання 2: «Ні пуху, ні пера» \n"   + "Продати " + storage.getSoldFeather()+ "/" + storage.getSoldFeatherP() + " пірʼїнок" ,
                "Завдання 3: «Тропічні делікатеси» \n"+ "Продати манго: " + storage.getSoldMango() + "/" + storage.getSoldManagoP() + "\n" +
                        "Зібрати горіхи: " + storage.getGatheredNuts() + "/" + storage.getGatheredNutsP() + "\n" +
                        "Зібрати звʼязки бананів: " + storage.getGatheredBananas() + "/" + storage.getGatheredBananasP(),
                "Завдання 4: «Тоні Старк відпочиває» \n" + "Заробити:  " + tasksWindow.nCoins + "/500 монет"
        );
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
                    finishLevel.createImagePane(anchorPane,2);
                }
            });
        }).start();
    }

    public void showExtraTasks(ActionEvent event) {
        MenuExtraGame2 gameM = new MenuExtraGame2(anchorPane);
        gameM.createImagePane();
        anchorPane.getChildren().add(gameM.getRoot());
    }

    /**
     * вивести налашування на екран
     * */
    public void showSettings(ActionEvent event) {
        settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());

        //settingsMenu.applyBlur(true,  anchorPane);

    }


    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
    }


    public static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("levelTwo.ser"))) {

            out.writeInt(countMonkeys);
            out.writeInt(countDragonflies);
            out.writeInt(countBanana);
            out.writeInt(countLemurs);
            out.writeInt(countPeacocks);
            out.writeInt(countParrots);






        } catch (IOException e) {
            e.printStackTrace();
        }

       // Dragonfly.saveAmountOfMeals();
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("levelTwo.ser"))) {

            countMonkeys = in.readInt();
            countDragonflies = in.readInt();
            countBanana = in.readInt();
            countLemurs = in.readInt();
            countPeacocks = in.readInt();
            countParrots = in.readInt();




        } catch (IOException e) {
            coins = 0; // Default value if there's an error or the file doesn't exist
        }
    }
    public static void setCoins(int coins) {FirstLevel.coins = coins;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodBar.setStyle("-fx-accent: #f37a39;");

        //addMultiProducts();
        addWallet();
        loadState();

        if (SettingsMenu.start) {
            deleteAllObjects();
            deleteObj();
            wallet.setCoins(100);
            countLemurs = 1;
            countPeacocks = 1;
            countMonkeys = 1;
            countDragonflies = 1;
            countParrots = 1;
            dragonfly.amountOfMeals = 0;
            Storage.soldFeather = 0;
            Storage.soldMango = 0;
            Storage.gatheredNuts = 0;
            Storage.gatheredBananas = 0;
            Storage.driedDragonfly = 0;
            bananas.clear();

            SettingsMenu.start = false;

        }
        addStorage();
        addFeeder();
        addWell();
        addAnimals();


    }

}