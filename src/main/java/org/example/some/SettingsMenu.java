package org.example.some;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.some.LevelMusicBack;

import java.io.*;

import static org.example.some.LevelMusicBack.mediaPlayerBack;

public class SettingsMenu {
    private Pane root;
    public ImageView close;
    public Label volumeLabel;
    public Slider volumeLevel;

    private final double MENU_WIDTH = 307;
    private final double MENU_HEIGHT = 507;

    private static double lastVolume; // Default volume value

    SettingsMenu(AnchorPane anchorPane) {
        if (!new File("settings.dat").exists()) {
            lastVolume = 100.0;
        }
        loadSettings();
        addPane(anchorPane);
        addCLosingForMenu(anchorPane);
        addVolume();
    }

    private void addPane(AnchorPane anchorPane) {
        root = new Pane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.63); -fx-background-radius: 10; -fx-background-insets: 0; -fx-padding: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.63), 10, 0, 0, 0);");
        root.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        root.setLayoutX(500);
        root.setLayoutY(100);
    }

    public void addCLosingForMenu(AnchorPane anchorPane) {
        close = new ImageView("file:src/main/resources/images/menuSettings/closingForMenu.png");
        close.setFitWidth(30);
        close.setFitHeight(30);
        close.setLayoutX(270);
        close.setLayoutY(10);
        root.getChildren().add(close);
        close.setOnMouseClicked(event -> {
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
        volumeLabel.setLayoutY(40);
        volumeLabel.setStyle("-fx-font-size: 20; -fx-text-fill: white;");
        root.getChildren().add(volumeLabel);
    }

    public void addVolumeSlider() {
        volumeLevel = new Slider(0, 100, lastVolume); // Min: 0, Max: 100, Initial: lastVolume (default volume)
        volumeLevel.setLayoutX(30);
        volumeLevel.setLayoutY(80);
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
