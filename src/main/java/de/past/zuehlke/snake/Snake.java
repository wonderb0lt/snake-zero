package de.past.zuehlke.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Snake extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tutorial application
        primaryStage.setTitle("Snake Zero");

        Button button = new Button();
        button.setText("Hello, World!");
        button.setOnAction(e -> new Alert(Alert.AlertType.NONE, "Hello World", ButtonType.OK).show());


        StackPane slackPain = new StackPane();
        slackPain.getChildren().add(button);
        primaryStage.setScene(new Scene(slackPain, 500, 400));
        primaryStage.show();
    }
}
