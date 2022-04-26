package my.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.util.*;
import java.util.stream.Collectors;

public class Snakes extends Canvas implements Changeable {

    class Snake implements Let{
        private long smoothStart = 5;

        private Point head;
        private Queue<Point> body = new LinkedList<>();
        private Direction currentDirection;

        private boolean isAlive = true;
        private boolean grow;
        private boolean canChange;
        private final Type type;


        Snake(Point head, Direction direction, Type type){
            this.head = head;
            this.currentDirection = direction;
            this.type = type;
            snakes.add(this);
            AllLets.getInstance().addLet(this);
        }

        void move(){
            if(isAlive) {
                canChange = true;
                body.add(head);
                head = newHead(currentDirection, head);
                PointsOfLets.getInstance().addLetPoint(head);
                if (!grow) PointsOfLets.getInstance().rmLetPoint(body.remove());
                grow = false;
                checkLets();
            }
        }

        void addNode(){
            grow = true;
            PointsOfLets.getInstance().addLetPoint(head);
        }

        void setDirection(Direction direction){
            if(canChange){
                if(direction.ordinal() % 2 != currentDirection.ordinal() % 2)
                    currentDirection = direction;
                canChange = false;
            }
        }

        void die(){
            PointsOfLets.getInstance().rmLetPoint(head);
            PointsOfLets.getInstance().rmLetPoint(new ArrayList<>(body));
            isAlive = false;
        }

        boolean containsNode(Point point){
            return body.contains(point);
        }

        boolean isAlive(){
            return isAlive;
        }

        Point getHead(){
            return head;
        }

        List<Point> getBody(){
            return body.stream().toList();
        }

        private Point newHead(Direction current, Point head){
            switch (current){
                case LEFT ->   { return new Point(head.x() - 1,head.y()); }
                case TOP ->    { return new Point(head.x(),head.y() - 1); }
                case RIGHT ->  { return new Point(head.x() + 1,head.y()); }
                case BOTTOM -> { return new Point(head.x(),head.y() + 1); }
                default ->     { return head; }
            }
        }

        private void checkLets(){
            if(type == Type.PLAYER && smoothStart-- > 0) return;
            for(Let let: AllLets.getInstance().getLets()) {
                let.actionWithSnake(this);
            }
        }

        void cutBody(Point point){
            Point tmp;
            if(body.contains(point)){
                do{
                    tmp = body.poll();
                    PointsOfLets.getInstance().rmLetPoint(tmp);
                }
                while(!tmp.equals(point));
            }
        }

        Direction getCurrentDirection(){
            return currentDirection;
        }

        @Override
        public void actionWithSnake(Snake snake) {
            if(!this.isAlive || !snake.isAlive) return;
            if(snake == this && body.contains(snake.getHead())) {
                die();
                return;
            }
            if(snake != this && head.equals(snake.head)){
                die();
                snake.die();
                return;
            }
            if(body.contains(snake.getHead())){
                cutBody(snake.getHead());
                snake.addNode();
            }
        }
    }

    private class InitSnake{
        private final Random random = new Random();
        private final Field field;
        private PointsOfLets pointsOfLets = PointsOfLets.getInstance();

        public InitSnake(Field field) {
            this.field = field;
        }

        Snakes.Snake next(Type type){
            Point head;
            Direction direction = Direction.values()[Math.abs(random.nextInt()) % Direction.values().length];

            do {
                head = new Point(Math.abs(random.nextInt(field.COLUMNS())), Math.abs(random.nextInt(field.ROWS())));
            } while(pointsOfLets.contains(head));

            pointsOfLets.addLetPoint(head);

            return new Snakes.Snake(head, direction, type);
        }
    }

    private List<Snake> snakes = new ArrayList<>();
    private final Field field;
    private final InitSnake initSnake;
    private List<SnakeBotController> snakeBotControllers = new ArrayList<>();

    Snakes(Field field){
        super(field.WIDTH(), field.HEIGHT());
        this.field = field;
        initSnake = new InitSnake(field);
    }


    void addBots(int cntOfSnakeBots){
        for (int i = 0; i <  cntOfSnakeBots; i++) {
            snakeBotControllers.add(new SnakeBotController(initSnake.next(Type.BOT)));
        }
    }
    
    SnakeController addPlayer(){
        return new SnakeController(initSnake.next(Type.PLAYER));
    }

    void move(){
        ruleBots();
        for(Snake snake: snakes) snake.move();
    }

    private void ruleBots(){
        int i = 0;
        for(SnakeBotController snakeBotController: snakeBotControllers) if(!snakeBotController.botAlive()) i++;
        addBots(i);
        snakeBotControllers = snakeBotControllers.stream().filter(SnakeBotController::botAlive).collect(Collectors.toList());
        snakeBotControllers.forEach(SnakeBotController::guess);
    }

    @Override
    public void change() {
        getGraphicsContext2D().clearRect(0,0, field.WIDTH(), field.HEIGHT());
        snakes.forEach(snake -> {
            switch (snake.type){
                case BOT -> {drawSnake(snake, Color.rgb(255, 0,0), Color.rgb(255,130,205)); return;}
                case PLAYER -> drawSnake(snake, Color.rgb(0,0,255), Color.rgb(0,230,255));
                default -> drawSnake(snake, Color.rgb(0,0,0), Color.rgb(100,100,100));
            }
        });
    }

    private void drawSnake(Snake snake, Color head, Color body){
        if(!snake.isAlive()) return;

        getGraphicsContext2D().setFill(body);
        for (Point point: snake.getBody())
            getGraphicsContext2D().fillRoundRect(point.x() * field.SQUARE(), point.y() * field.SQUARE(), field.SQUARE(), field.SQUARE(), field.SQUARE() - 8, field.SQUARE() - 8);

        getGraphicsContext2D().setFill(head);
        getGraphicsContext2D().fillRoundRect(snake.getHead().x() * field.SQUARE(), snake.getHead().y() * field.SQUARE(), field.SQUARE(), field.SQUARE(), field.SQUARE(), field.SQUARE());
    }
}
