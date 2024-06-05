package org.example.some.animals;

import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.some.AskingMenu;
import org.example.some.FirstLevel;
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
    private AnchorPane anchorPane;

    public Feeder(ImageView foodView, ProgressBar foodBar, Wallet wallet, AnchorPane anchorPane){
        this.foodView = foodView;
        this.foodView.setCursor(Cursor.HAND);
        this.foodBar = foodBar;
        this.wallet = wallet;
        this.foodLvl = 10;
        this.anchorPane = anchorPane;

        root = new Pane();
        root.getChildren().add(this.foodView);

        this.foodView.setOnMouseClicked(this::handleMouseClicked);
    }

    public void getFood(){
        if (progress > 0.0 && foodLvl > 0) {
            foodLvl--;
            progress -= 0.1;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Помилка!");
            alert.setContentText("У Вас недостатньо їжі. Поповніть запаси!");
            alert.showAndWait();
        }
        foodBar.setProgress(progress);
    }


//    потрібно буде щось зробити із зняттям гроше + при натисканні на колодязь чомусь теж знімаються гроші
    private void handleMouseClicked(MouseEvent event){
        if(progress < 1.0) {
            AskingMenu askingMenu = new AskingMenu("\t   Чи бажаєте ви поповнити годівничку за\n\t\t\t\t 54 монети?", FirstLevel.WIDTH / 3 + 60, FirstLevel.HEIGHT / 3);
            Button yesButton = askingMenu.getYes();
            Button noButton = askingMenu.getNo();
            Button closeButton = askingMenu.getClose();

            yesButton.setOnAction(even -> {
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
                wallet.expense(54);
                anchorPane.getChildren().remove(askingMenu.getRoot());
            });

            noButton.setOnAction(eve -> {
                anchorPane.getChildren().remove(askingMenu.getRoot());
            });

            closeButton.setOnAction(ev -> {
                anchorPane.getChildren().remove(askingMenu.getRoot());
            });

            anchorPane.getChildren().add(askingMenu.getRoot());
        }
    }

    public ImageView getFoodView() {
        return foodView;
    }

    public Pane getRoot() {
        return root;
    }

}

