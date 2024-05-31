package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.otherGameObjects.Wallet;

import java.io.IOException;
import java.util.Objects;

import static org.example.some.FirstLevel.saveState;

public class ShopSecondLevel extends Shop {
    private final int PARROT_PRICE = 905;
    private final int MONKEY_PRICE = 1120;
    private final int PEACOCK_PRICE = 1545;
    private final int LEMuR_PRICE = 1700;
    private final int DRAGONFLY_PRICE = 700;
    //public int amountOfCoins = SecondLevel.wallet.getCoins();

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public Wallet wallet;
    public AnchorPane anchorPane;

    public void buyCarrot(ActionEvent event) {

    }
    public void buyMonkey(ActionEvent event) {

    }
    public void buyPeacock(ActionEvent event) {

    }
    public void buyLemur(ActionEvent event) {

    }
    public void buyDragonfly(ActionEvent event) {

    }
    public void firstShop(ActionEvent event) {
        try {
            saveState();
            //ShopFirstLevel.setCurrentLevel("firstLevel.fxml");


            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstShopDone.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
