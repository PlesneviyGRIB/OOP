package my.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import my.snake.Controller.FirstPageController;
import my.snake.Controller.GameData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static int COLUMNS = 100;
    private static final int ROWS = HEIGHT / (WIDTH / COLUMNS);
    private static final int SQUARE = WIDTH / COLUMNS;

    private static Timeline timeline;
    //new Field(WIDTH,HEIGHT,ROWS,COLUMNS,SQUARE);

    public static List<Canvas> play(GameData gameData) throws IOException {

        Field field = gameData.field;
        BackGround backGround = new BackGround(field);
        Wall wall = new Wall(field,gameData.complicity);
        Food food = new Food(field, gameData.cntOfFood);
        Snakes snakes  = new Snakes(field);


        List<Changeable> changeables = new ArrayList<>();
        changeables.add(food);
        changeables.add(snakes);
        changeables.add(AllLets.getInstance());

        List<Canvas> canvas = new ArrayList<>();
        canvas.add(backGround);
        canvas.add(wall);
        canvas.add(food);
        canvas.add(snakes);

        snakes.addBots(gameData.cntOfBoots);
        SnakeController snakeController = snakes.addPlayer();

        FirstPageController.mainScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.RIGHT) snakeController.setDirection(Direction.RIGHT);
            if (keyCode == KeyCode.UP) snakeController.setDirection(Direction.TOP);
            if (keyCode == KeyCode.LEFT) snakeController.setDirection(Direction.LEFT);
            if (keyCode == KeyCode.DOWN) snakeController.setDirection(Direction.BOTTOM);
        });

        changeables.forEach(Changeable::change);

        timeline = new Timeline(new KeyFrame(Duration.millis(501 - gameData.velocity), event -> {
            snakes.move();
            changeables.forEach(Changeable::change);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return canvas;
    }

    public static void stopPlaying(){
        timeline.stop();
    }

    public static void continuePlaying(){
        timeline.play();
    }

    public static void exitGame(){
        PointsOfLets.getInstance().newObject();
        AllLets.getInstance().newObject();
        Snakes.newObject();
        timeline.stop();
    }

    private Scene getScene(String url) throws Exception{
        return new Scene((new FXMLLoader(this.getClass().getClassLoader().getResource(url))).load());
    }

    public void start(Stage stage) throws Exception{
        SceneManager.getSceneManager().setStage(stage);
        SceneManager.getSceneManager().addScene(getScene("FirstPage.fxml"), SceneName.FIRST);
        SceneManager.getSceneManager().addScene(getScene("SettingsPage.fxml"), SceneName.SETTINGS);
        SceneManager.getSceneManager().addScene(getScene("PausePage.fxml"), SceneName.PAUSE);
        SceneManager.getSceneManager().select(SceneName.FIRST);
    }

    public static void main(String... args) {
        launch();
    }
}
