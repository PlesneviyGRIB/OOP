package my.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Wall extends Canvas implements Let{
    private Set<Point> points = new HashSet<>();
    private Field field;
    private Random random = new Random();

    Wall(Field field, Complicity complicity){
        super(field.WIDTH(), field.HEIGHT());
        this.field = field;
        AllLets.getInstance().addLet(this);
        generate(complicity);
        draw();
    }

    private void generate(Complicity complicity){
        int cntOfBars = (field.HEIGHT() / field.SQUARE()) * (field.WIDTH() / field.SQUARE());

        switch (complicity){
            case NOOB -> cntOfBars = 0;
            case EASY -> cntOfBars = (int)(cntOfBars * 0.04);
            case MEDIUM -> cntOfBars = (int)(cntOfBars * 0.10);
            case HARD -> cntOfBars = (int)(cntOfBars * 0.15);
        }

        for (int i = 0; i< cntOfBars; i++) generateWalls();
        frame();
    }

    private void generateWalls(){
        Point point;
        do {
            point = new Point(Math.abs(random.nextInt()) % field.COLUMNS(), Math.abs(random.nextInt()) % field.ROWS());
        } while (points.contains(point) || PointsOfLets.getInstance().contains(point));
        points.add(point);
        PointsOfLets.getInstance().addLetPoint(point);
    }

    private void frame(){
        Point point;
        for(int i = -1; i < field.ROWS()+1;i++) {
            point=new Point(-1, i);
            points.add(point);
            PointsOfLets.getInstance().addLetPoint(point);
            point=new Point(field.COLUMNS(), i);
            points.add(point);
            PointsOfLets.getInstance().addLetPoint(point);
        }
        for(int i = -1; i < field.COLUMNS()+1;i++) {
            point=new Point(i,-1);
            points.add(point);
            PointsOfLets.getInstance().addLetPoint(point);
            point=new Point(i, field.ROWS());
            points.add(point);
            PointsOfLets.getInstance().addLetPoint(point);
        }
    }

    boolean hasWall(Point point){
        return points.contains(point);
    }

    private void draw(){
        getGraphicsContext2D().setFill(Color.rgb(110,110,110));
        for(Point point: points)
            getGraphicsContext2D().fillRoundRect(point.x()* field.SQUARE(), point.y() * field.SQUARE(), field.SQUARE(), field.SQUARE(), field.SQUARE() / 2, field.SQUARE() / 2);
    }

    @Override
    public void actionWithSnake(Snakes.Snake snake) {
        if(points.contains(snake.getHead())) {
            snake.die();
            PointsOfLets.getInstance().addLetPoint(snake.getHead());
        }
    }
}
