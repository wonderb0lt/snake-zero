package de.past.zuehlke.snake;

import de.past.zuehlke.snake.model.Direction;
import de.past.zuehlke.snake.render.JavaFxRenderedBoard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeApplication extends Application {
    private Group root;
    private JavaFxRenderedBoard board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tutorial application
        primaryStage.setTitle("Snake Zero");
        root = new Group();
        board = new JavaFxRenderedBoard();
        Scene scene = new Scene(root, 400, 400);
        board.initialize(root);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                board.onTick();
            }
        }, 250, 250);

        scene.setOnKeyPressed(e -> {
            Optional<Direction> newDirection = Direction.mapKeyCode(e.getCode());
            newDirection.ifPresent(board::onDirectionChange);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> timer.cancel());
    }
}
