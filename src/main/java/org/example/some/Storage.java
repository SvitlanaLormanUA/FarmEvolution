package org.example.some;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.otherGameObjects.Wallet;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static int product1Cost = 5;
    private static int product2Cost = 10;
    private static int product3Cost = 8;
    private static int product4Cost = 80;
    private static int product5Cost = 40;
    private ImageView storageView;
    private Wallet wallet;
    private Pane root;

    private int product1;
    private int product2;
    private int product3;
    private int product4;
    private int product5;

    List<ImageView> product1Views;

    public Storage(ImageView storageView, Wallet wallet){
        this.storageView = storageView;
        this.wallet = wallet;
        this.root = new Pane();
        root.getChildren().add(this.storageView);

         this.product1 = 0;
         this.product2 = 0;
         this.product3 = 0;
         this.product4 = 0;
         this.product5 = 0;
         product1Views = new ArrayList<>();
    }

    public void addProduct1(){
        product1++;

        if (product1==1) {
            ImageView productView1 = createProduct1(storageView.getLayoutX(), storageView.getLayoutY() + 52);
            product1Views.add(productView1);
        }
        if (product1==2) {
            ImageView productView2 = createProduct1(storageView.getLayoutX() + 13, storageView.getLayoutY() + 52);
            product1Views.add(productView2);
        }
        if (product1==3) {
            ImageView productView3 = createProduct1(storageView.getLayoutX() + 26, storageView.getLayoutY() + 52);
            product1Views.add(productView3);
        }
        if (product1==4) {
            ImageView productView4 = createProduct1(storageView.getLayoutX() + 39, storageView.getLayoutY() + 52);
            product1Views.add(productView4);
        }
        if (product1==5) {
            ImageView productView5 = createProduct1(storageView.getLayoutX() + 52, storageView.getLayoutY() + 52);
            product1Views.add(productView5);
        }


        if(product1==product1Views.size()) {
            Platform.runLater(() -> {
                root.getChildren().add(1, product1Views.get(product1Views.size()-1));
            });
        }
    }

    private ImageView createProduct1(double x, double y){
        ImageView productView = new ImageView(new Image("file:src/main/resources/images/firstLevel/products/egg.png"));
        productView.setFitWidth(25);
        productView.setFitHeight(25);
        productView.setLayoutX(x);
        productView.setLayoutY(y);

        return productView;
    }

    public void addProduct2(){
        product2++;
    }

    public void addProduct3(){
        product3++;
    }

    public void addProduct4(){
        product4++;
    }

    public void addProduct5(){
        product5++;
    }

    public void sellProduct1(int toSell){
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost *toSell);
        removeProductView(product1Views, t, toSell);
    }

    public void sellProduct2(int toSell){
        product2 -= toSell;
        wallet.income(product3Cost *toSell);
    }

    public void sellProduct3(int toSell){
        product3 -= toSell;
        wallet.income(product2Cost *toSell);
    }

    public void sellProduct4(int toSell){
        product4 -= toSell;
        wallet.income(product4Cost *toSell);
    }

    public void sellProduct5(int toSell){
        product5 -= toSell;
        wallet.income(product5Cost *toSell);
    }

    private void removeProductView(List<ImageView> productViews, int product, int toRemove){
        int start = product - 1;
        int end = product - toRemove;
        if (end < 0) end = 0;

        for (int i = start; i >= end; i--) {
            if(i<5) {
                ImageView view = productViews.get(i);
                Platform.runLater(() -> root.getChildren().remove(view));
                productViews.remove(i);
            }
        }
    }

    public int getProduct1() {
        return product1;
    }

    public int getProduct2() {
        return product2;
    }

    public int getProduct3() {
        return product3;
    }

    public int getProduct4() {
        return product4;
    }

    public int getProduct5() {
        return product5;
    }

    public Pane getRoot() {
        return root;
    }

    public ImageView getStorageView() {
        return storageView;
    }
}
