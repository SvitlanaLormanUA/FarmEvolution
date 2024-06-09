package org.example.some;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.animals.AbstractAnimal;
import org.example.some.otherGameObjects.Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Storage {

    private int product1Cost;
    private int product2Cost;
    private int product3Cost;
    private int product4Cost;
    private int product5Cost;

    private int bananaCost = 30;
    private int dragonflyPrCost = 200;
    private int featherCost = 100;
    private int mangoCost = 110;
    private int nutCost = 120;
    private ImageView storageView;
    private Wallet wallet;
    private Pane root;

    private int product1;
    private int product2;
    private int product3;
    private int product4;
    private int product5;

    Image product1Image;
    Image product2Image;
    Image product3Image;
    Image product4Image;
    Image product5Image;

    List<ImageView> product1Views;
    List<ImageView> product2Views;
    List<ImageView> product3Views;
    List<ImageView> product4Views;
    List<ImageView> product5Views;

    private Random random = new Random();

    private int nWool;
    private int nEggs;
    private int soldMilk;
    private int soldPig;
    private int soldRabbit;

    public Storage(ImageView storageView, Wallet wallet, Image product1View, Image product2View, Image product3View,
                   Image product4View, Image product5View){
        this.storageView = storageView;
        this.storageView.setCursor(Cursor.HAND);
        this.wallet = wallet;
        this.root = new Pane();
        root.getChildren().add(this.storageView);

        this.product1 = 0;
        this.product2 = 0;
        this.product3 = 0;
        this.product4 = 0;
        this.product5 = 0;

        this.product1Image = product1View;
        this.product2Image = product2View;
        this.product3Image = product3View;
        this.product4Image = product4View;
        this.product5Image = product5View;

        product1Views = new ArrayList<>();
        product2Views = new ArrayList<>();
        product3Views = new ArrayList<>();
        product4Views = new ArrayList<>();
        product5Views = new ArrayList<>();

        this.nWool = 0;
        this.nEggs = 0;
        this.soldMilk = 0;
        this.soldPig = 0;
        this.soldRabbit = 0;
    }

    public void lvl1(){
        product1Cost = 5;
        product2Cost = 10;
        product3Cost = 8;
        product4Cost = 80;
        product5Cost = 40;
    }

    public void lvl2(){
        product1Cost = 100;
        product2Cost = 500;
        product3Cost = 100;
        product4Cost = 110;
        product5Cost = 120;
    }

    public void addEgg(){
        product1++;
        nEggs++;
        if(product1<=5) {
            double x = storageView.getLayoutX();
            double y = storageView.getLayoutY() + 52;
            int width = 25;
            for (int i = 1; i <= 5; i++) {
                if (i == product1) {
                    if (product1 != 1) {
                        for (int j = 2; j <= product1; j++) {
                            x += 13;
                        }
                    }
                    ImageView productView = createProduct(product1Image, x, y, width, width);
                    product1Views.add(productView);
                    int num = product1 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product1Views.get(num));
                    });
                }
            }
        }
    }

    public void addWool(){
        product2++;
        nWool++;
        if(product2<=5) {
            double x = storageView.getLayoutX()+1;
            double y = storageView.getLayoutY() + 22;
            int width = 30;
            for (int i = 1; i <= 5; i++) {
                if (i == product2) {
                    if (product2 == 2 || product2 == 3) {
                        for (int j = 2; j <= product2; j++) {
                            x += 24;
                        }
                    } else if(product2 == 4){
                        x = storageView.getLayoutX()+15;
                        y = storageView.getLayoutY() + 10;
                    } else if(product2 == 5){
                        x = storageView.getLayoutX()+34;
                        y = storageView.getLayoutY() + 10;
                    }
                    ImageView productView = createProduct(product2Image, x, y, width, width);
                    product2Views.add(productView);
                    int num = product2 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product2Views.get(num));
                    });
                }
            }
        }
    }
    public void addMilk(){
        product3++;
        if(product3<=5) {
            double x = storageView.getLayoutX()+1;
            double y = storageView.getLayoutY() - 39;
            int width = 15;
            int height = 40;
            for (int i = 1; i <= 5; i++) {
                if (i == product3) {
                    if (product3 != 1) {
                        for (int j = 2; j <= product3; j++) {
                            x += 16;
                        }
                    }
                    ImageView productView = createProduct(product3Image, x, y, width, height);
                    product3Views.add(productView);
                    int num = product3 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product3Views.get(num));
                    });
                }
            }
        }
    }

    public void addPigMeat(){
        product4++;
        if(product4<=5) {
            double x = storageView.getLayoutX() + 2;
            double y = storageView.getLayoutY() + 97;
            int width = 30;
            for (int i = 1; i <= 5; i++) {
                if (i == product4) {
                    if (product4 == 2 || product4 == 3) {
                        for (int j = 2; j <= product4; j++) {
                            x += 22;
                        }
                    } else if(product4 == 4){
                        x += 10;
                        y = storageView.getLayoutY() + 87;
                    } else if(product4 == 5){
                        x += 30;
                        y = storageView.getLayoutY() + 87;
                    }
                    ImageView productView = createProduct(product4Image, x, y, width, width);
                    product4Views.add(productView);
                    int num = product4 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product4Views.get(num));
                    });
                }
            }
        }
    }

    public void addRabbitMeat(){
        product5++;
        if(product5<=5) {
            double x = storageView.getLayoutX()+2;
            double y = storageView.getLayoutY() + 127;
            int width = 20;
            for (int i = 1; i <= 5; i++) {
                if (i == product5) {
                    if (product5 != 1) {
                        for (int j = 2; j <= product5; j++) {
                            x += 13;
                        }
                    }
                    ImageView productView = createProduct(product5Image, x, y, width, width);
                    product5Views.add(productView);
                    int num = product5 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product5Views.get(num));
                    });
                }
            }
        }
    }

    public void addBanana(){
        product1++;
    }

    public void addDragonflyPr(){
        product2++;
        nWool++;
        if(product2<=5) {
            double x;
            double y;
            int width = 50;
            int height = 50;
            for (int i = 1; i <= 5; i++) {
                if (i == product2) {
                    x = random.nextInt(880, 1000);
                    y = random.nextInt(540, 560);
                    ImageView productView = createProduct(product2Image, x, y, width, height);
                    product2Views.add(productView);
                    int num = product2 - 1;
                    Platform.runLater(() -> {
                        root.getChildren().add(1, product2Views.get(num));
                    });
                }
            }
        }
    }

    public void sellProduct1(int toSell){
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost *toSell);
        removeProductView(product1Views, t, toSell);
    }

    public void sellProduct2(int toSell){
        int t = product2;
        product2 -= toSell;
        wallet.income(product2Cost *toSell);
        removeProductView(product2Views, t, toSell);
    }

    public void sellProduct3(int toSell){
        int t = product3;
        product3 -= toSell;
        wallet.income(product3Cost *toSell);
        removeProductView(product3Views, t, toSell);
        soldMilk++;
    }

    public void sellProduct4(int toSell){
        int t = product4;
        product4 -= toSell;
        wallet.income(product4Cost *toSell);
        removeProductView(product4Views, t, toSell);
        soldPig++;
    }

    public void sellProduct5(int toSell){
        int t = product5;
        product5 -= toSell;
        wallet.income(product5Cost *toSell);
        removeProductView(product5Views, t, toSell);
        soldRabbit++;
    }

    public void sellBananas(int toSell){
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost *toSell);

        int start = product1 - 1;
        int end = product1 - toSell;
        if (end < 0) end = 0;

        for (int i = start; i >= end; i--) {
            ImageView view = SecondLevel.bananas.get(i).getProductView();
            Platform.runLater(() -> AbstractAnimal.root.getChildren().remove(view));
            SecondLevel.bananas.remove(i);
        }
    }

    private ImageView createProduct(Image productImage, double x, double y, double width, double height){
        ImageView productView = new ImageView(productImage);
        productView.setFitWidth(width);
        productView.setFitHeight(height);
        productView.setLayoutX(x);
        productView.setLayoutY(y);
        return productView;
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

    public int getProduct1Cost() {
        return product1Cost;
    }

    public int getProduct2Cost() {
        return product2Cost;
    }

    public void setProduct2Cost(int product2Cost) {
        this.product2Cost = product2Cost;
    }

    public int getProduct3Cost() {
        return product3Cost;
    }

    public int getProduct4Cost() {
        return product4Cost;
    }

    public int getProduct5Cost() {
        return product5Cost;
    }

    public Pane getRoot() {
        return root;
    }

    public ImageView getStorageView() {
        return storageView;
    }

    public int getnWool() {
        return nWool;
    }

    public int getnEggs() {
        return nEggs;
    }

    public int getSoldMilk() {
        return soldMilk;
    }
    public int getSoldPig() {
        return soldPig;
    }

    public int getSoldRabbit() {
        return soldRabbit;
    }
}