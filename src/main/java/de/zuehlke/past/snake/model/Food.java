package de.zuehlke.past.snake.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Entity representing a bit of food on the board, with a specific color
 */
public class Food {
    private Point2D position;
    private Color color;
    private boolean consumed;
    private int value;

    public Food(Point2D position, int size) {
        this.position = position;
        this.value = size;

        Random random = new Random();
        this.color = Color.hsb(random.nextFloat() * 360, random.nextFloat(), 0.6 + (random.nextFloat() * 0.4));
    }

    public Point2D getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public void wasConsumed() {
        this.consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public Color getColor() {
        return color;
    }
}
