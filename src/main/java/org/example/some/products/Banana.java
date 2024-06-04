package org.example.some.products;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.some.SecondLevel;

import java.util.Random;



public class Banana {
    public int price;


    Random random;
    ImageView productView;


    public Banana(int price) {
        this.productView = new ImageView(new Image("file:src/main/resources/images/secondLevel/products/banana.png"));
        this.price = price;

        random = new Random();
        addProduct();
    }


    public void addProduct() {

     productView.setFitWidth(100);
     productView.setFitHeight(100);
     productView.setCursor(Cursor.HAND);

     productView.setLayoutX(random.nextInt(0, 90));
     productView.setLayoutY(random.nextInt(73, 400));

    }
    public ImageView getProductView() {
        return productView;
    }
    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


}
