package de.zuehlke.past.snake.model.ending;

import de.zuehlke.past.snake.model.AbstractGameTest;
import de.zuehlke.past.snake.model.SnakeGame;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class WinningEndConditionTest extends AbstractGameTest {
    @Test
    public void testWinning() {
        Point2D[] points = new Point2D[51];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(i, 0);
        }

        SnakeGame game = prepareGameWithSnakePoints(points);
        Optional<GameEndReason> reason = new WinningEndCondition().check(game);
        Assert.assertTrue(reason.isPresent());
        Assert.assertEquals(GameEndReason.WON, reason.get());
    }
}
