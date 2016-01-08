package de.past.zuehlke.snake;

import de.past.zuehlke.snake.model.Direction;
import de.past.zuehlke.snake.render.JavaFxGSnakeGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeApplication extends Application {
    private Group root;
    private JavaFxGSnakeGame board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tutorial application
        primaryStage.setTitle("Snake Zero");
        board = new JavaFxGSnakeGame();
        board.initialize(primaryStage);

        primaryStage.show();

    }
}
