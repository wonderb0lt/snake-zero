package de.past.zuehlke.snake.model.ending;

import com.google.common.collect.ImmutableList;
import de.past.zuehlke.snake.model.AbstractGameTest;
import de.past.zuehlke.snake.model.Snake;
import de.past.zuehlke.snake.model.SnakeGame;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SelfCollisionTest extends AbstractGameTest {

    @Test
    public void testNonCollisionCase() {
        SnakeGame game = prepareGameWithSnakePoints(
                new Point2D(3, 0),
                new Point2D(2, 0),
                new Point2D(1, 0)
        );
        Optional<GameEndReason> reasonOptional = new SelfCollisionEndCondition().check(game);
        assertFalse("End reason was present even though no self-collision existed", reasonOptional.isPresent());
    }

    @Test
    public void testCollisionCase() {
        SnakeGame game = prepareGameWithSnakePoints(
                new Point2D(0, 0),
                new Point2D(0, 1),
                new Point2D(1, 1),
                new Point2D(1, 0),
                new Point2D(0, 0)
        );

        Optional<GameEndReason> reason = new SelfCollisionEndCondition().check(game);
        assertTrue(reason.isPresent());
        assertEquals(GameEndReason.SELF_COLLISION, reason.get());
    }
}
