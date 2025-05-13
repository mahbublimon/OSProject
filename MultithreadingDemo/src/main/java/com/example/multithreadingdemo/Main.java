package com.example.multithreadingdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/multithreadingdemo/main.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        primaryStage.setTitle("JavaFX Multithreading Demo");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.setOnCloseRequest(event -> controller.shutdown());
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.setProperty("prism.verbose", "true"); // Add JavaFX debug info
        System.setProperty("javafx.preloader", "none");
        launch(args);
    }
}