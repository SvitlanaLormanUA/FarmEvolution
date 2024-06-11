package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Well well;
    @FXML
    private ImageView wellView;
    @FXML
    private ProgressBar wellBar;
    private Feeder feeder;
    private Storage storage;
    private ArrayList<Gnome> gnomeArrayList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wallet = FirstLevel.wallet;
        anchorPane.getChildren().add(wallet.getRoot());

        addWell();
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
