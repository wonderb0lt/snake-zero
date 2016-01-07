package de.past.zuehlke.snake.model.ending;

import de.past.zuehlke.snake.model.SimpleBoard;

import java.util.Optional;

public interface EndCondition {
    Optional<GameEndReason> check(SimpleBoard board);
}
