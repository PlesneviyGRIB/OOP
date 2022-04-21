package my.snake;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager sceneManager= new SceneManager();
    private SceneManager(){};
    private Map<SceneName, Scene> scenes = new HashMap<>();
    private Stage mainStage;

    public static SceneManager getSceneManager(){
        return sceneManager;
    }

    public void setStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    public void addScene(Scene scene, SceneName SceneName) {
        scenes.put(SceneName, scene);
    }

    public void select(SceneName sceneName){
        Scene scene = scenes.get(sceneName);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public Scene getScene(SceneName sceneName){
        return scenes.get(sceneName);
    }
}
