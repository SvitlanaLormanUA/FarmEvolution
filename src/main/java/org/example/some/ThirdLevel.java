package org.example.some;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import org.example.some.otherGameObjects.Well;

//import static org.example.some.SecondLevel.coins;
import static org.example.some.FirstLevel.coins;
import static org.example.some.Shop.getCurrentLevel;

public class ThirdLevel   extends LevelMusicBack implements javafx.fxml.Initializable{
    public static int WIDTH = 1255;
    public static int HEIGHT = 707;

    public AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int countFairy = 1;
    public static int countGnome = 1;
    public static int countMinotaur = 1;
    public static int countMushroom = 1;
    public static int countUnicorn = 1;

    private SettingsMenu settingsMenu;
    public static Wallet wallet;
    @FXML
    private Button shopButton;
    @FXML
    private ImageView background;
    private Well well;
    @FXML
    private ImageView wellView;
    @FXML
    private ProgressBar wellBar;
    private Feeder feeder;
    @FXML
    private ImageView feederView;

    @FXML
    private ProgressBar foodBar;
    private static Storage storage;
    @FXML
    private ImageView storageView;
    private static ArrayList<Gnome> gnomeArrayList = new ArrayList<>();
    private static ArrayList<Unicorn> unicornArrayList = new ArrayList<>();
    private static ArrayList<Fairy> fairyArrayList = new ArrayList<>();
    private static ArrayList<Mushroom> mushroomArray = new ArrayList<>();
    private static ArrayList<Minotaur> minotaurArrayList = new ArrayList<>();

     Gnome gnome;
     Unicorn unicorn;
     Fairy fairy;
     Mushroom mushroom;
     Minotaur minotaur;
     FinishLevel finishLevel;
     Tasks tasksWindow;


    @FXML
    public void backToMainMenu(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chooseThirdLevel.fxml")));
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
            //System.out.println("Shop button clicked");
            deleteAllObjects();
            saveState();
            ShopFirstLevel.setCurrentLevel("thirdLevel.fxml");

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopThirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addWell(){
        well = new Well(wellView, wellBar);
        anchorPane.getChildren().add(well.getWellView());
    }

    private void addFeeder(){
        feeder = new Feeder(feederView,foodBar, wallet, 200, anchorPane);
        anchorPane.getChildren().add(feeder.getFoodView());
    }

    private void addStorage(){
        Image pouch = new Image("file:src/main/resources/images/thirdLevel/products/pouch.png");
        Image fairyDust = new Image("file:src/main/resources/images/thirdLevel/products/fairyDust.png");
        Image horn = new Image("file:src/main/resources/images/thirdLevel/products/horn.png");
        Image unicornBlood = new Image("file:src/main/resources/images/thirdLevel/products/unicornBlood.png");
        Image mushroom = new Image("file:src/main/resources/images/thirdLevel/products/miniMushroom.png");
        storage = new Storage(storageView, wallet, pouch, fairyDust, horn, unicornBlood, mushroom);
        storage.lvl3();
        anchorPane.getChildren().add(storage.getRoot());
    }

    @FXML
    void addStorageMenu(MouseEvent event) {
        saveState();
        StorageMenu storageMenu = new  StorageMenu(storage, wallet, WIDTH/2-140, HEIGHT/4-100, anchorPane);
        storageMenu.thirdLvl();
        anchorPane.getChildren().addLast(storageMenu.getRoot());
    }

    public void addFairy() {
        for (int i = 0; i < countFairy; i++) {
             fairy = new Fairy(100, 50, 1000, 300, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(fairy.getAnimalView());
            fairyArrayList.add(fairy);
        }



    }
    public void addGnome() {
        for (int i = 0; i < countGnome; i++) {
             gnome = new Gnome(300, 350, 1000, 700, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(gnome.getAnimalView());
            gnomeArrayList.add(gnome);
        }
    }

    public void addMinotaur() {
        for (int i = 0; i < countMinotaur; i++) {
             minotaur = new Minotaur(200, 350, 800, 700, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(minotaur.getAnimalView());
            minotaurArrayList.add(minotaur);
        }
    }
    public void addMushroom() {
        for (int i = 0; i < countMushroom; i++) {
             mushroom = new Mushroom(350, 350, 900, 600, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(mushroom.getAnimalView());
            mushroomArray.add(mushroom);
        }


    }
    public void addUnicorn() {
        for (int i = 0; i < countUnicorn; i++) {
             unicorn = new Unicorn(300, 350, 800, 600, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(unicorn.getAnimalView());
            unicornArrayList.add(unicorn);
        }
    }




    public void setAnimals() {
        addFairy();
        addGnome();
        addMinotaur();
        addMushroom();
        addUnicorn();
    }


    public void showSettings(ActionEvent event) {
        settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());

        //settingsMenu.applyBlur(true,  anchorPane);

    }

    public void showExtraTasks(ActionEvent event) {
        MenuExtraGame3 game = new MenuExtraGame3(anchorPane);
        game.createImagePane();
        anchorPane.getChildren().add(game.getRoot());
    }

    public void showTasks(ActionEvent event) {
        tasksWindow = new Tasks(anchorPane, 3, storage);
        tasksWindow.createMenu(
                "Завдання 1: «Для темного лордаа»\n" + "Зібрати кров єдинорога: " + storage.getGatheredUnicornBlood() +"/" + storage.getGatheredUnicornBloodP(),
                "Завдання 2: «Чарівні знахідки» \n"+ "Продати мішечки: " + storage.getSoldPouch() + "/" + storage.getSoldPouchP() + "\n" +
                        "Продати пил феї: " + storage.getSoldDust() + "/" + storage.getSoldDustP(),
                "Завдання 3: «До таверни гномів» \n"+ "Продати гриби: " + storage.getSoldMushrooms() + "/" + storage.getSoldMushroomsP() + "\n" +
                        "Зібрати рога: " + storage.getGatheredHorns() + "/" + storage.getGatheredHornsP(),
                "Завдання 4: «Геній, мільярдер, плейбой, \nфілантроп» \n" + "Заробити:  " + tasksWindow.nCoins + "/1000 монет"
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
                    finishLevel.createImagePane(anchorPane,3);
                }
            });
        }).start();
    }

    /**
     * видаляємо обʼєкти (перед переходом на наступний рівень)
     * */
    public static void deleteAllObjects(){
        for(int i=0; i<gnomeArrayList.size(); i++){
            if (gnomeArrayList.get(i) == null) continue;
            gnomeArrayList.get(i).delete();
            gnomeArrayList.set(i, null);
        }
        for(int i=0; i<fairyArrayList.size(); i++){
            if(fairyArrayList.get(i) == null) continue;
            fairyArrayList.get(i).delete();
            fairyArrayList.set(i, null);
        }
        for(int i=0; i<minotaurArrayList.size(); i++){
            if(minotaurArrayList.get(i) == null) continue;
            minotaurArrayList.get(i).delete();
            minotaurArrayList.set(i, null);
        }
        for(int i=0; i<unicornArrayList.size(); i++){
            if(unicornArrayList.get(i) == null) continue;
            unicornArrayList.get(i).delete();
            unicornArrayList.set(i, null);
        }
        for(int i=0; i<mushroomArray.size(); i++){
            if(mushroomArray.get(i) == null) continue;
            mushroomArray.get(i).delete();
            mushroomArray.set(i, null);
        }
    }

    private void deleteObj(){
        well = null;
        feeder = null;
        foodBar = null;
        wellBar = null;
    }

    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
    }
    public static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("levelThree.ser"))) {

            out.writeInt(countGnome);
            out.writeInt(countUnicorn);
            out.writeInt(countFairy);
            out.writeInt(countMinotaur);
            out.writeInt(countMushroom);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Dragonfly.saveAmountOfMeals();
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("levelThree.ser"))) {

            countGnome = in.readInt();
            countUnicorn = in.readInt();
            countFairy = in.readInt();
            countMinotaur = in.readInt();
            countMushroom = in.readInt();
        } catch (IOException e) {
            coins = 100; // Default value if there's an error or the file doesn't exist
        }
    }
    public static void setCoins(int coins){

        FirstLevel.coins = coins;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setMouseTransparent(true);
        shopButton.toFront();
        foodBar.setStyle("-fx-accent: #e13be7;");



        addWallet();
        loadState();

        if (SettingsMenu.start) {
            deleteAllObjects();
            deleteObj();
            countGnome = 1;
            countUnicorn = 1;
            countFairy = 1;
            countMinotaur = 1;
            countMushroom = 1;
            storage.gatheredUnicornBlood = 0;
            storage.soldDust = 0;
            storage.soldPouch = 0;
            storage.soldMushrooms = 0;
            storage.soldHorns = 0;


            if (unicorn!=null) {
                unicorn.amountOfMeals = 0;
            }
            SettingsMenu.start = false;
        }
        addStorage();
        addWell();
        addFeeder();

        setAnimals();
    }

    private void addWallet() {
        wallet = FirstLevel.wallet;
        loadState();
        wallet.setCoins(coins);
        anchorPane.getChildren().add(wallet.getRoot());
    }

}