package org.example.some;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.some.otherGameObjects.Wallet;

public class Storage {

    private static int eggCost = 5;
    private static int milkCost = 10;
    private static int woolCost = 8;
    private static int pigMeatCost = 80;
    private static int rabbitMeatCost = 40;
    private ImageView storageView;
    private Wallet wallet;
    private Pane root;

    private int eggs;
    private int wool;
    private int milk;
    private int pigMeat;
    private int rabbitMeat;


    public Storage(ImageView storageView, Wallet wallet){
        this.storageView = storageView;
        this.wallet = wallet;
        this.root = new Pane();
        root.getChildren().add(this.storageView);

         this.eggs = 0;
         this.wool = 0;
         this.milk = 0;
         this.pigMeat = 0;
         this.rabbitMeat = 0;
    }

    public void addEgg(){
        eggs++;
        if (eggs>0){

        }
    }

    public void addWool(){
        wool++;
    }

    public void addMilk(){
        milk++;
    }

    public void addPigMeat(){
        pigMeat++;
    }

    public void addRabbitMeat(){
        rabbitMeat++;
    }

    public void sellEgg(int toSell){
        eggs -= toSell;
        wallet.income(eggCost*toSell);
    }

    public void sellWool(int toSell){
        wool -= toSell;
        wallet.income(woolCost*toSell);
    }

    public void sellMilk(int toSell){
        milk -= toSell;
        wallet.income(milkCost*toSell);
    }

    public void sellPigMeat(int toSell){
        pigMeat -= toSell;
        wallet.income(pigMeatCost*toSell);
    }

    public void sellRabbitMeat(int toSell){
        rabbitMeat -= toSell;
        wallet.income(rabbitMeatCost*toSell);
    }

    public int getEggs() {
        return eggs;
    }

    public int getWool() {
        return wool;
    }

    public int getMilk() {
        return milk;
    }

    public int getPigMeat() {
        return pigMeat;
    }

    public int getRabbitMeat() {
        return rabbitMeat;
    }
}
