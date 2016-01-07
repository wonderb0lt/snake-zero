package de.past.zuehlke.snake.model.ending;

import com.google.common.collect.Sets;
import de.past.zuehlke.snake.model.SnakeGame;
import javafx.geometry.Point2D;

import java.util.Deque;
import java.util.Optional;
import java.util.Set;

/**
 * Created by past on 07.01.2016.
 */
public class SelfCollisionEndCondition implements EndCondition {
    @Override
    public Optional<GameEndReason> check(SnakeGame board) {
        Deque<Point2D> snakePoints = board.getPrimarySnake().getPoints();

        Set<Point2D> foundPoints = Sets.newHashSetWithExpectedSize(snakePoints.size());
        for (Point2D point : snakePoints) {
            if (foundPoints.contains(point)) {
                return Optional.of(GameEndReason.SELF_COLLISION);
            } else {
                foundPoints.add(point);
            }
        }

        return Optional.empty();
    }
}
