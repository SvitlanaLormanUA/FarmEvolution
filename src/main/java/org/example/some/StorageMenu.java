package org.example.some;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.example.some.otherGameObjects.Wallet;

public class StorageMenu {
    private ImageView menuView;
    private Wallet wallet;

    private double x;
    private double y;
    private Pane root;
    private AnchorPane main;
    private Storage storage;
    private Button sellEggs;
    private Button sellMilk;
    private Button sellWool;
    private Button sellPigMeat;
    private Button sellRabbitMeat;
    private Button close;

    private Label eggsToSell;
    private Label milkToSell;
    private Label woolToSell;
    private Label pigMeatToSell;
    private Label rabbitMeatToSell;

    private TextField amountOfEggs;
    private TextField amountOfMilk;
    private TextField amountOfWool;
    private TextField amountOfPigMeat;
    private TextField amountOfRabbitMeat;

    public StorageMenu(Storage storage, Wallet wallet, double x, double y, AnchorPane main){

        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        menuView.setFitWidth(300);
        menuView.setFitHeight(420);
        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        this.main = main;

        this.x = x;
        this.y = y;

        this.storage = storage;
        this.wallet = wallet;

        close = new Button("×");
        close.setLayoutX(270);
        close.setLayoutY(10);

        eggsToSell = new Label("Яйця: "+storage.getProduct1()+"\nВведіть кількість для продажу:");
        eggsToSell.setLayoutX(20);
        eggsToSell.setLayoutY(20);
        amountOfEggs = new TextField();
        amountOfEggs.setLayoutX(20);
        amountOfEggs.setLayoutY(60);
        sellEggs = new Button("Продати");
        sellEggs.setLayoutX(180);
        sellEggs.setLayoutY(60);

        milkToSell = new Label("Молоко: "+storage.getProduct3()+"\nВведіть кількість для продажу:");
        milkToSell.setLayoutX(20);
        milkToSell.setLayoutY(100);
        amountOfMilk = new TextField();
        amountOfMilk.setLayoutX(20);
        amountOfMilk.setLayoutY(140);
        sellMilk = new Button("Продати");
        sellMilk.setLayoutX(180);
        sellMilk.setLayoutY(140);

        woolToSell = new Label("Хутро: "+storage.getProduct2()+"\nВведіть кількість для продажу:");
        woolToSell.setLayoutX(20);
        woolToSell.setLayoutY(180);
        amountOfWool = new TextField();
        amountOfWool.setLayoutX(20);
        amountOfWool.setLayoutY(220);
        sellWool = new Button("Продати");
        sellWool.setLayoutX(180);
        sellWool.setLayoutY(220);

        pigMeatToSell = new Label("Свинка: "+storage.getProduct4()+"\nВведіть кількість для продажу:");
        pigMeatToSell.setLayoutX(20);
        pigMeatToSell.setLayoutY(260);
        amountOfPigMeat = new TextField();
        amountOfPigMeat.setLayoutX(20);
        amountOfPigMeat.setLayoutY(300);
        sellPigMeat = new Button("Продати");
        sellPigMeat.setLayoutX(180);
        sellPigMeat.setLayoutY(300);

        rabbitMeatToSell = new Label("Кролик: "+storage.getProduct5()+"\nВведіть кількість для продажу:");
        rabbitMeatToSell.setLayoutX(20);
        rabbitMeatToSell.setLayoutY(340);
        amountOfRabbitMeat = new TextField();
        amountOfRabbitMeat.setLayoutX(20);
        amountOfRabbitMeat.setLayoutY(380);
        sellRabbitMeat = new Button("Продати");
        sellRabbitMeat.setLayoutX(180);
        sellRabbitMeat.setLayoutY(380);

        root = new Pane();
        root.setTranslateX(this.x);
        root.setTranslateY(this.y);
        root.getChildren().addAll(menuView, close, eggsToSell, amountOfEggs, sellEggs, milkToSell, amountOfMilk, sellMilk,
                woolToSell, amountOfWool, sellWool, pigMeatToSell, amountOfPigMeat, sellPigMeat, rabbitMeatToSell, amountOfRabbitMeat, sellRabbitMeat);



        close();
        sellEggs();
        sellMilk();
        sellWool();
        sellPigMeat();
        sellRabbitMeat();
    }

    private void close(){
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });
    }

    private void sellEggs(){
        sellEggs.setOnAction(event -> {
            String text = amountOfEggs.getText();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");

            if (!text.isEmpty()) {
                try {
                    int toSell = Integer.parseInt(text);
                    if (toSell >= 1 && toSell <= storage.getProduct1()) {
                        storage.sellProduct1(toSell);
                    }
                    else if(toSell<0){
                        alert.setContentText("Введіть додатнє число");
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("У Вас немає стільки яєць");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    alert.setContentText("Введіть кількість яєць для продажу");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Введіть кількість яєць для продажу");
                alert.showAndWait();
            }
        });
    }

    private void sellMilk(){
        sellMilk.setOnAction(event -> {
            String text = amountOfMilk.getText();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");

            if (!text.isEmpty()) {
                try {
                    int toSell = Integer.parseInt(text);
                    if (toSell >= 1 && toSell <= storage.getProduct3()) {
                        storage.sellProduct3(toSell);
                    }
                    else if(toSell<0){
                        alert.setContentText("Введіть додатнє число");
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("У Вас немає стільки молока");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    alert.setContentText("Введіть кількість галонів молока для продажу");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Введіть кількість галонів молока для продажу");
                alert.showAndWait();
            }
        });
    }

    private void sellWool(){
        sellWool.setOnAction(event -> {
            String text = amountOfWool.getText();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");

            if (!text.isEmpty()) {
                try {
                    int toSell = Integer.parseInt(text);
                    if (toSell >= 1 && toSell <= storage.getProduct2()) {
                        storage.sellProduct2(toSell);
                    }
                    else if(toSell<0){
                        alert.setContentText("Введіть додатнє число");
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("У Вас немає стільки хутра");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    alert.setContentText("Введіть кількість хутра для продажу");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Введіть кількість хутра для продажу");
                alert.showAndWait();
            }
        });
    }

    private void sellPigMeat(){
        sellPigMeat.setOnAction(event -> {
            String text = amountOfEggs.getText();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");

            if (!text.isEmpty()) {
                try {
                    int toSell = Integer.parseInt(text);
                    if (toSell >= 1 && toSell <= storage.getProduct4()) {
                        storage.sellProduct4(toSell);
                    }
                    else if(toSell<0){
                        alert.setContentText("Введіть додатнє число");
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("У Вас немає стільки свинини");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    alert.setContentText("Введіть кількість шматків свинини для продажу");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Введіть кількість шматків свинини для продажу");
                alert.showAndWait();
            }
        });
    }

    private void sellRabbitMeat(){
        sellRabbitMeat.setOnAction(event -> {
            String text = amountOfEggs.getText();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");

            if (!text.isEmpty()) {
                try {
                    int toSell = Integer.parseInt(text);
                    if (toSell >= 1 && toSell <= storage.getProduct5()) {
                        storage.sellProduct5(toSell);
                    }
                    else if(toSell<0){
                        alert.setContentText("Введіть додатнє число");
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("У Вас немає стільки кролятини");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    alert.setContentText("Введіть кількість кролятини для продажу");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Введіть кількість шматків кролятини для продажу");
                alert.showAndWait();
            }
        });
    }

    public Pane getRoot() {
        return root;
    }
}
