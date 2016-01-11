package de.past.zuehlke.snake.model.ending;

import de.past.zuehlke.snake.model.AbstractGameTest;
import de.past.zuehlke.snake.model.SnakeGame;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class WallCollisionTest extends AbstractGameTest {

    @Test
    public void testNotCollidingInWall() {
        SnakeGame game = prepareGameWithSnakePoints(new Point2D(2, 0), new Point2D(1, 0));
        Optional<GameEndReason> check = new WallCollisionEndCondition().check(game);
        Assert.assertFalse(check.isPresent());
    }

    @Test
    public void testCollidingWithZeroBound() {
        SnakeGame game = prepareGameWithSnakePoints(new Point2D(-1, 0), new Point2D(0, 0));
        Optional<GameEndReason> check = new WallCollisionEndCondition().check(game);

        Assert.assertTrue(check.isPresent());
        Assert.assertEquals(GameEndReason.WALL_COLLISION, check.get());
    }

    @Test
    public void testCollidingWithPositiveSide() {

        SnakeGame game = prepareGameWithSnakePoints(new Point2D(SnakeGame.DEFAULT_FIELD_SIZE + 1, 0), new Point2D(SnakeGame.DEFAULT_FIELD_SIZE, 0));
        Optional<GameEndReason> check = new WallCollisionEndCondition().check(game);

        Assert.assertTrue(check.isPresent());
        Assert.assertEquals(GameEndReason.WALL_COLLISION, check.get());
    }
}
