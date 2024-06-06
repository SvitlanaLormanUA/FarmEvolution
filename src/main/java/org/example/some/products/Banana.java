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

     productView.setFitWidth(55);
     productView.setFitHeight(55);
     productView.setCursor(Cursor.HAND);

     productView.setLayoutX(random.nextInt(650, 760));
     productView.setLayoutY(random.nextInt(177, 300));

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
