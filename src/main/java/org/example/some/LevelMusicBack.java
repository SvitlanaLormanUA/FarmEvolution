package org.example.some;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

abstract class LevelMusicBack implements Initializable {
    public static MediaPlayer mediaPlayerBack;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMusic();
    }
    public void addMusic(){
        String musicFile = "src/main/resources/sound/background.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayerBack = new MediaPlayer(sound);
        mediaPlayerBack.setOnEndOfMedia(() -> {
            mediaPlayerBack.seek(mediaPlayerBack.getStartTime()); // Повертаємося на початок
            mediaPlayerBack.play(); // Починаємо грати знову
        });
        mediaPlayerBack.play();
        mediaPlayerBack.setVolume(SettingsMenu.lastVolume);
    }
}
