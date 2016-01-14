package de.zuehlke.past.snake.model.ending;

import de.zuehlke.past.snake.model.AbstractGameTest;
import de.zuehlke.past.snake.model.SnakeGame;
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

        // TODO: Get value from default game config
        SnakeGame game = prepareGameWithSnakePoints(new Point2D(20 + 1, 0), new Point2D(20, 0));
        Optional<GameEndReason> check = new WallCollisionEndCondition().check(game);

        Assert.assertTrue(check.isPresent());
        Assert.assertEquals(GameEndReason.WALL_COLLISION, check.get());
    }
}
