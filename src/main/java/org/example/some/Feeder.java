package org.example.some;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Feeder {

    private ImageView foodView;
    private double x;
    private double y;
    private Pane root;
    private int foodLvl;
    ArrayList<ImageView> foodLvlScale;
    int setEmpty;

    public Feeder(double x, double y){
        Image image = new Image("file:src/main/resources/images/food.png");
        this.foodView = new ImageView(image);

        this.x = x;
        this.y = y;
        this.foodLvl = 10;
        this.foodLvlScale = new ArrayList<>();
        this.setEmpty = 0;
        foodView.setFitWidth(180);
        foodView.setFitHeight(120);
        foodView.setX(x);
        foodView.setY(y);

        root = new Pane();
        Image imageLvl = new Image("file:src/main/resources/images/foodLvl.png");
        int n = 0;
        for(int i=0; i<10; i++){
            ImageView lvlImageView = new ImageView(imageLvl);
            lvlImageView.setFitWidth(15);
            lvlImageView.setFitHeight(15);
            lvlImageView.setX(x - 20);
            lvlImageView.setY(y + 110 - n);
            n += 15;
            this.foodLvlScale.add(lvlImageView);
            root.getChildren().add(lvlImageView);
        }

        root.getChildren().add(foodView);

        root.setOnMouseClicked(this::handleMouseClicked);
    }

    public void getFood(){
        if (foodLvl > 0 && setEmpty < foodLvlScale.size()) {
            foodLvl--;
            foodLvlScale.remove(setEmpty);
            setEmpty++;
        }
    }


//    не виходить реалізувати зміну шкали
    private void handleMouseClicked(MouseEvent event){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (foodLvl < 10 && setEmpty > 0) {
                    foodLvl++;
                    setEmpty--;
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 10 секунд (10000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 10000);
    }

    public ImageView getFoodView() {
        return foodView;
    }

    public Pane getRoot() {
        return root;
    }

}

