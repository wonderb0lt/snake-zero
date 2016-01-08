package de.past.zuehlke.snake.render;

import com.google.common.collect.ImmutableSet;
import de.past.zuehlke.snake.model.Direction;
import de.past.zuehlke.snake.model.Food;
import de.past.zuehlke.snake.model.SnakeGame;
import de.past.zuehlke.snake.model.Snake;
import de.past.zuehlke.snake.model.ending.GameEndReason;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A board which is rendered onto a JavaFX window
 */
public class JavaFxSnakeGame extends SnakeGame {
    private boolean tearingDown;
    private boolean paused;
    private static final int SCALE_FACTOR = 40;
    private Timer tickTimer;
    private Group rootGroup;
    private Canvas canvas;
    private Scene scene;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        this.rootGroup = new Group();
        this.scene = new Scene(rootGroup, 400, 400);
        this.canvas = new Canvas(getWidth() * SCALE_FACTOR, getHeight() * SCALE_FACTOR);
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
        }, TICK_SPEED_IN_MS, TICK_SPEED_IN_MS);

        scene.setOnKeyPressed(e -> {
            if (ImmutableSet.of(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN).contains(e.getCode())) {
                Optional<Direction> newDirection = Direction.mapKeyCode(e.getCode());
                newDirection.ifPresent(this::onDirectionChange);
            } else if (KeyCode.P.equals(e.getCode())) {
                togglePause();
            } else if (KeyCode.ESCAPE.equals(e.getCode())) {
                tickTimer.cancel();
                System.exit(0);
            }
        });


        Group boardGroup = new Group(this.canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();
        drawBackground(context);
        drawSnake(context, getPrimarySnake());
        rootGroup.getChildren().add(boardGroup);
        stage.setScene(scene);

    }

    private void togglePause() {
        this.paused = !this.paused;
    }

    private void drawSnake(GraphicsContext context, Snake snake) {
        context.setFill(Color.WHITE);
        for (Point2D point : snake.getPoints()) {
            context.fillRect(point.getX() * SCALE_FACTOR, point.getY() * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
        }
    }

    private void drawBackground(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawFood(GraphicsContext context) {
        for (Food food : getSpawnedFood()) {
            Point2D pos = food.getPosition();
            context.setFill(Color.BEIGE);
            context.fillOval(pos.getX() * SCALE_FACTOR, pos.getY() * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
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
