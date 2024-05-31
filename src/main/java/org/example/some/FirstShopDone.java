package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.example.some.otherGameObjects.Wallet;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstShopDone extends ShopFirstLevel implements Initializable {
    public int amountOfCoins = FirstLevel.wallet.getCoins();
    public AnchorPane anchorPane = new AnchorPane();
    Wallet wallet;


    public void addWallet() {
        wallet = new Wallet(95, 50, amountOfCoins);
        anchorPane.getChildren().add(wallet.getRoot());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addWallet();
    }

}
