package my.snake.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import my.snake.*;



public class FirstPageController {
    public static Scene mainScene;

    @FXML
    private ImageView buttonPlay;

    @FXML
    private ImageView buttonQuestion;

    @FXML
    private ImageView buttonSettings;


    private GameData getField() throws Exception {
        Pane pane = (Pane) SceneManager.getSceneManager().getScene(SceneName.SETTINGS).getRoot();

        int botsCount = (int) ((Slider) pane.getChildren().get(1)).getValue();
        int cntOfFood = (int) ((Slider) pane.getChildren().get(2)).getValue();
        int velocity = (int) ((Slider) pane.getChildren().get(6)).getValue();
        int size = Integer.parseInt( ((ChoiceBox) pane.getChildren().get(8)).getValue().toString());
        boolean secondPlayer = ((CheckBox) pane.getChildren().get(12)).isSelected();
        Complicity complicity = Complicity.valueOf(((ChoiceBox) pane.getChildren().get(3)).getValue().toString());

        Field field = new my.snake.Field(1000,600,  (600 / (1000 / size)), size, (1000/size));
        double tmp =((600 / (1000 / (double)size)) * (double) size) / 100;
        System.out.println("tmp " + tmp);
        cntOfFood = (int)(tmp * cntOfFood);
        System.out.println("Columns " + (600 / (1000 / size)));
        System.out.println("Rows " + size);
        System.out.println("Cnt " + cntOfFood);

        return new GameData(field, complicity, botsCount, cntOfFood,velocity, secondPlayer);
    }

    @FXML
    void playButtonMouseClicked(MouseEvent event) throws Exception{
        Pane pane = (new FXMLLoader(getClass().getClassLoader().getResource("PlayPage.fxml"))).load();
        ImageView button = (ImageView) pane.getChildren().get(0);
        pane.getChildren().clear();
        mainScene = new Scene(pane);
        Game game = new Game();

        pane.getChildren().addAll(game.play(getField()));
        pane.getChildren().add(button);

        SceneManager.getSceneManager().addScene(mainScene, SceneName.PLAY);
        SceneManager.getSceneManager().select(SceneName.PLAY);
    }
    @FXML
    void playButtonMouseEntered(MouseEvent event) {
        buttonPlay.setBlendMode(BlendMode.RED);
    }
    @FXML
    void playButtonMouseExited(MouseEvent event) {
        buttonPlay.setBlendMode(BlendMode.DARKEN);
    }

    @FXML
    void questionButtonMouseClicked(MouseEvent event) {}
    @FXML
    void questionButtonMouseEntered(MouseEvent event) {
        buttonQuestion.setBlendMode(BlendMode.RED);
    }
    @FXML
    void questionButtonMouseExited(MouseEvent event) {
        buttonQuestion.setBlendMode(BlendMode.DARKEN);
    }

    @FXML
    void settingsButtonMouseClicked(MouseEvent event) {
        SceneManager.getSceneManager().select(SceneName.SETTINGS);
    }
    @FXML
    void settingsButtonMouseEntered(MouseEvent event) {
        buttonSettings.setBlendMode(BlendMode.RED);
    }
    @FXML
    void settingsButtonMouseExited(MouseEvent event) {
        buttonSettings.setBlendMode(BlendMode.DARKEN);
    }
}