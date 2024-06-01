package org.example.some.otherGameObjects;

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
    private static int coins;
    public Label nCoins;

    public Wallet(double x, double y, int setCoins) {
        /*Image image = new Image("file:src/main/resources/images/coins.png");
        this.walletView = new ImageView(image);*/


        this.x = x;
        this.y = y;

        this.coins = setCoins(setCoins);


        nCoins = new Label(String.valueOf(coins));
        nCoins.setLayoutX(x);//263
        nCoins.setLayoutY(y);//32
        nCoins.setFont(new Font("Arial", 22));

        root = new Pane();
        root.getChildren().addAll(nCoins);
    }

    public void income(int n) {
        this.coins += n;
        this.nCoins.setText(String.valueOf(coins));
    }

    public void expense(int n) {
        this.coins -= n;
        this.nCoins.setText(String.valueOf(coins));
    }

    public ImageView getWalletView() {
        return walletView;
    }

    public Pane getRoot() {
        return root;
    }

    public static int getCoins() {
        return coins;
    }

    public int setCoins(int coins) {
        return this.coins = coins;
    }

}
