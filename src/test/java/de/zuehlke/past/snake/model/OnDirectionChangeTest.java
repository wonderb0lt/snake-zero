package de.zuehlke.past.snake.model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class OnDirectionChangeTest extends AbstractGameTest {
    @Test
    public void testOnDirectionChange() {
        SnakeGame game = prepareGameWithSnakePoints(Point2D.ZERO);
        game.onDirectionChange(Direction.SOUTH);
        Assert.assertEquals(Direction.SOUTH, game.getPrimarySnake().getCurrentDirection());
    }
}
