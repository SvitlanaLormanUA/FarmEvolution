package org.example.some.otherGameObjects;


import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Well object class
 * */
public class Well {

    private ImageView wellView;
    private Pane root;
    private int waterLvl;
    Label waterLvlLabel;
    private double progress = 1.0;
    private ProgressBar waterBar;

    public Well(ImageView well, ProgressBar waterBar){
        this.wellView = well;
        this.wellView.setCursor(Cursor.HAND);
        this.waterBar = waterBar;
        this.waterLvl = 10;

        root = new Pane();
        root.getChildren().add(wellView);

        wellView.setOnMouseEntered(this::handleMouseEntered);
        wellView.setOnMouseExited(this::handleMouseExited);
        wellView.setOnMouseClicked(this::handleMouseClicked);
    }
/**
 * a  method to get water from the well
 *
 * */
    public void getWater(){
        if (progress > 0.0 && waterLvl > 0) {
            waterLvl--;
            progress -= 0.1;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");
            alert.setContentText("У Вас недостатньо води. Поповніть запаси!");
            alert.showAndWait();
        }
        waterBar.setProgress(progress);
    }

    /**
     * a method to handle the water level in the well
     * */
    private void handleMouseEntered(MouseEvent event){
        waterLvlLabel = new Label("Рівень води: " + this.waterLvl);
        waterLvlLabel.setLayoutX(300);
        waterLvlLabel.setLayoutY(300-20);
        root.getChildren().add(waterLvlLabel);
    }

    private void handleMouseExited(MouseEvent event) {
        root.getChildren().remove(waterLvlLabel);
    }


    private int n;
    /**
     * a method to higher the amount of water in the well when user clicks on it
     * */
    private void handleMouseClicked(MouseEvent event){
        n = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (n<10 && waterLvl < 10 && progress<1.0) {
                    waterLvl++;
                    waterLvlLabel.setText("Рівень води: " + waterLvl);

                    progress+=0.1;
                    waterBar.setProgress(progress);
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 10 секунд (10000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 10000);
    }

    /**
     * a method to get the well view
     * */
    public ImageView getWellView() {
        return wellView;
    }

    /**
     * a method to get the root of the well
     * */
    public Pane getRoot() {
        return root;
    }
}

