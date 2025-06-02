package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewImpl extends Application {


/**
    DOCUMENTATION: Authors: Alex, Linus, Andreas.

    Usage of this Spotify-like application:

    Start the app  by using the main method.
    Decide whether you want to listen to an already defined Playlist or a new Song.
    Search for new songs in the Search engine.
    Follow your instinct, it might be self-explanatory.
    Have fun!!!

*/



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewImpl.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Walter!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}