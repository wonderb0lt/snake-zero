package de.past.zuehlke.snake.model;

import com.google.common.collect.Sets;
import de.past.zuehlke.snake.model.ending.*;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * A simple board which manages the basic rules of the game, but is not responsible for rendering the data.
 */
public class SnakeGame {
    // TODO: These should be configurable instead of being public constants!
    public static final int WINNING_POINTS = 50;
    public static final int TICK_SPEED_IN_MS = 200;
    public static final int DEFAULT_FIELD_SIZE = 10;
    /**
     * The primary snake on the board
     */
    private Snake primarySnake;
    private Set<EndCondition> endConditions = Sets.newHashSet(
            new SelfCollisionEndCondition(),
            new WallCollisionEndCondition(),
            new WinningEndCondition()
    );

    private Set<Food> spawnedFood;
    private int width;
    private int height;

    public SnakeGame() {
        primarySnake = Snake.startingOffAt(Point2D.ZERO);
        spawnedFood = new HashSet<>();
        width = DEFAULT_FIELD_SIZE;
        height = DEFAULT_FIELD_SIZE;
    }

    public void onTick() {
        primarySnake.advance();
        checkFoodConsumption();
        checkEndReason();
    }

    protected void checkFoodConsumption() {
        for (Food food : spawnedFood) {
            if (food.getPosition().equals(primarySnake.getHead())) {
                primarySnake.consume(food);
                food.wasConsumed();
            }

        }

        spawnedFood.removeIf(Food::isConsumed);
        spawnFood();
    }

    protected void spawnFood() {
        if (spawnedFood.size() == 0) {
            Random random = new Random();
            Point2D point = new Point2D(random.nextInt(width), random.nextInt(height));
            while (primarySnake.getPoints().contains(point)) {
                point = new Point2D(random.nextInt(width), random.nextInt(height));
            }

            spawnedFood.add(new Food(point, 1));
        }
    }

    public void onDirectionChange(Direction newDirection) {
        try {
            primarySnake.setCurrentDirection(newDirection);
        } catch (IllegalArgumentException e) {
            // Drop exception on wrong user input
        }
    }

    /**
     * Checks whether the game has ended
     *
     * @return A reason for the ending of the game, or an empty optional if none of the end-conditions are met.
     */
    private Optional<GameEndReason> checkEndReason() {
        for (EndCondition endCondition : endConditions) {
            Optional<GameEndReason> endReason = endCondition.check(this);

            if (endReason.isPresent()) {
                endGame(endReason.get());
            }
        }

        return Optional.empty();
    }

    protected void endGame(GameEndReason endReason) {
        // No implementation in base game, but there will be an end to the game in actual rendering classes
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

    public Set<Food> getSpawnedFood() {
        return spawnedFood;
    }
}
