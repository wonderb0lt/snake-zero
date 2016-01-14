package de.zuehlke.past.snake.model;

import javafx.scene.input.KeyCode;

import java.util.Optional;

/**
 * Represents a cardinal direction in which a snake can go.
 */
public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * @return true if the instance and the other direction lie in cardinally opposing directions
     */
    public boolean isOpposite(Direction otherDirection) {
        return (
                (this == NORTH && otherDirection == SOUTH)
                        || (this == SOUTH && otherDirection == NORTH)
                        || (this == EAST && otherDirection == WEST)
                        || (this == WEST && otherDirection == EAST)
        );
    }

    /**
     * Maps a key code to a cardinal direction, assuming NORTH == UP and EAST == RIGHT.
     *
     * @return An optional containing the mapped direction, or an empty Optional when the key is not one of UP/DOWN/LEFT/RIGHT
     */
    public static Optional<Direction> mapKeyCode(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                return Optional.of(NORTH);
            case DOWN:
                return Optional.of(SOUTH);
            case RIGHT:
                return Optional.of(EAST);
            case LEFT:
                return Optional.of(WEST);
            default:
                return Optional.empty();
        }
    }
}
