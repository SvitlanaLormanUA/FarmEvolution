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
import org.example.some.otherGameObjects.Instr;
import org.example.some.otherGameObjects.Wallet;
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import org.example.some.otherGameObjects.Well;

//import static org.example.some.SecondLevel.coins;
import static org.example.some.Shop.getCurrentLevel;

public class ThirdLevel   extends LevelMusicBack implements javafx.fxml.Initializable{
    public static int WIDTH = 1255;
    public static int HEIGHT = 707;
    public static Wallet wallet;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setMouseTransparent(true);
        shopButton.toFront();
        foodBar.setStyle("-fx-accent: #e13be7;");
        wallet = SecondLevel.wallet;
        anchorPane.getChildren().add(wallet.getRoot());

        addStorage();
        addWell();
        addFeeder();

        addGnome();
    }

    @FXML
    public void previousLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("secondLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveState() {

    }


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
            System.out.println("Shop button clicked");
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
        Image fairyDust = new Image("file:src/main/resources/images/secondLevel/products/dragonflyPr.png");
        Image horn = new Image("file:src/main/resources/images/secondLevel/products/feather.png");
        Image unicornBlood = new Image("file:src/main/resources/images/thirdLevel/products/unicornBlood.png");
        Image mushroom = new Image("file:src/main/resources/images/secondLevel/products/nut.png");
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



    }
    public void addGnome() {
        Gnome gnome = new Gnome(300, 350, 1000, 700, anchorPane, well, feeder, storage);
        anchorPane.getChildren().add(gnome.getAnimalView());
        gnomeArrayList.add(gnome);
    }

    public void addMinotaur() {

    }
    public void addMushroom() {

    }
    public void addUnicorn() {


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
    }

    public void showTasks(ActionEvent event) {
    }

    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
    }
}