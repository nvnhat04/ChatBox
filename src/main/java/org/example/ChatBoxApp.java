package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatBoxApp extends Application {


    private static final int WIDTH = 500;
    private static final int HEIGHT = 650;
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ChatBoxApp.class.getResource("ChatBoxUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        primaryStage.setTitle("ChatBox");
        primaryStage.setScene(scene);

        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
