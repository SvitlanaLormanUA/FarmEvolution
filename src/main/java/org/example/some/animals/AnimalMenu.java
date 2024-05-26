package org.example.some.animals;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AnimalMenu {

    private ImageView menuView;

    private double x;
    private double y;
    private Pane root;
    private Animal animal;
    private Button feed;
    private Button drink;
    private Button sell;
    private Label hungerLabel;
    private Label thirstLabel;
    private Label costLabel;

    public AnimalMenu(Animal animal, double x, double y){
        Image image = new Image("file:src/main/resources/images/animalMenuBack.jpg");
        this.menuView = new ImageView(image);
        this.x = x;
        this.y = y;
        this.animal = animal;
        menuView.setFitWidth(200);
        menuView.setFitHeight(200);

        hungerLabel = new Label("Рівень ситості: " + this.animal.getHungerLvl());
        hungerLabel.setLayoutX(20);
        hungerLabel.setLayoutY(20);

        thirstLabel = new Label("Рівень води: " + this.animal.getThirstLvl());
        thirstLabel.setLayoutX(20);
        thirstLabel.setLayoutY(40);

        feed = new Button("Нагодувати");
        feed.setLayoutX(50);
        feed.setLayoutY(70);

        drink = new Button("Напоїти");
        drink.setLayoutX(50);
        drink.setLayoutY(100);

        costLabel = new Label("Ціна для продажу: " + this.animal.getCost());
        costLabel.setLayoutX(20); // Встановлюємо відносно меню
        costLabel.setLayoutY(120);

        sell = new Button("Продати");
        sell.setLayoutX(50);
        sell.setLayoutY(150);

        root = new Pane();
        root.setTranslateX(this.x+50);
        root.setTranslateY(this.y-20);
        root.getChildren().addAll(menuView, hungerLabel, thirstLabel, feed, drink, costLabel, sell);

        feed();
        drink();
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

    public Pane getRoot() {
        return root;
    }

}

