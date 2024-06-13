package org.example.some;

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
    private Storage storage;
    @FXML
    private ImageView storageView;
    private ArrayList<Gnome> gnomeArrayList = new ArrayList<>();
    private ArrayList<Unicorn> unicornArrayList = new ArrayList<>();
    private ArrayList<Fairy> fairyArrayList = new ArrayList<>();
    private ArrayList<Mushroom> mushroomArray = new ArrayList<>();
    private ArrayList<Minotaur> minotaurArrayList = new ArrayList<>();





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
            Fairy fairy = new Fairy(300, 50, 1000, 300, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(fairy.getAnimalView());
            fairyArrayList.add(fairy);
        }



    }
    public void addGnome() {
        for (int i = 0; i < countGnome; i++) {
            Gnome gnome = new Gnome(300, 350, 1000, 700, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(gnome.getAnimalView());
            gnomeArrayList.add(gnome);
        }
    }

    public void addMinotaur() {
        for (int i = 0; i < countMinotaur; i++) {
            Minotaur minotaur = new Minotaur(300, 350, 1000, 700, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(minotaur.getAnimalView());
            minotaurArrayList.add(minotaur);
        }
    }
    public void addMushroom() {
        for (int i = 0; i < countMushroom; i++) {
            Mushroom mushroom = new Mushroom(350, 350, 900, 600, anchorPane, well, feeder, storage);
            anchorPane.getChildren().add(mushroom.getAnimalView());
            mushroomArray.add(mushroom);
        }


    }
    public void addUnicorn() {
        for (int i = 0; i < countUnicorn; i++) {
            Unicorn unicorn = new Unicorn(300, 350, 900, 600, anchorPane, well, feeder, storage);
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
    }

    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
    }
    static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("levelThree.ser"))) {
            out.writeInt(coins);
            out.writeInt(countGnome);
            out.writeInt(countUnicorn);
            out.writeInt(countFairy);
            out.writeInt(countMinotaur);
            out.writeInt(countMushroom);







        } catch (IOException e) {
            e.printStackTrace();
        }

        Dragonfly.saveAmountOfMeals();
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("levelThree.ser"))) {
            coins = in.readInt();
            countGnome = in.readInt();
            countUnicorn = in.readInt();
            countFairy = in.readInt();
            countMinotaur = in.readInt();
            countMushroom = in.readInt();



            setCoins(coins);
            wallet.setCoins(coins);
            wallet.nCoins.setText(String.valueOf(coins));
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
        addStorage();
        addWell();
        addFeeder();
        loadState();
        setAnimals();
    }

    private void addWallet() {
        wallet = FirstLevel.wallet;
        wallet.setCoins(coins);
        anchorPane.getChildren().add(wallet.getRoot());
    }

}