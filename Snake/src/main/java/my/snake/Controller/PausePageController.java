package my.snake.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import my.snake.*;

public class PausePageController {

    @FXML
    private ImageView continueButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView restartButton;

    private GameData getField() throws Exception {
        Pane pane = (Pane) SceneManager.getSceneManager().getScene(SceneName.SETTINGS).getRoot();

        int botsCount = (int) ((Slider) pane.getChildren().get(1)).getValue();
        int cntOfFood = (int) ((Slider) pane.getChildren().get(2)).getValue();
        int velocity = (int) ((Slider) pane.getChildren().get(6)).getValue();
        int size = Integer.parseInt( ((ChoiceBox) pane.getChildren().get(8)).getValue().toString());
        Complicity complicity = Complicity.valueOf(((ChoiceBox) pane.getChildren().get(3)).getValue().toString());

        Field field = new my.snake.Field(1000,600,  (600 / (1000 / size)),size, (1000/size));
        System.out.println(field);
        System.out.println(cntOfFood  + " cntOfFood");
        System.out.println(field + " cntOfBots");
        System.out.println(velocity + " velocity");

        return new GameData(field, complicity, botsCount, cntOfFood,velocity);
    }

    @FXML
    void continueButtonMouseClicked(MouseEvent event) {
        Main.continuePlaying();
        SceneManager.getSceneManager().select(SceneName.PLAY);
    }

    @FXML
    void continueButtonMouseEntered(MouseEvent event) {
        continueButton.setBlendMode(BlendMode.RED);

    }

    @FXML
    void continueButtonMouseExited(MouseEvent event) {
        continueButton.setBlendMode(BlendMode.DARKEN);
    }

    @FXML
    void exitButtonMouseClicked(MouseEvent event) {
        Main.exitGame();
        SceneManager.getSceneManager().select(SceneName.FIRST);
    }

    @FXML
    void exitButtonMouseEntered(MouseEvent event) {
        exitButton.setBlendMode(BlendMode.RED);
    }

    @FXML
    void exitButtonMouseExited(MouseEvent event) {
        exitButton.setBlendMode(BlendMode.DARKEN);
    }

    @FXML
    void restartButtonMouseClicked(MouseEvent event) throws Exception{
        Main.exitGame();

        Pane pane = (new FXMLLoader(getClass().getClassLoader().getResource("PlayPage.fxml"))).load();
        ImageView button = (ImageView) pane.getChildren().get(0);
        pane.getChildren().clear();
        pane.getChildren().addAll(Main.play(getField()));
        pane.getChildren().add(button);

        SceneManager.getSceneManager().addScene(new Scene(pane), SceneName.PLAY);
        SceneManager.getSceneManager().select(SceneName.PLAY);
    }

    @FXML
    void restartButtonMouseEntered(MouseEvent event) {
        restartButton.setBlendMode(BlendMode.RED);
    }

    @FXML
    void restartButtonMouseExited(MouseEvent event) {
        restartButton.setBlendMode(BlendMode.DARKEN);
    }
}
