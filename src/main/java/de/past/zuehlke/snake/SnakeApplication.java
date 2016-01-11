package de.past.zuehlke.snake;

import de.past.zuehlke.snake.render.JavaFxSnakeGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class SnakeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake Zero");
        JavaFxSnakeGame board = new JavaFxSnakeGame();
        board.initialize(primaryStage);
        primaryStage.show();
    }
}
