package de.zuehlke.past.snake.model;

import javafx.geometry.Point2D;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private int piecesToGrow;
    private Direction currentDirection;
    private Deque<Point2D> points;

    public Snake(int length, Direction currentDirection, Deque<Point2D> points) {
        this.currentDirection = currentDirection;
        this.points = points;
    }

    public Deque<Point2D> advance() {
        Point2D head = points.getFirst();
        Point2D newPoint;

        switch (currentDirection) {
            case NORTH:
                newPoint = head.add(0, -1);
                break;
            case EAST:
                newPoint = head.add(1, 0);
                break;
            case SOUTH:
                newPoint = head.add(0, 1);
                break;
            case WEST:
                newPoint = head.add(-1, 0);
                break;
            default:
                throw new IllegalArgumentException("Unknown cardinal direction: " + currentDirection);
        }

        points.addFirst(newPoint);

        if (piecesToGrow == 0) {
            points.removeLast();
        } else {
            piecesToGrow--;
        }

        return points;
    }

    public int consume(Food food) {
        piecesToGrow += food.getValue();
        return points.size() + piecesToGrow;
    }

    public static Snake startingOffAt(Point2D startingPoint) {
        return new Snake(1, Direction.EAST, new LinkedList<>(Collections.singletonList(startingPoint)));
    }

    public Deque<Point2D> getPoints() {
        return points;
    }

    public Point2D getHead() {
        return points.getFirst();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction newDirection) {
        if (currentDirection.isOpposite(newDirection)) {
            throw new IllegalArgumentException("Cannot set new direction (" + newDirection + ") that is opposite to the previous direction (" + currentDirection + ")");
        }

        this.currentDirection = newDirection;
    }
}
