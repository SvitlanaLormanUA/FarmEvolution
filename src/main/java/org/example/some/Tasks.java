package org.example.some;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.example.some.otherGameObjects.Wallet;

public class Tasks {
    private Pane root;
    private AnchorPane main;
    private ImageView menuView;
    private Wallet wallet;
    private Storage storage;
    private double x;
    private double y;
    private  Label taskLabel;
    private  Label taskLabel2;
    private  Label taskLabel3;
    private  Label taskLabel4;
    private Button close;
    private boolean nextLvL = false;


    private boolean task1 = false;
    private boolean task2 = false;
    private boolean task3 = false;
    private boolean task4 = false;

    private int nCoins;

    public Tasks(AnchorPane main) {
        this.main = main;
        this.wallet = FirstLevel.wallet;
        this.storage = FirstLevel.storage;
        this.nCoins = 0;
        createMenu();
    }
    private void addCloseButton(){
         close = new Button("×");
        close.setLayoutX(240);
        close.setLayoutY(10);
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });
    }
    private void addTaskFour() {
        if (task1 && task2 && task3){
            nCoins = wallet.getCoins();
        }
        taskLabel4 = new Label("Завдання 4: «Туди сюди і мільйонер»\n" +
                "Заробити монети: "+nCoins+"/2000");
        taskLabel4.setLayoutX(20);
        taskLabel4.setLayoutY(190);
        if (nCoins>=2000){
            nextLvL = true;
        }
    }
    private void addTaskThree() {
        taskLabel3 = new Label("Завдання 3: «До зими готовий!»\n" +
                "Зібрати хутро: "+storage.getnWool()+"/20 штук");
        taskLabel3.setLayoutX(20);
        taskLabel3.setLayoutY(140);
        if(storage.getnWool()>=20){
            task3 = true;
        }
    }
    private void addTaskTwo() {
        taskLabel2 = new Label("Завдання 2: «Молоко, любов і гуси»\n" +
                "Зібрати яйця: "+storage.getnEggs()+"/30 штук\n" +
                "Продати молоко: "+storage.getSoldMilk()+"/15 галонів");
        taskLabel2.setLayoutX(20);
        taskLabel2.setLayoutY(80);
        if (storage.getnEggs()>=30 && storage.getSoldMilk()>=15){
            task2=true;
        }
    }
    private void addTaskOne() {
         taskLabel = new Label("Завдання 1: «Досвідчений м’ясник»\n" +
                "Продати м’ясо свині: "+storage.getSoldPig()+"/10 шматків\n" +
                "Продати м’ясо кролів: "+storage.getSoldRabbit()+"/15 шматків");
        taskLabel.setLayoutX(20);
        taskLabel.setLayoutY(20);
        if (storage.getSoldPig()>=10 && storage.getSoldRabbit()>=15){
            task1=true;
        }
    }

    public void createMenu() {
        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        menuView.setFitWidth(280);
        menuView.setFitHeight(250);

        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        addTaskOne();
        addTaskTwo();
        addTaskThree();
        addTaskFour();
        addCloseButton();



        root = new Pane();
        root.setTranslateX(this.x + 880);
        root.setTranslateY(this.y + 100);
        root.getChildren().addAll(menuView, taskLabel, taskLabel2, taskLabel3, taskLabel4, close);
    }

    public boolean isNextLvL() {
        return nextLvL;
    }

    public Pane getRoot() {
        return root;
    }
}

