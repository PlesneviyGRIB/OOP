package my.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import my.snake.controllers.FirstPageController;
import my.snake.controllers.GameData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Timeline timeline;

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
        SnakeController snakeController1 = snakes.addPlayer();

        FirstPageController.mainScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.RIGHT) snakeController.setDirection(Direction.RIGHT);
            if (keyCode == KeyCode.UP) snakeController.setDirection(Direction.TOP);
            if (keyCode == KeyCode.LEFT) snakeController.setDirection(Direction.LEFT);
            if (keyCode == KeyCode.DOWN) snakeController.setDirection(Direction.BOTTOM);

            if(gameData.secondPlayer) {
                if (keyCode == KeyCode.D) snakeController1.setDirection(Direction.RIGHT);
                if (keyCode == KeyCode.W) snakeController1.setDirection(Direction.TOP);
                if (keyCode == KeyCode.A) snakeController1.setDirection(Direction.LEFT);
                if (keyCode == KeyCode.S) snakeController1.setDirection(Direction.BOTTOM);
            }
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
        timeline.stop();
    }
}
