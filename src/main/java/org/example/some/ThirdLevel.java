package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.*;
import org.example.some.otherGameObjects.Wallet;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wallet = FirstLevel.wallet;
        anchorPane.getChildren().add(wallet.getRoot());
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

    public void addFairy() {



    }
    public void addGnome() {



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


}
