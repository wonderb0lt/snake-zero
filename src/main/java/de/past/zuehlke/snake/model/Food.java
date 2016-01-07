package de.past.zuehlke.snake.model;

import javafx.geometry.Point2D;

/**
 * Entity representing a bit of food on the board.
 *
 * @TODO Neither spawned nor rendered yet
 */
public class Food {
    private Point2D position;
    private boolean consumed;
    private int value;

    public Food(Point2D position, int size) {
        this.position = position;
        this.value = size;
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
}
