package org.example.some.otherGameObjects;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class Well {

    private ImageView wellView;
    private double x;
    private double y;
    private Pane root;
    private int waterLvl;
    Label waterLvlLabel;

    public Well(double x, double y){
        Image image = new Image("file:src/main/resources/images/well.png");
        this.wellView = new ImageView(image);

        this.x = x;
        this.y = y;
        this.waterLvl = 10;
        wellView.setFitWidth(120);
        wellView.setFitHeight(180);
        wellView.setX(x);
        wellView.setY(y);

        root = new Pane();
        root.getChildren().add(wellView);

        root.setOnMouseEntered(this::handleMouseEntered);
        root.setOnMouseExited(this::handleMouseExited);
        root.setOnMouseClicked(this::handleMouseClicked);
    }

    public void getWater(){
        waterLvl--;
    }

    private void handleMouseEntered(MouseEvent event){
        waterLvlLabel = new Label("Рівень води: " + this.waterLvl);
        waterLvlLabel.setLayoutX(x);
        waterLvlLabel.setLayoutY(y-20);
        root.getChildren().add(waterLvlLabel);
    }

    private void handleMouseExited(MouseEvent event) {
        root.getChildren().remove(waterLvlLabel);
    }

    private void handleMouseClicked(MouseEvent event){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (waterLvl < 10) {
                    waterLvl++;
                    waterLvlLabel.setText("Рівень води: " + waterLvl);
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 10 секунд (10000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 10000);
    }

    public ImageView getWellView() {
        return wellView;
    }

    public Pane getRoot() {
        return root;
    }
}

