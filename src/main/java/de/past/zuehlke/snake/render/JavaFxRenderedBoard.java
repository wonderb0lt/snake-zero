package de.past.zuehlke.snake.render;

import de.past.zuehlke.snake.model.Food;
import de.past.zuehlke.snake.model.SnakeGame;
import de.past.zuehlke.snake.model.Snake;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A board which is rendered onto a JavaFX window
 */
public class JavaFxRenderedBoard extends SnakeGame {
    private static final int SCALE_FACTOR = 40;
    private Canvas canvas;

    public void initialize(Group parent) {
        this.canvas = new Canvas(getWidth() * SCALE_FACTOR, getHeight() * SCALE_FACTOR);
        Group boardGroup = new Group(this.canvas);


        GraphicsContext context = canvas.getGraphicsContext2D();

        drawBackground(context);
        drawSnake(context, getPrimarySnake());

        parent.getChildren().add(boardGroup);
    }

    private void drawSnake(GraphicsContext context, Snake snake) {
        context.setFill(Color.WHITE);
        for (Point2D point : snake.getPoints()) {
            context.fillRect(point.getX() * SCALE_FACTOR, point.getY() * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
        }
    }

    private void drawBackground(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawFood(GraphicsContext context) {
        for (Food food : getSpawnedFood()) {
            Point2D pos = food.getPosition();
            context.setFill(Color.BEIGE);
            context.fillOval(pos.getX() * SCALE_FACTOR, pos.getY() * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
        }
    }

    @Override
    public void onTick() {
        super.onTick();

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBackground(canvas.getGraphicsContext2D());
        drawSnake(canvas.getGraphicsContext2D(), getPrimarySnake());
        drawFood(canvas.getGraphicsContext2D());
    }
}
