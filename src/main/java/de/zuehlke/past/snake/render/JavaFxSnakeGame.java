package de.zuehlke.past.snake.render;

import com.google.common.collect.ImmutableSet;
import de.zuehlke.past.snake.model.*;
import de.zuehlke.past.snake.model.ending.GameEndReason;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

/**
 * A board which is rendered onto a JavaFX window
 */
public class JavaFxSnakeGame extends SnakeGame {
    private boolean tearingDown;
    private boolean paused;
    private int scaleFactor;
    private Timer tickTimer;
    private Group rootGroup;
    private Canvas canvas;
    private Scene scene;
    private Stage stage;
    public static final Random RANDOM = new Random();

    public void initialize(Stage stage) {
        this.stage = stage;
        this.rootGroup = new Group();
        this.scene = new Scene(rootGroup, getConfig().getResolution(), getConfig().getResolution());
        this.scaleFactor = getConfig().getResolution() / getConfig().getFieldSize();
        this.canvas = new Canvas(getConfig().getFieldSize() * this.scaleFactor, getConfig().getFieldSize() * this.scaleFactor);
        this.tickTimer = new Timer();
        this.paused = false;
        this.tearingDown = false;

        tickTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!paused) {
                    onTick();
                }
            }
        }, getConfig().getTickSpeed(), getConfig().getTickSpeed());

        scene.setOnKeyPressed(e -> {
            if (ImmutableSet.of(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN).contains(e.getCode())) {
                Optional<Direction> newDirection = Direction.mapKeyCode(e.getCode());
                newDirection.ifPresent(this::onDirectionChange);
            } else if (KeyCode.P.equals(e.getCode())) {
                togglePause();
            } else if (KeyCode.ESCAPE.equals(e.getCode())) {
                tickTimer.cancel();
                stage.hide();
            }
        });


        Group boardGroup = new Group(this.canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();
        drawBackground(context);
        drawSnake(context, getPrimarySnake());
        rootGroup.getChildren().add(boardGroup);
        stage.setScene(scene);

    }

    public JavaFxSnakeGame(SnakeConfiguration config) {
        super(config);
    }

    private void togglePause() {
        this.paused = !this.paused;
    }

    private void drawSnake(GraphicsContext context, Snake snake) {
        float startColor = 255;
        float endColor = 192;
        Deque<Point2D> points = snake.getPoints();
        int snakeLength = points.size();
        int i = 0;

        Iterator<Point2D> descendingIterator = points.descendingIterator();
        while (descendingIterator.hasNext()) {
            Point2D point = descendingIterator.next();

            if (points.size() > 1) {
                int interpolatedColor = (int) (endColor + (startColor - endColor) * (i++ / (float) snakeLength));
                context.setFill(Color.rgb(interpolatedColor, interpolatedColor, interpolatedColor));
            } else {
                context.setFill(Color.WHITE);
            }

            context.fillRect(point.getX() * scaleFactor, point.getY() * scaleFactor, scaleFactor, scaleFactor);
        }
    }

    private void drawBackground(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawFood(GraphicsContext context) {
        for (Food food : getSpawnedFood()) {
            Point2D pos = food.getPosition();
            context.setFill(food.getColor());
            context.fillOval(pos.getX() * scaleFactor, pos.getY() * scaleFactor, scaleFactor, scaleFactor);
        }
    }

    private void drawStats(GraphicsContext context) {
        context.setFill(Color.WHITE);
        context.setFont(new Font(20));
        context.fillText("Points: " + getPrimarySnake().getPoints().size(), 0, canvas.getHeight() - 20);
    }

    @Override
    public void onTick() {
        super.onTick();

        if (!tearingDown) {
            GraphicsContext context = canvas.getGraphicsContext2D();

            context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawBackground(context);
            drawSnake(context, getPrimarySnake());
            drawFood(context);
            drawStats(context);

        }
    }

    @Override
    protected void endGame(GameEndReason endReason) {
        tearingDown = true;
        tickTimer.cancel();
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        context.setFont(Font.font(20));
        context.setFill(Color.RED);
        context.fillText("Game Over: " + endReason.toString(), 50, 50);
        context.setFont(Font.font(10));
        context.fillText("Press ESC to exit game", 70, 70);

    }
}
