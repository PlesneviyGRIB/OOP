package my.snake;

class SnakeController {
    private Snakes.Snake snake;

    SnakeController(Snakes.Snake snake){
        this.snake = snake;
    }

    void setDirection(Direction direction){
        snake.setDirection(direction);
    }
}
