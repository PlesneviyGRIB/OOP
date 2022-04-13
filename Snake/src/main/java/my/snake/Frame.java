package my.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;


public class Frame extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int COLUMNS = 20;
    private static final int ROWS = HEIGHT / (WIDTH / COLUMNS);
    private static final int SQUARE = WIDTH / COLUMNS;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Snake game");

        Field field = new Field(WIDTH,HEIGHT,ROWS,COLUMNS,SQUARE);

        BackGround backGround = new BackGround(field);
        Wall wall = new Wall(field,Complicity.HARD);
        Food food = new Food(field, 100);
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

        Group group = new Group();
        canvas.forEach(canvasItem -> group.getChildren().add(canvasItem));

        snakes.addBots(5);
        SnakeController snakeController = snakes.addPlayer();
        //SnakeController snakeController1 = snakes.addPlayer();

        Scene scene = new Scene(group);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.RIGHT) snakeController.setDirection(Direction.RIGHT);
            if (keyCode == KeyCode.UP) snakeController.setDirection(Direction.TOP);
            if (keyCode == KeyCode.LEFT) snakeController.setDirection(Direction.LEFT);
            if (keyCode == KeyCode.DOWN) snakeController.setDirection(Direction.BOTTOM);

//            if (keyCode == KeyCode.D) snakeController1.setDirection(Direction.RIGHT);
//            if (keyCode == KeyCode.W) snakeController1.setDirection(Direction.BOTTOM);
//            if (keyCode == KeyCode.A) snakeController1.setDirection(Direction.LEFT);
//            if (keyCode == KeyCode.S) snakeController1.setDirection(Direction.TOP);
        });

        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
        changeables.forEach(Changeable::change);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> {
            snakes.move();
            changeables.forEach(Changeable::change);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}