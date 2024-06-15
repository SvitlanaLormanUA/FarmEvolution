package org.example.some;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.some.FirstLevel.wallet;
import static org.example.some.LevelMusicBack.mediaPlayerBack;

public class TheEnd {

    public AnchorPane anchorPane;
    public Stage stage;
    private Pane root;
    private Scene scene;

   /*
   * method to start game again
   * @param event
   * */

    public void beginAgain(ActionEvent event) {
        SettingsMenu.restart = true;
        SettingsMenu.start = true;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        mediaPlayerBack.stop();

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

        // Storage.reset();
        wallet.resetWallet();
        wallet.setCoins(100);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } catch (
    IOException e) {
        throw new RuntimeException(e);
    }

    }

}
