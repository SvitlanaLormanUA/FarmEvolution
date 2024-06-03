package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.animals.Feeder;
import org.example.some.animals.Monkey;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.example.some.Shop.getCurrentLevel;

public class SecondLevel  implements Initializable {
    public  Well well;
    public  Feeder feeder;
    public static Wallet wallet;
    public AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wallet = FirstLevel.wallet;
        anchorPane.getChildren().add(wallet.getRoot());
        addMonkey();

    }

    public void addMonkey() {
        Monkey monkey = new Monkey(250, 300, 1000, 630, anchorPane, well, feeder, "file:src/main/resources/images/secondLevel/animals/monkey.png", "file:src/main/resources/images/secondLevel/animals/monkey.png", "src/main/resources/sound/monkey.mp3", "file:src/main/resources/images/secondLevel/products/banana.png");
        anchorPane.getChildren().add(monkey.getAnimalView());
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

    public static void saveState() {

    }


    //ВИДАЛИМО
    @FXML
    public void previousLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void nextLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displaySettings(ActionEvent event) {
    }

    public void showTasks(ActionEvent event) {
    }

    public void showExtraTasks(ActionEvent event) {
    }

    public void showSettings(ActionEvent event) {
    }

    public void showInfo(ActionEvent event) {
    }
}
