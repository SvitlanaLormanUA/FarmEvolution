package org.example.some;

import javafx.event.ActionEvent;
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

public class ShopSecondLevel extends Shop implements Initializable {

    private final int PARROT_PRICE = 905;
    private final int MONKEY_PRICE = 1120;
    private final int PEACOCK_PRICE = 1545;
    private final int LEMUR_PRICE = 1700;
    private final int DRAGONFLY_PRICE = 700;

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public AnchorPane anchorPane;
    private static int level;
    public ImageView imageView;

    public ImageView backButtonView;
    public Button backButton;
    public ImageView closeButtonView;
    public Button closeButton;
    public ImageView coinsView;

    public Button nextButton;
    public ImageView nextButtonView;

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }

    private void handleBuy(ActionEvent event, int price, String animalName, Runnable onSuccess) {
        if (wallet.getCoins() >= price) {
            SecondLevel.wallet.expense(price);
            wallet.expense(price);

            onSuccess.run();
            showAlert(Alert.AlertType.INFORMATION, "Ви купили " + animalName);
        } else {
            showAlert(Alert.AlertType.ERROR, "У Вас недостатньо монет для покупки " + animalName);
        }
    }

    public void buyParrot(ActionEvent event) {
        handleBuy(event, PARROT_PRICE, "Папугу", () -> {
            SecondLevel.countParrots++;
        });
    }

    public void buyMonkey(ActionEvent event) {
        handleBuy(event, MONKEY_PRICE, "Мавпу", () -> {
            SecondLevel.countMonkeys++;
        });
    }

    public void buyPeacock(ActionEvent event) {
        handleBuy(event, PEACOCK_PRICE, "Павича", () -> {
            SecondLevel.countPeacocks++;
        });
    }

    public void buyLemur(ActionEvent event) {
        handleBuy(event, LEMUR_PRICE, "Лємура", () -> {
            SecondLevel.countLemurs++;
        });
    }

    public void buyDragonfly(ActionEvent event) {
        handleBuy(event, DRAGONFLY_PRICE, "Бабку", () -> {
            SecondLevel.countDragonflies++;
        });
    }

    private void addImageBasedOnPreviousLevel() {
        if (getCurrentLevel() != null) {
            switch (getCurrentLevel()) {
                case "firstLevel.fxml":
                    addImage("file:src/main/resources/images/shop/tropical/tropNotOpened.png");
                    addWallet();
                    level = 1;
                    break;
                case "thirdLevel.fxml":
                    addImage("file:src/main/resources/images/shop/tropical/tropicalDone.png");
                    addWallet();
                    level = 3;
                    break;
                default:
                    addWallet();
                    level = 2;
            }
        }
    }

    public void removeButtons() {
        anchorPane.getChildren().remove(backButton);
        anchorPane.getChildren().remove(backButtonView);
        anchorPane.getChildren().remove(closeButtonView);
        anchorPane.getChildren().remove(closeButton);
        anchorPane.getChildren().remove(coinsView);
        anchorPane.getChildren().remove(nextButton);
        anchorPane.getChildren().remove(nextButtonView);
    }

    public void addButtons() {
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(backButtonView);
        anchorPane.getChildren().add(closeButtonView);
        anchorPane.getChildren().add(closeButton);
        anchorPane.getChildren().add(coinsView);
        anchorPane.getChildren().add(nextButton);
        anchorPane.getChildren().add(nextButtonView);
        anchorPane.getChildren().add(backButton);
    }

    private void addImage(String imagePath) {
        imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setX(0);
        imageView.setY(0);
        removeButtons();
        addButtons();
        addWallet();
    }

    public void firstShop(ActionEvent event) {
        try {
            FirstLevel.saveState();
            SecondLevel.saveState();
            if (level == 1) {
                ShopFirstLevel.setCurrentLevel("firstLevel.fxml");
            } else if (level == 2) {
                ShopFirstLevel.setCurrentLevel("secondLevel.fxml");
            } else if (level == 3) {
                ShopFirstLevel.setCurrentLevel("thirdLevel.fxml");
            }

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopFirstLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void thirdShop(ActionEvent event) {
        try {
            FirstLevel.saveState();
            SecondLevel.saveState();
            if (level == 1) {
                ShopThirdLevel.setCurrentLevel("firstLevel.fxml");
            } else if (level == 2) {
                ShopThirdLevel.setCurrentLevel("secondLevel.fxml");
            } else if (level == 3) {
                ShopThirdLevel.setCurrentLevel("thirdLevel.fxml");
            }

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopThirdLevel.fxml")));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addImageBasedOnPreviousLevel();
    }
}
