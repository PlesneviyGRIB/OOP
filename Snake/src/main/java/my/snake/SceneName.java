package my.snake;

public enum SceneName{ FIRST, PLAY, PAUSE, SETTINGS }
enum Direction { LEFT,TOP,RIGHT,BOTTOM }
enum Type { PLAYER, BOT }

interface Let { void actionWithSnake(Snakes.Snake snake); }
interface Changeable { void change(); }

record Point(int x, int y) {}