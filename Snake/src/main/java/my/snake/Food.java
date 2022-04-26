package my.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.io.File;
import java.util.*;

public class Food extends Canvas implements Let, Changeable {
    private final Field field;
    private final Map<Point,Image> foodLocation = new HashMap<>();
    private final Random random = new Random();
    private PointsOfLets pointsOfLets = PointsOfLets.getInstance();

    private static final String PATH = "/home/egor/GitHub/OOP/Snake/src/main/resources/data/FoodImages";
    private static Image[] images;
    static{
        File[] imageFiles = new File(PATH).listFiles((dir, name) -> name.matches("\\w+.png"));
        images = new Image[imageFiles.length];

        for (int i = 0; i< images.length;i++)
            images[i] = new Image(imageFiles[i].toURI().toString());
    }

    Food(Field field, int cntOfFood) {
        super(field.WIDTH(),field.HEIGHT());
        this.field = field;

        for(int i = 0; i<cntOfFood; ++i) addFood();

        AllLets.getInstance().addLet(this);
        change();
    }

    private void addFood(){
        Point point;
        do {
            point = new Point(Math.abs(random.nextInt()) % field.COLUMNS(), Math.abs(random.nextInt()) % field.ROWS());
        } while (foodLocation.containsKey(point) || pointsOfLets.contains(point));

        pointsOfLets.addLetPoint(point);
        foodLocation.put(point, images[Math.abs(random.nextInt()) % images.length]);
    }

    private void rmFood(Point point){
        foodLocation.remove(point);
        addFood();
        pointsOfLets.rmLetPoint(point);
    }

    @Override
    public void change(){
        getGraphicsContext2D().clearRect(0,0, field.WIDTH(), field.HEIGHT());
        for (Map.Entry<Point,Image> entry: foodLocation.entrySet()){
            getGraphicsContext2D().drawImage(entry.getValue(), entry.getKey().x() * field.SQUARE(), entry.getKey().y() * field.SQUARE(), field.SQUARE(), field.SQUARE());
        }
    }

    @Override
    public void actionWithSnake(Snakes.Snake snake) {
        if(foodLocation.containsKey(snake.getHead())){
            rmFood(snake.getHead());
            snake.addNode();
        }
    }
}