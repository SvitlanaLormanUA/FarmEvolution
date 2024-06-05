package org.example.some;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.otherGameObjects.Wallet;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private int product1Cost = 5;
    private int product2Cost = 10;
    private int product3Cost = 8;
    private int product4Cost = 80;
    private int product5Cost = 40;
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

    private int nWool;
    private int nEggs;
    private int soldMilk;
    private int soldPig;
    private int soldRabbit;

    public Storage(ImageView storageView, Wallet wallet, Image product1View, Image product2View, Image product3View,
                   Image product4View, Image product5View){
        this.storageView = storageView;
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

    public void addProduct1(){
        product1++;
        nEggs++;
        if (product1==1) {
            ImageView productView1 = createProduct(product1Image, storageView.getLayoutX(), storageView.getLayoutY() + 52, 25, 25);
            product1Views.add(productView1);
            Platform.runLater(() -> {
                root.getChildren().add(1,product1Views.get(0));
            });
        }
        if (product1==2) {
            ImageView productView2 = createProduct(product1Image, storageView.getLayoutX() + 13, storageView.getLayoutY() + 52, 25, 25);
            product1Views.add(productView2);
            Platform.runLater(() -> {
                root.getChildren().add(1,product1Views.get(1));
            });
        }
        if (product1==3) {
            ImageView productView3 = createProduct(product1Image, storageView.getLayoutX() + 26, storageView.getLayoutY() + 52, 25, 25);
            product1Views.add(productView3);
            Platform.runLater(() -> {
                root.getChildren().add(1,product1Views.get(2));
            });
        }
        if (product1==4) {
            ImageView productView4 = createProduct(product1Image, storageView.getLayoutX() + 39, storageView.getLayoutY() + 52,25, 25);
            product1Views.add(productView4);
            Platform.runLater(() -> {
                root.getChildren().add(1,product1Views.get(3));
            });
        }
        if (product1==5) {
            ImageView productView5 = createProduct(product1Image, storageView.getLayoutX() + 52, storageView.getLayoutY() + 52, 25, 25);
            product1Views.add(productView5);
            Platform.runLater(() -> {
                root.getChildren().add(1,product1Views.get(4));
            });
        }


        /*if(product1==product1Views.size()) {
            Platform.runLater(() -> {
                root.getChildren().add(product1Views.get(product1Views.size()-1));
            });
        }*/
    }

    public void addProduct2(){
        product2++;
        nWool++;
        if (product2==1) {
            ImageView productView1 = createProduct(product2Image, storageView.getLayoutX()+1, storageView.getLayoutY() + 22, 30, 30);
            product2Views.add(productView1);
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(0));
            });
        }
        if (product2==2) {
            ImageView productView2 = createProduct(product2Image, storageView.getLayoutX() + 25, storageView.getLayoutY() + 22, 30, 30);
            product2Views.add(productView2);
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(1));
            });
        }
        if (product2==3) {
            ImageView productView3 = createProduct(product2Image, storageView.getLayoutX() + 49, storageView.getLayoutY() + 22, 30, 30);
            product2Views.add(productView3);
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(2));
            });
        }
        if (product2==4) {
            ImageView productView4 = createProduct(product2Image, storageView.getLayoutX() + 15, storageView.getLayoutY() + 10,30, 30);
            product2Views.add(productView4);
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(3));
            });
        }
        if (product2==5) {
            ImageView productView5 = createProduct(product2Image, storageView.getLayoutX() + 34, storageView.getLayoutY() + 10, 30, 30);
            product2Views.add(productView5);
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(4));
            });
        }


        /*if(product2==product2Views.size()) {
            Platform.runLater(() -> {
                root.getChildren().add(1, product2Views.get(product2Views.size()-1));
            });
        }*/
    }
    public void addProduct3(){
        product3++;
        if (product3==1) {
            ImageView productView1 = createProduct(product3Image, storageView.getLayoutX()+1, storageView.getLayoutY()-39, 15, 40);
            product3Views.add(productView1);
            Platform.runLater(() -> {
                root.getChildren().add(1, product3Views.get(0));
            });
        }
        if (product3==2) {
            ImageView productView2 = createProduct(product3Image, storageView.getLayoutX() + 17, storageView.getLayoutY()-39, 15, 40);
            product3Views.add(productView2);
            Platform.runLater(() -> {
                root.getChildren().add(1, product3Views.get(1));
            });
        }
        if (product3==3) {
            ImageView productView3 = createProduct(product3Image, storageView.getLayoutX() + 33, storageView.getLayoutY()-39, 15, 40);
            product3Views.add(productView3);
            Platform.runLater(() -> {
                root.getChildren().add(1, product3Views.get(2));
            });
        }
        if (product3==4) {
            ImageView productView4 = createProduct(product3Image, storageView.getLayoutX() + 49, storageView.getLayoutY()-39,15, 40);
            product3Views.add(productView4);
            Platform.runLater(() -> {
                root.getChildren().add(1, product3Views.get(3));
            });
        }
        if (product3==5) {
            ImageView productView5 = createProduct(product3Image, storageView.getLayoutX() + 65, storageView.getLayoutY()-39, 15, 40);
            product3Views.add(productView5);
            Platform.runLater(() -> {
                root.getChildren().add(1, product3Views.get(4));
            });
        }
    }

    public void addProduct4(){
        product4++;
        if (product4==1) {
            ImageView productView1 = createProduct(product4Image, storageView.getLayoutX()+2, storageView.getLayoutY()+97, 30, 30);
            product4Views.add(productView1);
            Platform.runLater(() -> {
                root.getChildren().add(1, product4Views.get(0));
            });
        }
        if (product4==2) {
            ImageView productView2 = createProduct(product4Image, storageView.getLayoutX() + 24, storageView.getLayoutY()+97, 30, 30);
            product4Views.add(productView2);
            Platform.runLater(() -> {
                root.getChildren().add(1, product4Views.get(1));
            });
        }
        if (product4==3) {
            ImageView productView3 = createProduct(product4Image, storageView.getLayoutX() + 46, storageView.getLayoutY()+97, 30, 30);
            product4Views.add(productView3);
            Platform.runLater(() -> {
                root.getChildren().add(1, product4Views.get(2));
            });
        }
        if (product4==4) {
            ImageView productView4 = createProduct(product4Image, storageView.getLayoutX() + 12, storageView.getLayoutY()+87,30, 30);
            product4Views.add(productView4);
            Platform.runLater(() -> {
                root.getChildren().add( product4Views.get(3));
            });
        }
        if (product4==5) {
            ImageView productView5 = createProduct(product4Image, storageView.getLayoutX() + 32, storageView.getLayoutY()+87, 30, 30);
            product4Views.add(productView5);
            Platform.runLater(() -> {
                root.getChildren().add( product4Views.get(4));
            });
        }
    }

    public void addProduct5(){
        product5++;
        if (product5==1) {
            ImageView productView1 = createProduct(product5Image, storageView.getLayoutX()+2, storageView.getLayoutY()+127, 20, 20);
            product5Views.add(productView1);
            Platform.runLater(() -> {
                root.getChildren().add( product5Views.get(0));
            });
        }
        if (product5==2) {
            ImageView productView2 = createProduct(product5Image, storageView.getLayoutX() + 15, storageView.getLayoutY()+127, 20, 20);
            product5Views.add(productView2);
            Platform.runLater(() -> {
                root.getChildren().add( product5Views.get(1));
            });
        }
        if (product5==3) {
            ImageView productView3 = createProduct(product5Image, storageView.getLayoutX() + 28, storageView.getLayoutY()+127, 20, 20);
            product5Views.add(productView3);
            Platform.runLater(() -> {
                root.getChildren().add( product5Views.get(2));
            });
        }
        if (product5==4) {
            ImageView productView4 = createProduct(product5Image, storageView.getLayoutX() + 41, storageView.getLayoutY()+127,20, 20);
            product5Views.add(productView4);
            Platform.runLater(() -> {
                root.getChildren().add( product5Views.get(3));
            });
        }
        if (product5==5) {
            ImageView productView5 = createProduct(product5Image, storageView.getLayoutX() + 55, storageView.getLayoutY()+127, 20, 20);
            product5Views.add(productView5);
            Platform.runLater(() -> {
                root.getChildren().add( product5Views.get(4));
            });
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
