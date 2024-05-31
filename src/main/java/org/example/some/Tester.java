package org.example.some;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.some.animals.Feeder;
import org.example.some.animals.Sheep;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;

// Клас для тестування створених можливостей для додавання і взаємодії з тваринами
public class Tester extends Application {

    private Button button;
    Pane root;
    Well well;
    Feeder feeder;
    Wallet wallet;
    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();

        button = new Button("Додати вівцю");
        button.setLayoutX(350);
        button.setLayoutY(50);

        root.getChildren().add(button);
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();

//        addWell();
        addWallet();
//        addFeeder();
        addSheep();
    }

    private void addSheep(){
        button.setOnAction(event -> {
            Sheep sheep = new Sheep(200, 300,600,570, root, wallet, well, feeder);
            root.getChildren().add(sheep.getAnimalView());
            sheep.play();
        });
    }

    private void addWallet(){
        wallet = new Wallet(50,50, 100);
        root.getChildren().add(wallet.getRoot());
    }

//    private void addWell(){
//        well = new Well(20, 380);
//        root.getChildren().add(well.getRoot());
//    }

//    private void addFeeder(){
//        feeder = new Feeder(600, 450);
//        root.getChildren().add(feeder.getRoot());
//    }

    public static void main(String[] args) {
        launch(args);
    }

}
