package com.example.lbofinder;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * Find line break opportunities
 *
 */
public class App extends Application {
    @Override
    public void start(final Stage primaryStage) throws IOException {
        final SplitPane root = (SplitPane) FXMLLoader.load(getClass().getResource("/App.fxml"));
        final Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Find line break oppotunities");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}