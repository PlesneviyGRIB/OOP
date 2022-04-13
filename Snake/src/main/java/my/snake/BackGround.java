package my.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class BackGround extends Canvas {
    private final Field field;

    BackGround(Field field){
        super(field.WIDTH(), field.HEIGHT());
        this.field = field;
        draw(getGraphicsContext2D());
    }

    private void draw(GraphicsContext graphicsContext){
        for(int i = 0 ; i<field.COLUMNS(); i++)
            for (int j = 0; j<field.ROWS(); j++){
                graphicsContext.setFill(Color.web((i+j) % 2 == 0? "AAD751":"A2D149"));
                graphicsContext.fillRect(i * field.SQUARE(), j * field.SQUARE(), field.SQUARE(), field.SQUARE());
            }
    }
}
