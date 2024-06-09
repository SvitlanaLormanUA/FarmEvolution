package org.example.some;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.some.LevelMusicBack;
import org.example.some.otherGameObjects.Wallet;

import java.io.*;
import java.util.Objects;

import static org.example.some.FirstLevel.*;
import static org.example.some.LevelMusicBack.mediaPlayerBack;

public class SettingsMenu {
    private Pane root;
    public ImageView close;
    public Label volumeLabel;
    public Slider volumeLevel;
    public Button startAgain;

    private Stage stage;
    private Scene scene;
    private AnchorPane anchorPane;

    private final double MENU_WIDTH = 300;
    private final double MENU_HEIGHT = 400;

    static double lastVolume;
    public static boolean start = false; // Default volume value

    SettingsMenu(AnchorPane anchorPane) {
        if (!new File("settings.dat").exists()) {
            lastVolume = 100.0;
        }
        this.anchorPane = anchorPane;
        loadSettings();
        addPane(anchorPane);
        addCLosingForMenu(anchorPane);
        addVolume();
        addStartAgainButton();
    }

    private void askUser() {
        AskingMenu askingMenu = new AskingMenu("Ви впевнені, що хочете почати спочатку?  \n  Весь Ваш прогрес на всіх рівнях буде втрачено", 500, 250);
        Button yesButton = askingMenu.getYes();
        Button noButton = askingMenu.getNo();
        Button closeButton = askingMenu.getClose();

        yesButton.setOnAction(event -> {
            try{

                start = true;
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                mediaPlayerBack.stop();
                anchorPane.getChildren().remove(askingMenu.getRoot());
                mediaPlayerBack.seek(mediaPlayerBack.getStartTime());
                mediaPlayerBack.play();
                FirstLevel.countCow = 1;

                FirstLevel.countSheep = 1;
                FirstLevel.countPig = 1;
                FirstLevel.countGoose = 1;
                FirstLevel.countRabbit = 1;




                SecondLevel.countBanana = 0;
                SecondLevel.countMonkeys = 1;
                SecondLevel.countDragonflies = 1;
                wallet.resetWallet();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        noButton.setOnAction(event -> {
            anchorPane.getChildren().remove(askingMenu.getRoot());
        });
        closeButton.setOnAction(event -> {
            anchorPane.getChildren().remove(askingMenu.getRoot());
        });
        anchorPane.getChildren().add(askingMenu.getRoot());

    }
    private void addStartAgainButton() {
        startAgain = new Button("Почати знову");
        startAgain.setLayoutX(30);
        startAgain.setLayoutY(200);
        startAgain.setPrefSize(251, 25);
        startAgain.setStyle("-fx-background-color: #2D819D; -fx-text-fill: white; -fx-font-size: 20; -fx-border-radius: 100px;");
        startAgain.setOnAction(event -> {
            startAgain.setStyle("-fx-background-color: #3aacd3; -fx-text-fill: white; -fx-font-size: 20; -fx-border-radius: 100px;");
            wallet.setCoins(100);
            askUser();
        });
        root.getChildren().add(startAgain);
    }


    private void addPane(AnchorPane anchorPane) {
        root = new Pane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.63); -fx-background-radius: 10; -fx-background-insets: 0; -fx-padding: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.63), 10, 0, 0, 0);");
        root.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        root.setLayoutX(500);
        root.setLayoutY(150);
    }

    public void addCLosingForMenu(AnchorPane anchorPane) {
        close = new ImageView("file:src/main/resources/images/menuSettings/closingForMenu.png");
        close.setFitWidth(30);
        close.setFitHeight(30);
        close.setLayoutX(250);
        close.setLayoutY(10);
        root.getChildren().add(close);
        close.setOnMouseClicked(event -> {
            close.setStyle("-fx-cursor: hand;");
            anchorPane.getChildren().remove(root);
            lastVolume = volumeLevel.getValue();
            saveSettings(); // Save the last volume before closing
        });
    }

    public void addVolume() {
        addVolumeLabel();
        addVolumeSlider();
        volumeLevel.setValue(lastVolume); // Set the volume slider value
        mediaPlayerBack.setVolume(lastVolume / 100); // Set the volume for mediaPlayerBack
    }

    public void addVolumeLabel() {
        volumeLabel = new Label("Музика");
        volumeLabel.setLayoutX(MENU_WIDTH / 2 - 30);
        volumeLabel.setLayoutY(70);
        volumeLabel.setStyle("-fx-font-size: 20; -fx-text-fill: white;");
        root.getChildren().add(volumeLabel);
    }

    public void addVolumeSlider() {
        volumeLevel = new Slider(0, 100, lastVolume); // Min: 0, Max: 100, Initial: lastVolume (default volume)
        volumeLevel.setLayoutX(30);
        volumeLevel.setLayoutY(120);
        volumeLevel.setPrefSize(251, 25);

        volumeLevel.valueProperty().addListener((observable, oldValue, newValue) -> {
            String style = String.format("-fx-border-radius: 100px; -fx-background-color: linear-gradient(to right, #2D819D %d%%, #969696 %d%%)",
                    newValue.intValue(), newValue.intValue());
            volumeLevel.setStyle(style);
            lastVolume = newValue.doubleValue(); // Update lastVolume
            mediaPlayerBack.setVolume(lastVolume / 100); // Update volume for mediaPlayerBack
        });

        root.getChildren().add(volumeLevel);
    }

    public Pane getRoot() {
        return root;
    }

    private void saveSettings() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("settings.dat"))) {
            outputStream.writeDouble(lastVolume);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("settings.dat"))) {
            lastVolume = inputStream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
