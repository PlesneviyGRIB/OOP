package my.snake.controllers;

import my.snake.Complicity;

public class GameData {
    public final my.snake.Field field;
    public final Complicity complicity;
    public final int cntOfBoots;
    public final int cntOfFood;
    public final int velocity;
    public final boolean secondPlayer;

    public GameData(my.snake.Field field, Complicity complicity, int cntOfBoots, int cntOfFood, int velocity, boolean secondPlayer) {
        this.field = field;
        this.complicity = complicity;
        this.cntOfBoots = cntOfBoots;
        this.cntOfFood = cntOfFood;
        this.velocity = velocity;
        this.secondPlayer = secondPlayer;
    }
}
