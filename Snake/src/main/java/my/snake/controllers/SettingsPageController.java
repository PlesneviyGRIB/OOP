package my.snake.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import my.snake.SceneManager;
import my.snake.SceneName;

public class SettingsPageController {

    @FXML
    private ChoiceBox<String> complicityChoiceBox;

    @FXML
    private ChoiceBox<Integer> fieldSizeChoiceBox;

    public void initialize(){
        complicityChoiceBox.getItems().add("NOOB");
        complicityChoiceBox.getItems().add("EASY");
        complicityChoiceBox.getItems().add("MEDIUM");
        complicityChoiceBox.getItems().add("HARD");

        fieldSizeChoiceBox.getItems().add(10);
        fieldSizeChoiceBox.getItems().add(20);
        fieldSizeChoiceBox.getItems().add(40);
        fieldSizeChoiceBox.getItems().add(50);
        fieldSizeChoiceBox.getItems().add(100);
    }

    @FXML
    void saveButtonMouseClicked(MouseEvent event) {
        SceneManager.getSceneManager().select(SceneName.FIRST);
    }
}
