package de.past.zuehlke.snake.model;

import com.google.common.collect.Sets;
import de.past.zuehlke.snake.model.ending.EndCondition;
import de.past.zuehlke.snake.model.ending.SelfCollisionEndCondition;
import de.past.zuehlke.snake.model.ending.WallCollisionEndCondition;
import de.past.zuehlke.snake.model.ending.WinningEndCondition;

import java.util.Set;

/**
 * Configuration details for the game
 */
public class SnakeConfiguration {
    private int resolution;
    private int winningPoints;
    private int fieldSize;
    private boolean loopAround;
    private int parallelFood;
    private int tickSpeed;
    private Set<EndCondition> endConditions;

    public static SnakeConfiguration defaultConfiguration() {
        SnakeConfiguration config = new SnakeConfiguration();
        config.setResolution(800);
        config.setWinningPoints(50);
        config.setFieldSize(20);
        config.setLoopAround(true);
        config.setParallelFood(1);
        config.setEndConditions(Sets.newHashSet(
                new SelfCollisionEndCondition(),
                new WallCollisionEndCondition(),
                new WinningEndCondition()

        ));
        config.setTickSpeed(175);

        return config;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getWinningPoints() {
        return winningPoints;
    }

    public void setWinningPoints(int winningPoints) {
        this.winningPoints = winningPoints;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public boolean isLoopAround() {
        return loopAround;
    }

    public void setLoopAround(boolean loopAround) {
        this.loopAround = loopAround;
    }

    public int getParallelFood() {
        return parallelFood;
    }

    public void setParallelFood(int parallelFood) {
        this.parallelFood = parallelFood;
    }

    public Set<EndCondition> getEndConditions() {
        return endConditions;
    }

    public void setEndConditions(Set<EndCondition> endConditions) {
        this.endConditions = endConditions;
    }

    public int getTickSpeed() {
        return tickSpeed;
    }

    public void setTickSpeed(int tickSpeed) {
        this.tickSpeed = tickSpeed;
    }
}
