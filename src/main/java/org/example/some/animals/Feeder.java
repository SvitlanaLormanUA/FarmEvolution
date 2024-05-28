package org.example.some.animals;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.some.otherGameObjects.Wallet;

import java.util.Timer;
import java.util.TimerTask;

public class Feeder {

    private ImageView foodView;
    private ProgressBar foodBar;
    private double progress = 1.0;
    private Pane root;
    private int foodLvl;
    private Wallet wallet;

    public Feeder(ImageView foodView, ProgressBar foodBar, Wallet wallet){
        this.foodView = foodView;
        this.foodBar = foodBar;
        this.wallet = wallet;
        this.foodLvl = 10;

        root = new Pane();
        root.getChildren().add(foodView);

        root.setOnMouseClicked(this::handleMouseClicked);
    }

    public void getFood(){
        if (progress > 0.0 && foodLvl > 0) {
            foodLvl--;
            progress -= 0.1;
        }
        foodBar.setProgress(progress);
    }


//    потрібно буде щось зробити із зняттям гроше + при натисканні на колодязь чомусь теж знімаються гроші
    private void handleMouseClicked(MouseEvent event){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (foodLvl < 10 && progress < 1) {
                    foodLvl++;

                    progress += 0.1;
                    foodBar.setProgress(progress);
                } else {
                    timer.cancel();
                }
            }
        };
        // Запуск завдання з інтервалом 10 секунд (10000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 10000);
        wallet.expense(10);
    }

    public ImageView getFoodView() {
        return foodView;
    }

    public Pane getRoot() {
        return root;
    }

}

