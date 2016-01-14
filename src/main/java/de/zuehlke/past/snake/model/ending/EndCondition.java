package de.zuehlke.past.snake.model.ending;

import de.zuehlke.past.snake.model.SnakeGame;

import java.util.Optional;

public interface EndCondition {
    Optional<GameEndReason> check(SnakeGame board);
}
