package de.past.zuehlke.snake;

import de.past.zuehlke.snake.model.SnakeConfiguration;
import de.past.zuehlke.snake.render.JavaFxSnakeGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SnakeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake Zero");
        GridPane gridPane = new GridPane();

        TextField winningPoints = new TextField("50");
        TextField tickSpeed = new TextField("175");
        TextField resolution = new TextField("400");
        TextField fieldSize = new TextField("10");

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            // Hide the config stage, play the game and show the config stage again when a round is done.
            primaryStage.hide();
            startGame(new Stage(), configurationFromFields(winningPoints, tickSpeed, resolution, fieldSize));
            primaryStage.show();
        });


        gridPane.setHgap(5);
        gridPane.addRow(0, new Label("Winning points: "), winningPoints);
        gridPane.addRow(1, new Label("Tick speed (ms): "), tickSpeed);
        gridPane.addRow(2, new Label("Resolution (px): "), resolution);
        gridPane.addRow(3, new Label("Field size: "), fieldSize);
        gridPane.addRow(4, startButton);

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    private SnakeConfiguration configurationFromFields(TextField winningPoints, TextField tickSpeed, TextField resolution, TextField fieldSize) {
        SnakeConfiguration config = SnakeConfiguration.defaultConfiguration();
        config.setWinningPoints(Integer.parseInt(winningPoints.getText()));
        config.setTickSpeed(Integer.parseInt(tickSpeed.getText()));
        config.setResolution(Integer.parseInt(resolution.getText()));
        config.setFieldSize(Integer.parseInt(fieldSize.getText()));
        config.setLoopAround(false);

        return config;
    }

    private void startGame(Stage stage, SnakeConfiguration configuration) {
        JavaFxSnakeGame board = new JavaFxSnakeGame(configuration);
        board.initialize(stage);
        stage.showAndWait();
    }
}
