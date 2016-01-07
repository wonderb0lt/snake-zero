package de.past.zuehlke.snake.model.ending;

import de.past.zuehlke.snake.model.SnakeGame;

import java.util.Optional;

public interface EndCondition {
    Optional<GameEndReason> check(SnakeGame board);
}
