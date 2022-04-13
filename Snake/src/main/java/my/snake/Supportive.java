package my.snake;


enum Direction { LEFT,TOP,RIGHT,BOTTOM;}
enum Type { PLAYER, BOT;}
enum Complicity{ EASY, MEDIUM, HARD;}
enum LetType {FOOD, WALL, SNAKE}

interface Let { void actionWithSnake(Snakes.Snake snake); }
interface Changeable { void change(); }

record Field(int WIDTH, int HEIGHT, int ROWS, int COLUMNS, int SQUARE) {}
record Point(int x, int y) {}