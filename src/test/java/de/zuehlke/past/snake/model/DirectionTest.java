package de.zuehlke.past.snake.model;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void testIsOpposite() {
        Assert.assertTrue(Direction.EAST.isOpposite(Direction.WEST));
        Assert.assertTrue(Direction.NORTH.isOpposite(Direction.SOUTH));

        Assert.assertFalse(Direction.WEST.isOpposite(Direction.NORTH));
        Assert.assertFalse(Direction.SOUTH.isOpposite(Direction.EAST));
    }
}
