package org.example.some;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ShopThirdLevel extends Shop implements javafx.fxml.Initializable {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static Wallet wallet;
    public ImageView imageView;
    public AnchorPane anchorPane;



    private static int level;
    public ImageView backButtonView;
    public ImageView closeButtonView;
    public Button closeButton;
    public ImageView coinsView;
    public Button backButton;

    private void addImageBasedOnPreviousLevel() {
        if (getCurrentLevel() != null) {
            switch (getCurrentLevel()) {
                case "firstLevel.fxml":
                   // wallet = ShopFirstLevel.wallet;
                    addImage("file:src/main/resources/images/shop/magical/magicalNotOpened.png");
                    addWallet();
                    level = 1;
                    break;
                case "secondLevel.fxml":
                   // wallet = FirstLevel.wallet;
                    addImage("file:src/main/resources/images/shop/magical/magicalNotOpened.png");
                    addWallet();
                    level = 2;

                    break;
                default:
                    addWallet();
                    level =3;
            }
        }
    }

    private void addImage(String imagePath) {

        imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(IMAGE_WIDTH);  // задайте ширину
        imageView.setFitHeight(IMAGE_HEIGHT);// задайте висоту
        imageView.setX(0);
        imageView.setY(0);
        removeButtons();
        addButtons();
        addWallet();

    }

    private void addButtons() {
        anchorPane.getChildren().add(imageView);

        anchorPane.getChildren().add(backButtonView);
        anchorPane.getChildren().add(closeButtonView);
        anchorPane.getChildren().add(closeButton);
        anchorPane.getChildren().add(coinsView);
        anchorPane.getChildren().add(backButton);
    }

    private void removeButtons() {
        anchorPane.getChildren().remove(backButton);
        anchorPane.getChildren().remove(backButtonView);
        anchorPane.getChildren().remove(closeButtonView);
        anchorPane.getChildren().remove(closeButton);
        anchorPane.getChildren().remove(coinsView);

    }


    public void secondShop(ActionEvent event) {
        try {
            saveState();
            if (level == 1)
                ShopSecondLevel.setCurrentLevel("firstLevel.fxml");
            else if (level ==2)
                ShopSecondLevel.setCurrentLevel("secondLevel.fxml");
            else if (level==3)
                ShopSecondLevel.setCurrentLevel("thirdLevel.fxml");

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopSecondLevel.fxml")));
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
