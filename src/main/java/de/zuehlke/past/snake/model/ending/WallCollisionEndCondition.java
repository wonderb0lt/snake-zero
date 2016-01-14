package de.zuehlke.past.snake.model.ending;

import de.zuehlke.past.snake.model.SnakeGame;
import javafx.geometry.Point2D;

import java.util.Optional;

public class WallCollisionEndCondition implements EndCondition {
    @Override
    public Optional<GameEndReason> check(SnakeGame board) {
        Point2D head = board.getPrimarySnake().getPoints().getFirst();

        if (head.getX() < 0 || head.getY() < 0 || head.getX() >= board.getConfig().getFieldSize() || head.getY() >= board.getConfig().getFieldSize()) {
            return Optional.of(GameEndReason.WALL_COLLISION);
        } else {
            return Optional.empty();
        }
    }
}
