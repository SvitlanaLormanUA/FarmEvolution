package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.some.otherGameObjects.Wallet;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.example.some.FirstLevel.saveState;
public class ShopFirstLevel extends Shop implements Initializable {
    private static final int COW_PRICE = 1000;
    private static final int GOOSE_PRICE = 200;
    private static final int PIG_PRICE = 530;
    private static final int SHEEP_PRICE = 648;
    private static final int RABBIT_PRICE = 350;


    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public  AnchorPane  anchorPane;

    private static int level;
    public ImageView coinsView;
    public ImageView backButtonView;
    public   ImageView imageView;
    public Button backButton;
    public ImageView closeButtonView;
    public Button closeButton;
    public Button buyPig;
    public Button buyCow;
    public Button buySheep;
    public Button buyRabbit;
    public Button buyGoose;


    public static int amountOfCoins;

    void addImageBasedOnPreviousLevel() {
        if (getCurrentLevel()!= null) {
            switch (getCurrentLevel()) {
                case "secondLevel.fxml":
                    //Second
                    addImage();
                    addWallet();
                    level=2;
                    break;
                case "thirdLevel.fxml":
                    //Third
                    addImage();
                    addWallet();
                    level=3;
                    break;
                default:
                    addWallet();
            }
        }
    }

    public void removeAnimalButtons() {
        anchorPane.getChildren().remove(buyCow);
        anchorPane.getChildren().remove(buyGoose);
        anchorPane.getChildren().remove(buyPig);
        anchorPane.getChildren().remove(buyRabbit);
        anchorPane.getChildren().remove(buySheep);
    }

    public void removeButtons() {
        anchorPane.getChildren().remove(backButtonView);
        anchorPane.getChildren().remove(backButton);
        anchorPane.getChildren().remove(backButtonView);
        anchorPane.getChildren().remove(coinsView);
        anchorPane.getChildren().remove(closeButton);
        anchorPane.getChildren().remove(closeButtonView);
        removeAnimalButtons();

    }
    public void returnBackButtons() {
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(coinsView);
        anchorPane.getChildren().add(backButtonView);
        anchorPane.getChildren().add(backButton);
        anchorPane.getChildren().add(closeButtonView);
        anchorPane.getChildren().add(closeButton);
    }
    public void addImage() {
         imageView = new ImageView(new Image("file:src/main/resources/images/shop/firstLevel/firstDone.png "));
        imageView.setFitWidth(IMAGE_WIDTH);  // задайте ширину
        imageView.setFitHeight(IMAGE_HEIGHT);// задайте висоту
        imageView.setX(0);
        imageView.setY(0);
          removeButtons();
          returnBackButtons();
        //if third level true -- return 3
        System.out.println("IN THE FIRST");

    }


    @FXML
    public void secondShop(ActionEvent event) {
        try {
            saveState();
           if( level == 2) {
               ShopFirstLevel.setCurrentLevel("secondLevel.fxml");
           } else if( level == 3) {
                ShopFirstLevel.setCurrentLevel("thirdLevel.fxml");
            } else {
               ShopFirstLevel.setCurrentLevel("firstLevel.fxml");
           }


            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopSecondLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }

    private void handleBuy(ActionEvent event, int price, String animalName, Runnable onSuccess) {
        if (wallet.getCoins() >= price) {
            FirstLevel.wallet.expense(price);
            wallet.expense(price);
            onSuccess.run();
            showAlert(Alert.AlertType.INFORMATION, "Ви купили " + animalName);
        } else {
            showAlert(Alert.AlertType.ERROR, "У Вас недостатньо монет для покупки " + animalName);
        }
    }

    @FXML
    public void buyCow(ActionEvent event) {
        handleBuy(event, COW_PRICE, "корову", () -> {
            FirstLevel.countCow++;
        });
    }

    @FXML
    public void buyGoose(ActionEvent event) {
        handleBuy(event, GOOSE_PRICE, "гуся", () -> {
            FirstLevel.countGoose++;
        });
    }

    @FXML
    public void buyPig(ActionEvent event) {
        handleBuy(event, PIG_PRICE, "cвинку", () -> {
            FirstLevel.countPig++;
        });
    }

    @FXML
    public void buySheep(ActionEvent event) {
        handleBuy(event, SHEEP_PRICE, "вівцю", () -> {
            FirstLevel.countSheep++;
        });
    }

    @FXML
    public void buyRabbit(ActionEvent event) {
        handleBuy(event, RABBIT_PRICE, "кролика", () -> {
            FirstLevel.countRabbit++;
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amountOfCoins = FirstLevel.wallet.getCoins();

        addImageBasedOnPreviousLevel();
      // addWallet();
    }




}
