package de.past.zuehlke.snake.model.ending;

import de.past.zuehlke.snake.model.SimpleBoard;
import javafx.geometry.Point2D;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created by past on 07.01.2016.
 */
public class WallCollisionEndCondition implements EndCondition {
    @Override
    public Optional<GameEndReason> check(SimpleBoard board) {
        Point2D head = board.getPrimarySnake().getPoints().getFirst();

        if (head.getX() < 0 || head.getY() < 0 || head.getX() >= board.getWidth() || head.getY() >= board.getHeight()) {
            return Optional.of(GameEndReason.WALL_COLLISION);
        } else {
            return Optional.empty();
        }
    }
}
