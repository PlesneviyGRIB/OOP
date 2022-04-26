package my.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception{
        SceneManager.getSceneManager().setStage(stage);
        SceneManager.getSceneManager().addScene(getScene("FirstPage.fxml"), SceneName.FIRST);
        SceneManager.getSceneManager().addScene(getScene("SettingsPage.fxml"), SceneName.SETTINGS);
        SceneManager.getSceneManager().select(SceneName.FIRST);
    }

    private Scene getScene(String url) throws Exception{
        return new Scene((new FXMLLoader(this.getClass().getClassLoader().getResource(url))).load());
    }

    public static void main(String... args) {
        launch();
    }
}
