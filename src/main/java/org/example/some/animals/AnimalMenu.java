package org.example.some.animals;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class AnimalMenu {

    private ImageView menuView;

    private double x;
    private double y;
    private Pane root;
    private Animal animal;
    private Button feed;
    private Button drink;
    private Button sell;
    private Button close;
    private Label hungerLabel;
    private Label thirstLabel;
    private Label costLabel;

    public AnimalMenu(Animal animal, double x, double y){
        Image image = new Image("file:src/main/resources/images/animalMenu.jpg");
        this.menuView = new ImageView(image);
        this.x = x;
        this.y = y;
        this.animal = animal;
        menuView.setFitWidth(200);
        menuView.setFitHeight(150);

        hungerLabel = new Label("Рівень ситості: " + this.animal.getHungerLvl());
        hungerLabel.setLayoutX(20);
        hungerLabel.setLayoutY(20);

        thirstLabel = new Label("Рівень води: " + this.animal.getThirstLvl());
        thirstLabel.setLayoutX(20);
        thirstLabel.setLayoutY(40);

        feed = new Button("Нагодувати");
        feed.setLayoutX(20);
        feed.setLayoutY(65);

        drink = new Button("Напоїти");
        drink.setLayoutX(110);
        drink.setLayoutY(65);

        close = new Button("×");
        close.setLayoutX(155);
        close.setLayoutY(20);

        costLabel = new Label("Ціна для продажу: " + this.animal.getCost());
        costLabel.setLayoutX(20); // Встановлюємо відносно меню
        costLabel.setLayoutY(100);

        sell = new Button("Продати");
        sell.setLayoutX(50);
        sell.setLayoutY(120);

        root = new Pane();
        root.setTranslateX(this.x+50);
        root.setTranslateY(this.y-20);
        root.getChildren().addAll(menuView, hungerLabel, thirstLabel, feed, drink, costLabel, sell, close);

        feed();
        drink();
        sell();
        close();
    }

    private void feed(){
        feed.setOnAction(event -> {
            animal.feed();
            hungerLabel.setText("Рівень ситості: " + this.animal.getHungerLvl());
        });
    }

    private void drink(){
        drink.setOnAction(event -> {
            animal.drink();
            thirstLabel.setText("Рівень води: " + this.animal.getThirstLvl());
        });
    }

    private void sell(){
        sell.setOnAction(event -> {
            animal.sell();
            AbstractAnimal.isOnScreen = false;
        });
    }

    private void close(){
        close.setOnAction(event -> {
            animal.removeMenu();
        });
    }

    public Pane getRoot() {
        return root;
    }

}

