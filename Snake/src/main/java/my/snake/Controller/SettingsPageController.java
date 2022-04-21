package my.snake.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import my.snake.SceneManager;
import my.snake.SceneName;

public class SettingsPageController {

    @FXML
    private Slider botsCount;

    @FXML
    private CheckBox botsStatus;

    @FXML
    private ChoiceBox<String> complicityChoiceBox;

    @FXML
    private ChoiceBox<Integer> fieldSizeChoiceBox;

    @FXML
    private Slider countOfFood;

    @FXML
    private Button saveButton;

    @FXML
    private Slider velocityGrade;

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

    @FXML
    void saveButtonMouseEntered(MouseEvent event) {

    }

    @FXML
    void savebuttonMouseExited(MouseEvent event) {

    }
}
