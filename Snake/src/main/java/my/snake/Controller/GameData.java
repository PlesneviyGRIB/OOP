package my.snake.Controller;

import my.snake.Complicity;

public class GameData {
    public final my.snake.Field field;
    public final Complicity complicity;
    public final int cntOfBoots;
    public final int cntOfFood;
    public final int velocity;

    public GameData(my.snake.Field field, Complicity complicity, int cntOfBoots, int cntOfFood, int velocity) {
        this.field = field;
        this.complicity = complicity;
        this.cntOfBoots = cntOfBoots;
        this.cntOfFood = cntOfFood;
        this.velocity = velocity;
    }
}
