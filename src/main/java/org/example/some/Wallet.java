package org.example.some;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Wallet {

    private ImageView walletView;
    private double x;
    private double y;
    private Pane root;
    private int coins;
    private Label nCoins;

    public Wallet(double x, double y){
        Image image = new Image("file:src/main/resources/images/coins.png");
        this.walletView = new ImageView(image);

        this.x = x;
        this.y = y;
        this.coins = 100;
        walletView.setFitWidth(70);
        walletView.setFitHeight(30);
        walletView.setX(x);
        walletView.setY(y);

        nCoins = new Label(String.valueOf(coins));
        nCoins.setLayoutX(x+35);
        nCoins.setLayoutY(y+8);

        root = new Pane();
        root.getChildren().addAll(walletView, nCoins);
    }

    public void income(int n){
        this.coins+=n;
        this.nCoins.setText(String.valueOf(coins));
    }

    public void expense(int n){
        this.coins-=n;
        this.nCoins.setText(String.valueOf(coins));
    }

    public ImageView getWalletView() {
        return walletView;
    }

    public Pane getRoot() {
        return root;
    }
}
