package org.example.some;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.some.animals.AbstractAnimal;
import org.example.some.animals.Dragonfly;
import org.example.some.animals.Feeder;
import org.example.some.animals.Monkey;
import org.example.some.otherGameObjects.Instr;
import org.example.some.otherGameObjects.Wallet;
import org.example.some.otherGameObjects.Well;
import javafx.scene.control.ProgressBar;
import org.example.some.products.Banana;

import java.io.*;
import java.net.URL;
import java.util.*;


public class SecondLevel  extends LevelMusicBack implements Initializable {

    public static int WIDTH = 1255;
    public static int HEIGHT = 707;

    public  Well well;
    public  Feeder feeder;
    @FXML
    private ImageView feeder2View;
    @FXML
    private ProgressBar foodBar;

    public Storage storage;
    @FXML
    private ImageView storage2View;


    public static Wallet wallet;
    public  AnchorPane anchorPane;
    public ImageView lian;
    public ImageView background;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static int coins;
    private static SettingsMenu settingsMenu;

    public static int countMonkeys = 1;
    public static int countDragonflies = 1;



    public static boolean bananaIsAdded = false;
    public static boolean productIsAdded = false;
    private static boolean settingsShown = false;

    public static int countBanana = 0;
    public static ArrayList<Banana> bananas = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodBar.setStyle("-fx-accent: #f37a39;");

        //addMultiProducts();
        addWallet();
        loadState();
        addStorage();
        addFeeder();
        addMonkey();
        addDragonfly();
    }



    public void addProducts(Monkey monkey) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (countBanana < 5) {
                        Banana  banana = new Banana(100);
                        anchorPane.getChildren().add(banana.getProductView());
                        bananaIsAdded = true;
                       bananas.add(banana);
                        banana.getProductView().setOnMouseClicked(event -> {
                            banana.getProductView().setDisable(true);
                            AbstractAnimal.root.getChildren().remove(monkey.productView);
                            SecondLevel.productIsAdded = false;

                            monkey.goForBanana();
                            ;
                        });

                        countBanana++;
                    } else {
                        timer.cancel();
                    }

                });

            }

        };
        timer.scheduleAtFixedRate(task, 0, 3000);



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

    private void addFeeder(){
        feeder = new Feeder(feeder2View, foodBar, wallet, 54, anchorPane);
        anchorPane.getChildren().add(feeder.getFoodView());
    }

    public void addStorage(){
        Image banana = new Image("file:src/main/resources/images/secondLevel/products/banana.png");
        Image dragonflyPr = new Image("file:src/main/resources/images/secondLevel/products/dragonflyPr.png");
        Image feather = new Image("file:src/main/resources/images/secondLevel/products/feather.png");
        Image mango = new Image("file:src/main/resources/images/secondLevel/products/mango.png");
        Image nut = new Image("file:src/main/resources/images/secondLevel/products/nut.png");
        storage = new Storage(storage2View, wallet, banana, dragonflyPr, feather, mango, nut);
        storage.lvl2();
        anchorPane.getChildren().add(storage.getRoot());
    }

    public void addMonkey() {
        Monkey monkey = new Monkey(250, 300, 700, 700, anchorPane, well, feeder, storage);
        anchorPane.getChildren().add(monkey.getAnimalView());
        addProducts(monkey);
        addThought(monkey);

        //  System.out.println(lian.getBoundsInParent());
    }

    public void addDragonfly() {
        Dragonfly dragonfly = new Dragonfly(50, 50, 1200, 300, anchorPane, well, feeder, storage);
        anchorPane.getChildren().add(dragonfly.getAnimalView());
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

    @FXML
    void addStorageMenu(MouseEvent event) {
        StorageMenu storageMenu = new  StorageMenu(storage, wallet, WIDTH/2-140, HEIGHT/4-100, anchorPane);
        storageMenu.secondLvl();
        anchorPane.getChildren().add(storageMenu.getRoot());
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
         settingsMenu = new SettingsMenu(anchorPane);
        anchorPane.getChildren().add(settingsMenu.getRoot());
        countBanana = 10;
        //settingsMenu.applyBlur(true,  anchorPane);

    }


    public void showInfo(ActionEvent event) {
        Instr infoWindow = new Instr(anchorPane);
        infoWindow.createImagePane();
        anchorPane.getChildren().add(infoWindow.getRoot());
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
    public static void setCoins(int coins) {SecondLevel.coins = coins;}



}