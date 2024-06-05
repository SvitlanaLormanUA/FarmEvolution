package org.example.some;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.some.animals.Feeder;
import org.example.some.animals.Monkey;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;
import org.example.some.products.Banana;

import java.io.*;
import java.net.URL;
import java.util.*;


public class SecondLevel  implements Initializable {

    public  Well well;
    public  Feeder feeder;
    public Storage storage;

    public static int countMonkeys = 1;
    public static Wallet wallet;
    public  AnchorPane anchorPane;
    public ImageView lian;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static int coins;


    public static ArrayList<Banana> bananas = new ArrayList<>();


    public static boolean bananaIsAdded = false;
    public static boolean productIsAdded = false;

    public static int countBanana = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMultiProducts();
        addWallet();
        loadState();
        addMonkey();


    }



    public void addMultiProducts(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (countBanana < 5) {
                    Platform.runLater(() -> addProducts());
                } else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10000);
    }
    public boolean addProducts() {
        Banana  banana = new Banana(100);
        anchorPane.getChildren().add(banana.getProductView());
        bananaIsAdded = true;
        countBanana++;
        banana.getProductView().setOnMouseClicked(event -> {
            SecondLevel.wallet.income(banana.getPrice());
            anchorPane.getChildren().remove(banana.getProductView());
            bananaIsAdded = false;
        });
        bananas.add(banana);
        return bananaIsAdded;

    }

    public void addThought(Monkey monkey) {
        if (bananaIsAdded && !productIsAdded) {
            monkey.giveProduct();
            monkey.updateProductViewPosition();
            productIsAdded = true;
        }
    }
    public void addWallet() {
        wallet = FirstLevel.wallet;
        anchorPane.getChildren().add(wallet.getRoot());
    }

    public void addMonkey() {
        Monkey monkey = new Monkey(250, 300, 600, 530, anchorPane, well, feeder, storage);
        anchorPane.getChildren().add(monkey.getAnimalView());
        if (addProducts() ) {
            addThought(monkey);
        }
        //  System.out.println(lian.getBoundsInParent());
    }
    @FXML
    public void backToMainMenu(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chooseSecondLevel.fxml")));

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void enterShop(ActionEvent event) {
        try {
            saveState();
            ShopFirstLevel.setCurrentLevel("secondLevel.fxml");

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopSecondLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //ВИДАЛИМО
    @FXML
    public void previousLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void nextLevel(ActionEvent event) {
        try {
            saveState();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void showTasks(ActionEvent event) {
    }

    public void showExtraTasks(ActionEvent event) {
    }

    public void showSettings(ActionEvent event) {
    }

    public void showInfo(ActionEvent event) {
    }

    static void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser"))) {
            out.writeInt(wallet.getCoins());
            out.writeInt(countMonkeys);








        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            coins = in.readInt();
            wallet.setCoins(coins);
            /* countMonkeys = in.readInt();*/

            setCoins(coins);
            wallet.setCoins(coins);
            wallet.nCoins.setText(String.valueOf(coins));
        } catch (IOException e) {
            coins = 0; // Default value if there's an error or the file doesn't exist
        }
    }
    public static void setCoins(int coins) {
        SecondLevel.coins = coins;
    }


}