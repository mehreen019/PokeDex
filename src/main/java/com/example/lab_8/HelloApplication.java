package com.example.lab_8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String cssFile = this.getClass().getResource("cards.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        HelloController cnt = fxmlLoader.getController();
        cnt.establishConnection();
        cnt.populateGrid();

        stage.setTitle("Pokedex!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}