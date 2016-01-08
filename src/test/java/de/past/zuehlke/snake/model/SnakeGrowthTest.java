package de.past.zuehlke.snake.model;

import com.google.common.collect.ImmutableList;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.Deque;

import static org.junit.Assert.*;

public class SnakeGrowthTest {
    @Test
    public void testGrowthOfSingleFood() {
        Food toConsume = new Food(Point2D.ZERO, 1);
        Snake snake = Snake.startingOffAt(Point2D.ZERO);

        assertEquals("Initial size too big", snake.getPoints().size(), 1);

        snake.consume(toConsume);
        assertEquals("Snake grew in size before advancing", snake.getPoints().size(), 1);

        snake.advance();
        assertEquals("Snake did not grow after advancing", snake.getPoints().size(), 2);
        assertEquals(snake.getPoints().getFirst(), new Point2D(1, 0));
        assertEquals(snake.getPoints().getLast(), Point2D.ZERO);
    }

    @Test
    public void testGrowthMultiValueFood() {
        Food toConsume = new Food(Point2D.ZERO, 3);
        Snake snake = Snake.startingOffAt(Point2D.ZERO);
        Deque<Point2D> points = snake.getPoints();

        snake.consume(toConsume);
        snake.advance();
        assertEquals(2, points.size());
        snake.advance();
        assertEquals(3, points.size());
        snake.advance();
        assertEquals(4, points.size());
        snake.advance();
        assertEquals(4, points.size());

        assertEquals(ImmutableList.of(
                new Point2D(4, 0),
                new Point2D(3, 0),
                new Point2D(2, 0),
                new Point2D(1, 0)
        ), points);
    }
}
