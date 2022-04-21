module my.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens my.snake to javafx.fxml;
    opens my.snake.Controller to javafx.fxml;

    exports my.snake;
}