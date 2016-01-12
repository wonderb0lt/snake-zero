package de.past.zuehlke.snake.model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class OnTickTest extends AbstractGameTest {
    @Test
    public void testSnakeAdvancesOnTick() {
        SnakeGame game = prepareGameWithSnakePoints(Point2D.ZERO);
        Snake snake = game.getPrimarySnake();
        game.onTick();


        Assert.assertEquals(1, snake.getPoints().size());
        Assert.assertEquals(new Point2D(1, 0), snake.getPoints().getFirst());
    }

    @Test
    public void testSnakeAdvanceWithLoopingEnabled() {
        SnakeConfiguration config = SnakeConfiguration.defaultConfiguration();
        config.setLoopAround(true);

        SnakeGame game = new SnakeGame(config);

        for (int i = 0; i < 11; i++) {
            game.onTick();
        }

        Assert.assertEquals(Point2D.ZERO, game.getPrimarySnake().getHead()); // Snake should have looped around
    }

    @Test
    public void foodIsSpawnedOnTick() {
        SnakeGame game = prepareGameWithSnakePoints(Point2D.ZERO);

        Assert.assertEquals("Game did not start off with no spawned food", 0, game.getSpawnedFood().size());
        game.onTick();
        Assert.assertEquals("Game did not spawn food after first tick", 1, game.getSpawnedFood().size());
        game.onTick();
        Assert.assertEquals("Food was removed after additional tick", 1, game.getSpawnedFood().size());

        game.getSpawnedFood().clear();
        Assert.assertEquals(0, game.getSpawnedFood().size());
        game.onTick();
        Assert.assertEquals("Food was not respawned after removal", 1, game.getSpawnedFood().size());

    }
}
