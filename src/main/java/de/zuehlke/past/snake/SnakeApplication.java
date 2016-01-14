package de.zuehlke.past.snake;

import de.zuehlke.past.snake.model.SnakeConfiguration;
import de.zuehlke.past.snake.render.JavaFxSnakeGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        CheckBox loopAround = new CheckBox("Allow looparound?");

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            // Hide the config stage, play the game and show the config stage again when a round is done.
            primaryStage.hide();
            startGame(new Stage(), configurationFromFields(winningPoints, tickSpeed, resolution, fieldSize, loopAround));
            primaryStage.show();
        });


        int row = 0;
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.addRow(row++, new Label("Winning points: "), winningPoints);
        gridPane.addRow(row++, new Label("Tick speed (ms): "), tickSpeed);
        gridPane.addRow(row++, new Label("Resolution (px): "), resolution);
        gridPane.addRow(row++, new Label("Field size: "), fieldSize);
        gridPane.add(loopAround, 1, row++);

        gridPane.addRow(row++, startButton);
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    private SnakeConfiguration configurationFromFields(TextField winningPoints, TextField tickSpeed, TextField resolution, TextField fieldSize, CheckBox loopAround) {
        SnakeConfiguration config = SnakeConfiguration.defaultConfiguration();
        config.setWinningPoints(Integer.parseInt(winningPoints.getText()));
        config.setTickSpeed(Integer.parseInt(tickSpeed.getText()));
        config.setResolution(Integer.parseInt(resolution.getText()));
        config.setFieldSize(Integer.parseInt(fieldSize.getText()));
        config.setLoopAround(loopAround.isSelected());

        return config;
    }

    private void startGame(Stage stage, SnakeConfiguration configuration) {
        JavaFxSnakeGame board = new JavaFxSnakeGame(configuration);
        board.initialize(stage);
        stage.showAndWait();
    }
}
