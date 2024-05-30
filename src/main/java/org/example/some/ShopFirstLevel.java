package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.some.FirstLevel.saveState;

public class ShopFirstLevel {
    private static final int COW_PRICE = 1000;
    private static final int GOOSE_PRICE = 200;
    private static final int PIG_PRICE = 530;
    private static final int SHEEP_PRICE = 648;
    private static final int RABBIT_PRICE = 350;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void backToGame(ActionEvent event) {
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
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }

    private void handleBuy(ActionEvent event, int price, String animalName, Runnable onSuccess) {
        if (FirstLevel.wallet.getCoins() >= price) {
            FirstLevel.wallet.expense(price);
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
        handleBuy(event, PIG_PRICE , "cвинку", () -> {
            FirstLevel.countPig++;
        });
    }
    @FXML
    public void buySheep(ActionEvent event) {
        handleBuy(event, SHEEP_PRICE , "вівцю", () -> {
            FirstLevel.countSheep++;
        });
    }
    @FXML
    public void buyRabbit(ActionEvent event) {
        handleBuy(event, RABBIT_PRICE , "кролика", () -> {
            FirstLevel.countRabbit++;
        });
    }


}
