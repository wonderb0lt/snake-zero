package de.zuehlke.past.snake.model;

import javafx.geometry.Point2D;

import java.util.Arrays;

/**
 * Utility methods for tests which test the {@link SnakeGame} class
 */
public class AbstractGameTest {
    protected SnakeGame prepareGameWithSnakePoints(Point2D... points) {
        SnakeGame game = new SnakeGame();
        Snake snake = game.getPrimarySnake();
        snake.getPoints().clear();
        snake.getPoints().addAll(Arrays.asList(points));
        return game;
    }
}
