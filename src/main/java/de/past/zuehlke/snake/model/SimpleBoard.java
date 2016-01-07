package de.past.zuehlke.snake.model;

import com.google.common.collect.Sets;
import de.past.zuehlke.snake.model.ending.EndCondition;
import de.past.zuehlke.snake.model.ending.GameEndReason;
import de.past.zuehlke.snake.model.ending.SelfCollisionEndCondition;
import de.past.zuehlke.snake.model.ending.WallCollisionEndCondition;
import javafx.geometry.Point2D;

import java.util.Deque;
import java.util.Optional;
import java.util.Set;

/**
 * A simple board which manages the basic rules of the game, but is not responsible for rendering the data.
 */
public class SimpleBoard {
    /**
     * The primary snake on the board
     */
    private Snake primarySnake;
    private Set<EndCondition> endConditions = Sets.newHashSet(
            new SelfCollisionEndCondition(),
            new WallCollisionEndCondition()
    );

    private int width;
    private int height;

    public SimpleBoard() {
        primarySnake = Snake.startingOffAt(Point2D.ZERO);
        width = 10;
        height = 10;
    }

    public void onTick() {
        primarySnake.advance();
        checkEndReason();
    }

    public void onDirectionChange(Direction newDirection) {
        primarySnake.setCurrentDirection(newDirection);
    }

    /**
     * Checks whether the game has ended
     *
     * @return A reason for the ending of the game, or an empty optional if none of the end-conditions are met.
     */
    public Optional<GameEndReason> checkEndReason() {
        for (EndCondition endCondition : endConditions) {
            Optional<GameEndReason> endReason = endCondition.check(this);

            if (endReason.isPresent()) {
                throw new RuntimeException("Game ended unexpectedly: " + endReason);
            }
        }

        return null;
    }

    public Snake getPrimarySnake() {
        return primarySnake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
