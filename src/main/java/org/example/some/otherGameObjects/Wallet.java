package org.example.some.otherGameObjects;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.example.some.FirstLevel;

import java.io.Serializable;
/**
 * Class that represents the wallet of the player
 */
public class Wallet implements Serializable {

    private ImageView walletView;
    private double x;
    private double y;
    private Pane root;
    private int coins;  // Removed static keyword
    public Label nCoins;

    public Wallet(double x, double y, int setCoins) {
        this.x = x;
        this.y = y;
        this.coins = setCoins;

        nCoins = new Label(String.valueOf(coins));
        nCoins.setLayoutX(x); // 263
        nCoins.setLayoutY(y); // 32
        nCoins.setFont(new Font("Arial", 22));

        root = new Pane();
        root.getChildren().addAll(nCoins);
    }
/**
*
* method to add coins to the wallet
* @param n - number of coins to add
*
* */
    public void income(int n) {
        coins += n;
        FirstLevel.saveCoins();
        updateCoinsLabel();
    }

    /**
     * Method to remove coins from the wallet
     * @param n - number of coins to remove
     */

    public void expense(int n) {
        if(coins-n>=0) {
            coins -= n;
            FirstLevel.saveCoins();
            updateCoinsLabel();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");
            alert.setContentText("У Вас недостатньо грошей! \nВиконайте додаткові завдання для заробітку");
            alert.showAndWait();
        }
    }
/**
* method to update the label with the number of coins
 *
 * */
    public void updateCoinsLabel() {
        this.nCoins.setText(String.valueOf(coins));
    }
/**
 * method to get the root of the wallet
 * */
    public Pane getRoot() {
        return root;
    }

    /**
     * set the number of coins
     * */
    public void setCoins(int coins) {
        this.coins = coins;
        updateCoinsLabel();
    }

    /**
     * method to reset the wallet
     * by default the amount of coins in wallet equals 100
     * */
    public void resetWallet() {
        coins = 100;
        updateCoinsLabel(); // Якщо потрібно оновити лейбл
    }

    /**
     * coins getter
     * */
    public int getCoins() {
        return coins;
    }

}
