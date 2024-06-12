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
    private Button sellProduct1;
    private Button sellProduct2;
    private Button sellProduct3;
    private Button sellProduct4;
    private Button sellProduct5;
    private Button close;

    private Label product1ToSell;
    private Label product2ToSell;
    private Label product3ToSell;
    private Label product4ToSell;
    private Label product5ToSell;

    private TextField amountOfProduct1;
    private TextField amountOfProduct2;
    private TextField amountOfProduct3;
    private TextField amountOfProduct4;
    private TextField amountOfProduct5;

    public StorageMenu(Storage storage, Wallet wallet, double x, double y, AnchorPane main){


        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        menuView.setFitWidth(300);
        menuView.setFitHeight(500);
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

        root = new Pane();
        root.setTranslateX(this.x);
        root.setTranslateY(this.y);
        root.getChildren().addAll(menuView, close);


        close();
    }

    public void firstLvl(){
        product1ToSell = new Label("Яйця: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
        product1ToSell.setLayoutX(20);
        product1ToSell.setLayoutY(20);
        amountOfProduct1 = new TextField();
        amountOfProduct1.setLayoutX(20);
        amountOfProduct1.setLayoutY(75);
        sellProduct1 = new Button("Продати");
        sellProduct1.setLayoutX(180);
        sellProduct1.setLayoutY(75);

        product2ToSell = new Label("Молоко: "+storage.getProduct3()+"\nВартість: "+storage.getProduct3Cost()+" монет/шт\nВведіть кількість для продажу:");
        product2ToSell.setLayoutX(20);
        product2ToSell.setLayoutY(115);
        amountOfProduct2 = new TextField();
        amountOfProduct2.setLayoutX(20);
        amountOfProduct2.setLayoutY(170);
        sellProduct2 = new Button("Продати");
        sellProduct2.setLayoutX(180);
        sellProduct2.setLayoutY(170);

        product3ToSell = new Label("Хутро: "+storage.getProduct2()+"\nВартість: "+storage.getProduct2Cost()+" монет/шт\nВведіть кількість для продажу:");
        product3ToSell.setLayoutX(20);
        product3ToSell.setLayoutY(210);
        amountOfProduct3 = new TextField();
        amountOfProduct3.setLayoutX(20);
        amountOfProduct3.setLayoutY(265);
        sellProduct3 = new Button("Продати");
        sellProduct3.setLayoutX(180);
        sellProduct3.setLayoutY(265);

        product4ToSell = new Label("Свинка: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
        product4ToSell.setLayoutX(20);
        product4ToSell.setLayoutY(305);
        amountOfProduct4 = new TextField();
        amountOfProduct4.setLayoutX(20);
        amountOfProduct4.setLayoutY(360);
        sellProduct4 = new Button("Продати");
        sellProduct4.setLayoutX(180);
        sellProduct4.setLayoutY(360);

        product5ToSell = new Label("Кролик: "+storage.getProduct5()+"\nВартість: "+storage.getProduct5Cost()+" монет/шт\nВведіть кількість для продажу:");
        product5ToSell.setLayoutX(20);
        product5ToSell.setLayoutY(400);
        amountOfProduct5 = new TextField();
        amountOfProduct5.setLayoutX(20);
        amountOfProduct5.setLayoutY(455);
        sellProduct5 = new Button("Продати");
        sellProduct5.setLayoutX(180);
        sellProduct5.setLayoutY(455);

        root.getChildren().addAll(product1ToSell, amountOfProduct1, sellProduct1, product2ToSell, amountOfProduct2, sellProduct2,
                product3ToSell, amountOfProduct3, sellProduct3, product4ToSell, amountOfProduct4, sellProduct4, product5ToSell, amountOfProduct5, sellProduct5);

        sellEggs();
        sellMilk();
        sellWool();
        sellPigMeat();
        sellRabbitMeat();
    }

    public void secondLvl(){
        product1ToSell = new Label("Банани: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
        product1ToSell.setLayoutX(20);
        product1ToSell.setLayoutY(20);
        amountOfProduct1 = new TextField();
        amountOfProduct1.setLayoutX(20);
        amountOfProduct1.setLayoutY(75);
        sellProduct1 = new Button("Продати");
        sellProduct1.setLayoutX(180);
        sellProduct1.setLayoutY(75);

        product2ToSell = new Label("Бабка сушена: "+storage.getProduct2()+"\nВартість: "+storage.getProduct2Cost()+" монет/шт\nВведіть кількість для продажу:");
        product2ToSell.setLayoutX(20);
        product2ToSell.setLayoutY(115);
        amountOfProduct2 = new TextField();
        amountOfProduct2.setLayoutX(20);
        amountOfProduct2.setLayoutY(170);
        sellProduct2 = new Button("Продати");
        sellProduct2.setLayoutX(180);
        sellProduct2.setLayoutY(170);

        product3ToSell = new Label("Пір'я павича: "+storage.getProduct3()+"\nВартість: "+storage.getProduct3Cost()+" монет/шт\nВведіть кількість для продажу:");
        product3ToSell.setLayoutX(20);
        product3ToSell.setLayoutY(210);
        amountOfProduct3 = new TextField();
        amountOfProduct3.setLayoutX(20);
        amountOfProduct3.setLayoutY(265);
        sellProduct3 = new Button("Продати");
        sellProduct3.setLayoutX(180);
        sellProduct3.setLayoutY(265);

        product4ToSell = new Label("Манго: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
        product4ToSell.setLayoutX(20);
        product4ToSell.setLayoutY(305);
        amountOfProduct4 = new TextField();
        amountOfProduct4.setLayoutX(20);
        amountOfProduct4.setLayoutY(360);
        sellProduct4 = new Button("Продати");
        sellProduct4.setLayoutX(180);
        sellProduct4.setLayoutY(360);

        product5ToSell = new Label("Горіхи: "+storage.getProduct5()+"\nВартість: "+storage.getProduct5Cost()+" монет/шт\nВведіть кількість для продажу:");
        product5ToSell.setLayoutX(20);
        product5ToSell.setLayoutY(400);
        amountOfProduct5 = new TextField();
        amountOfProduct5.setLayoutX(20);
        amountOfProduct5.setLayoutY(455);
        sellProduct5 = new Button("Продати");
        sellProduct5.setLayoutX(180);
        sellProduct5.setLayoutY(455);

        root.getChildren().addAll(product1ToSell, amountOfProduct1, sellProduct1, product2ToSell, amountOfProduct2, sellProduct2,
                product3ToSell, amountOfProduct3, sellProduct3, product4ToSell, amountOfProduct4, sellProduct4, product5ToSell, amountOfProduct5, sellProduct5);

        sellBanana();
        sellDragonflyPr();
        sellFeather();
        sellMango();
        sellNut();
    }

    public void thirdLvl(){
        product1ToSell = new Label("Мішечки: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
        product1ToSell.setLayoutX(20);
        product1ToSell.setLayoutY(20);
        amountOfProduct1 = new TextField();
        amountOfProduct1.setLayoutX(20);
        amountOfProduct1.setLayoutY(75);
        sellProduct1 = new Button("Продати");
        sellProduct1.setLayoutX(180);
        sellProduct1.setLayoutY(75);

        product2ToSell = new Label("Пил феї: "+storage.getProduct2()+"\nВартість: "+storage.getProduct2Cost()+" монет/шт\nВведіть кількість для продажу:");
        product2ToSell.setLayoutX(20);
        product2ToSell.setLayoutY(115);
        amountOfProduct2 = new TextField();
        amountOfProduct2.setLayoutX(20);
        amountOfProduct2.setLayoutY(170);
        sellProduct2 = new Button("Продати");
        sellProduct2.setLayoutX(180);
        sellProduct2.setLayoutY(170);

        product3ToSell = new Label("Рога: "+storage.getProduct3()+"\nВартість: "+storage.getProduct3Cost()+" монет/шт\nВведіть кількість для продажу:");
        product3ToSell.setLayoutX(20);
        product3ToSell.setLayoutY(210);
        amountOfProduct3 = new TextField();
        amountOfProduct3.setLayoutX(20);
        amountOfProduct3.setLayoutY(265);
        sellProduct3 = new Button("Продати");
        sellProduct3.setLayoutX(180);
        sellProduct3.setLayoutY(265);

        product4ToSell = new Label("Кров єдинорога: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
        product4ToSell.setLayoutX(20);
        product4ToSell.setLayoutY(305);
        amountOfProduct4 = new TextField();
        amountOfProduct4.setLayoutX(20);
        amountOfProduct4.setLayoutY(360);
        sellProduct4 = new Button("Продати");
        sellProduct4.setLayoutX(180);
        sellProduct4.setLayoutY(360);

        product5ToSell = new Label("Гриби: "+storage.getProduct5()+"\nВартість: "+storage.getProduct5Cost()+" монет/шт\nВведіть кількість для продажу:");
        product5ToSell.setLayoutX(20);
        product5ToSell.setLayoutY(400);
        amountOfProduct5 = new TextField();
        amountOfProduct5.setLayoutX(20);
        amountOfProduct5.setLayoutY(455);
        sellProduct5 = new Button("Продати");
        sellProduct5.setLayoutX(180);
        sellProduct5.setLayoutY(455);

        root.getChildren().addAll(product1ToSell, amountOfProduct1, sellProduct1, product2ToSell, amountOfProduct2, sellProduct2,
                product3ToSell, amountOfProduct3, sellProduct3, product4ToSell, amountOfProduct4, sellProduct4, product5ToSell, amountOfProduct5, sellProduct5);

        sellPouch();


        sellUnicornBlood();
    }

    private void close(){
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });
    }

    private void sellEggs(){
        sellProduct1.setOnAction(event -> {
            if(sell(amountOfProduct1, storage.getProduct1())){
                int toSell = Integer.parseInt(amountOfProduct1.getText());
                storage.sellProduct1(toSell);
                product1ToSell.setText("Яйця: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct1.setText("");
            }
        });
    }

    private void sellMilk(){
        sellProduct2.setOnAction(event -> {
            if(sell(amountOfProduct2, storage.getProduct3())){
                int toSell = Integer.parseInt(amountOfProduct2.getText());
                storage.sellProduct3(toSell);
                product2ToSell.setText("Молоко: "+storage.getProduct3()+"\nВартість: "+storage.getProduct3Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct2.setText("");
            }
        });
    }

    private void sellWool(){
        sellProduct3.setOnAction(event -> {
            if(sell(amountOfProduct3, storage.getProduct2())){
                int toSell = Integer.parseInt(amountOfProduct3.getText());
                storage.sellProduct2(toSell);
                product3ToSell.setText("Хутро: "+storage.getProduct2()+"\nВартість: "+storage.getProduct2Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct3.setText("");
            }
        });
    }

    private void sellPigMeat(){
        sellProduct4.setOnAction(event -> {
            if(sell(amountOfProduct4, storage.getProduct4())){
                int toSell = Integer.parseInt(amountOfProduct4.getText());
                storage.sellProduct4(toSell);
                product4ToSell.setText("Свинка: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct4.setText("");
            }

        });
    }

    private void sellRabbitMeat(){
        sellProduct5.setOnAction(event -> {
            if(sell(amountOfProduct5, storage.getProduct5())){
                int toSell = Integer.parseInt(amountOfProduct5.getText());
                storage.sellProduct5(toSell);
                product5ToSell.setText("Кролик: "+storage.getProduct5()+"\nВартість: "+storage.getProduct5Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct5.setText("");
            }

        });
    }

    private void sellBanana(){
        sellProduct1.setOnAction(event -> {
            if(sell(amountOfProduct1, storage.getProduct1())){
                int toSell = Integer.parseInt(amountOfProduct1.getText());
                storage.sellBananas(toSell);
                product1ToSell.setText("Банани: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct1.setText("");
            }
        });
    }

    private void sellDragonflyPr(){
        sellProduct2.setOnAction(event -> {
            if(sell(amountOfProduct2, storage.getProduct2())){
                int toSell = Integer.parseInt(amountOfProduct2.getText());
                storage.sellProduct2(toSell);
                product2ToSell.setText("Бабка сушена: "+storage.getProduct2()+"\nВартість: "+storage.getProduct2Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct2.setText("");
            }
        });
    }

    private void sellFeather(){
        sellProduct3.setOnAction(event -> {
            if(sell(amountOfProduct3, storage.getProduct3())){
                int toSell = Integer.parseInt(amountOfProduct3.getText());
                storage.sellProduct3(toSell);
                product3ToSell.setText("Пір'я: "+storage.getProduct3()+"\nВартість: "+storage.getProduct3Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct3.setText("");
            }
        });
    }

    private void sellMango(){
        sellProduct4.setOnAction(event -> {
            if(sell(amountOfProduct4, storage.getProduct4())){
                int toSell = Integer.parseInt(amountOfProduct4.getText());
                storage.sellProduct4(toSell);
                product4ToSell.setText("Манго: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct4.setText("");
            }
        });
    }

    private void sellNut(){
        sellProduct5.setOnAction(event -> {
            if(sell(amountOfProduct5, storage.getProduct5())){
                int toSell = Integer.parseInt(amountOfProduct5.getText());
                storage.sellProduct5(toSell);
                product5ToSell.setText("Горіхи: "+storage.getProduct5()+"\nВартість: "+storage.getProduct5Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct5.setText("");
            }
        });
    }

    private void sellPouch(){
        sellProduct1.setOnAction(event -> {
            if(sell(amountOfProduct1, storage.getProduct1())){
                int toSell = Integer.parseInt(amountOfProduct1.getText());
                storage.sellProduct1(toSell);
                product1ToSell.setText("Мішечки: "+storage.getProduct1()+"\nВартість: "+storage.getProduct1Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct1.setText("");
            }
        });
    }

    private void sellUnicornBlood(){
        sellProduct4.setOnAction(event -> {
            if(sell(amountOfProduct4, storage.getProduct4())){
                int toSell = Integer.parseInt(amountOfProduct4.getText());
                storage.sellProduct4(toSell);
                product4ToSell.setText("Кров єдинорога: "+storage.getProduct4()+"\nВартість: "+storage.getProduct4Cost()+" монет/шт\nВведіть кількість для продажу:");
                amountOfProduct4.setText("");
            }
        });
    }

    private boolean sell(TextField amount, int nProd){
        String text = amount.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Помилка!");

        if (!text.isEmpty()) {
            try {
                int toSell = Integer.parseInt(text);
                if (toSell >= 1 && toSell <= nProd) {
                    return true;
                }
                else if(toSell<0){
                    alert.setContentText("Введіть додатнє число");
                    alert.showAndWait();
                    return false;
                }
                else {
                    alert.setContentText("У Вас немає стільки продукту");
                    alert.showAndWait();
                    return false;
                }
            } catch (NumberFormatException e) {
                alert.setContentText("Введіть кількість продукту для продажу");
                alert.showAndWait();
                return false;
            }
        } else {
            alert.setContentText("Введіть кількість продукту для продажу");
            alert.showAndWait();
            return false;
        }
    }

    public Pane getRoot() {
        return root;
    }
}