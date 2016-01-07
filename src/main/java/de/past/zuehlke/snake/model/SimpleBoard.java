package de.past.zuehlke.snake.model;

import javafx.geometry.Point2D;

/**
 * A simple board which manages the basic rules of the game, but is not responsible for rendering the data.
 */
public class SimpleBoard {
    /**
     * The primary snake on the board
     */
    private Snake primarySnake;
    private int width;
    private int height;

    public SimpleBoard() {
        primarySnake = Snake.startingOffAt(Point2D.ZERO);
        width = 10;
        height = 10;
    }

    public void onTick() {
        primarySnake.advance();
    }

    public void onDirectionChange(Direction newDirection) {
        primarySnake.setCurrentDirection(newDirection);
    }

    public Snake getPrimarySnake() {
        return primarySnake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
