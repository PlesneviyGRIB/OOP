package my.snake.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import my.snake.Main;
import my.snake.SceneManager;
import my.snake.SceneName;

import java.io.IOException;


public class PlayPageController {

    @FXML
    private ImageView pauseButton;

    @FXML
    void pauseButtonMouseClicked() throws IOException {
        Main.stopPlaying();
        Pane pane = (new FXMLLoader(getClass().getClassLoader().getResource("PausePage.fxml"))).load();

        SceneManager.getSceneManager().addScene(new Scene(pane), SceneName.PAUSE);
        SceneManager.getSceneManager().select(SceneName.PAUSE);
    }

    @FXML
    void pauseButtonMouseEntered() {
        pauseButton.setBlendMode(BlendMode.RED);
    }

    @FXML
    void pauseButtonMouseExited() {
        pauseButton.setBlendMode(BlendMode.DARKEN);
    }
}
