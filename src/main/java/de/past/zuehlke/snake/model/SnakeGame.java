package de.past.zuehlke.snake.model;

import com.google.common.collect.Sets;
import de.past.zuehlke.snake.model.ending.EndCondition;
import de.past.zuehlke.snake.model.ending.GameEndReason;
import de.past.zuehlke.snake.model.ending.SelfCollisionEndCondition;
import de.past.zuehlke.snake.model.ending.WallCollisionEndCondition;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * A simple board which manages the basic rules of the game, but is not responsible for rendering the data.
 */
public class SnakeGame {
    /**
     * The primary snake on the board
     */
    private Snake primarySnake;
    private Set<EndCondition> endConditions = Sets.newHashSet(
            new SelfCollisionEndCondition(),
            new WallCollisionEndCondition()
    );

    private Set<Food> spawnedFood;
    private int width;
    private int height;

    public SnakeGame() {
        primarySnake = Snake.startingOffAt(Point2D.ZERO);
        spawnedFood = new HashSet<>();
        width = 10;
        height = 10;
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
        primarySnake.setCurrentDirection(newDirection);
    }

    /**
     * Checks whether the game has ended
     *
     * @return A reason for the ending of the game, or an empty optional if none of the end-conditions are met.
     */
    public Optional<GameEndReason> checkEndReason() {
        for (EndCondition endCondition : endConditions) {
            Optional<GameEndReason> endReason = endCondition.check(this);

            if (endReason.isPresent()) {
                throw new RuntimeException("Game ended unexpectedly: " + endReason);
            }
        }

        return null;
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
