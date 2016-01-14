package de.zuehlke.past.snake.model.ending;

import de.zuehlke.past.snake.model.SnakeGame;

import java.util.Optional;

/**
 * Created by past on 08.01.2016.
 */
public class WinningEndCondition implements EndCondition {
    @Override
    public Optional<GameEndReason> check(SnakeGame board) {
        if (board.getPrimarySnake().getPoints().size() >= board.getConfig().getWinningPoints()) {
            return Optional.of(GameEndReason.WON);
        } else {
            return Optional.empty();
        }
    }
}
