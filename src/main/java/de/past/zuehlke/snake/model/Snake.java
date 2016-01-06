package de.past.zuehlke.snake.model;

import javafx.geometry.Point2D;

import java.util.Deque;
import java.util.List;

public class Snake {
    private int length;
    private Deque<Point2D> points;

    public Deque<Point2D> advance(Direction direction) {
        Point2D newPoint = new Point2D(points.getFirst().getX(), points.getFirst().getY());

        switch (direction) {
            case NORTH:
                newPoint.add(0, -1);
                break;
            case EAST:
                newPoint.add(1, 0);
                break;
            case SOUTH:
                newPoint.add(0, 1);
                break;
            case WEST:
                newPoint.add(-1, 0);
                break;
        }

        points.addFirst(newPoint);
        points.removeLast();
        return points;
    }
}
