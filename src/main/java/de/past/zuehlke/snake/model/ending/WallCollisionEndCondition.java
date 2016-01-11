package de.past.zuehlke.snake.model.ending;

import de.past.zuehlke.snake.model.SnakeGame;
import javafx.geometry.Point2D;

import java.util.Optional;

public class WallCollisionEndCondition implements EndCondition {
    @Override
    public Optional<GameEndReason> check(SnakeGame board) {
        Point2D head = board.getPrimarySnake().getPoints().getFirst();

        if (head.getX() < 0 || head.getY() < 0 || head.getX() >= board.getWidth() || head.getY() >= board.getHeight()) {
            return Optional.of(GameEndReason.WALL_COLLISION);
        } else {
            return Optional.empty();
        }
    }
}
