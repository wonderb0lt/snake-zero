package de.past.zuehlke.snake.model;

import de.past.zuehlke.snake.model.ending.*;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * A simple board which manages the basic rules of the game, but is not responsible for rendering the data.
 */
public class SnakeGame {
    private SnakeConfiguration config;
    /**
     * The primary snake on the board
     */
    private Snake primarySnake;

    /**
     * The food available for the snake on the field (limited by {@link SnakeConfiguration#parallelFood})
     */
    private Set<Food> spawnedFood;

    public SnakeGame() {
        this(SnakeConfiguration.defaultConfiguration());
    }

    public SnakeGame(SnakeConfiguration config) {
        this.config = config;
        primarySnake = Snake.startingOffAt(Point2D.ZERO);
        spawnedFood = new HashSet<>();

    }

    public void onTick() {
        primarySnake.advance();
        checkFoodConsumption();
        checkLoopAround();
        checkEndReason();
    }

    private void checkLoopAround() {
        if (config.isLoopAround()) {
            Deque<Point2D> points = primarySnake.getPoints();
            Point2D head = primarySnake.getHead();

            if (head.getX() < 0) {
                points.addFirst(points.pop().add(config.getFieldSize(), 0));
            } else if (head.getX() >= config.getFieldSize()) {
                points.addFirst(points.pop().add(-config.getFieldSize(), 0));
            } else if (head.getY() < 0) {
                points.addFirst(points.pop().add(0, config.getFieldSize()));
            } else if (head.getY() >= config.getFieldSize()) {
                points.addFirst(points.pop().add(0, -config.getFieldSize()));
            }
        }
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
        if (spawnedFood.size() < config.getParallelFood()) {
            Random random = new Random();
            Point2D point = new Point2D(random.nextInt(config.getFieldSize()), random.nextInt(config.getFieldSize()));

            // Loop till you found a place to spawn the food which is not within the snake.
            while (primarySnake.getPoints().contains(point)) {
                point = new Point2D(random.nextInt(config.getFieldSize()), random.nextInt(config.getFieldSize()));
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
        for (EndCondition endCondition : config.getEndConditions()) {
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

    public Set<Food> getSpawnedFood() {
        return spawnedFood;
    }

    public SnakeConfiguration getConfig() {
        return this.config;
    }
}
