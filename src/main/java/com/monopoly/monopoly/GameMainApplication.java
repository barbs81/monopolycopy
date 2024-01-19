package com.monopoly.monopoly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameMainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("start-window.fxml"));
            Image icon = new Image(getClass().getResourceAsStream("monopolyIcon.png"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MONOPOLY");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}