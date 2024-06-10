package org.example.some.otherGameObjects;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.Serializable;

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

    public void income(int n) {
        coins += n;
        updateCoinsLabel();
    }

    public void expense(int n) {
        if(coins-n>=0) {
            coins -= n;
            updateCoinsLabel();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");
            alert.setContentText("У Вас недостатньо грошей! \nВиконайте додаткові завдання для заробітку");
            alert.showAndWait();
        }
    }

    public void updateCoinsLabel() {
        this.nCoins.setText(String.valueOf(coins));
    }

    public ImageView getWalletView() {
        return walletView;
    }

    public Pane getRoot() {
        return root;
    }

    public void setCoins(int coins) {
        this.coins = coins;
        updateCoinsLabel();
    }
    public void resetWallet() {
        coins = 100;
        updateCoinsLabel(); // Якщо потрібно оновити лейбл
    }
    public int getCoins() {
        return coins;
    }

}
