package de.zuehlke.past.snake.model;

import com.google.common.collect.ImmutableList;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeMovementTest {
    private void snakeShouldExistAt(Snake snake, Point2D... expectedPoints) {
        Deque<Point2D> snakePoints = snake.getPoints();
        Point2D[] actualPoints = snakePoints.toArray(new Point2D[snakePoints.size()]);


        Assert.assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testOnePointSnakeAdvancing() {
        Snake snake = Snake.startingOffAt(new Point2D(10, 10));

        snake.setCurrentDirection(Direction.EAST);
        snake.advance();
        snakeShouldExistAt(snake, new Point2D(11, 10));

        snake.setCurrentDirection(Direction.NORTH);
        snake.advance();
        snakeShouldExistAt(snake, new Point2D(11, 9));

        snake.setCurrentDirection(Direction.WEST);
        snake.advance();
        snakeShouldExistAt(snake, new Point2D(10, 9));

        snake.setCurrentDirection(Direction.SOUTH);
        snake.advance();
        snakeShouldExistAt(snake, new Point2D(10, 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCardinalDirectionChange() {
        Snake.startingOffAt(new Point2D(10, 10)).setCurrentDirection(Direction.WEST);
    }

    @Test
    public void testAdvancingWithLongerSnake() {
        Snake snake = new Snake(3, Direction.EAST, new ArrayDeque<>(ImmutableList.of(
                new Point2D(2, 0),
                new Point2D(1, 0),
                new Point2D(0, 0)
        )));

        snake.advance();

        snakeShouldExistAt(snake,
                new Point2D(3, 0),
                new Point2D(2, 0),
                new Point2D(1, 0)
        );
    }

}
