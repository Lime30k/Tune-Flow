package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

import java.io.IOException;


/**
 DOCUMENTATION: Authors: Alex, Linus, Andreas.

 Usage of this Tune-Flow application:

 Start the app by running the main method.
 Decide whether you want to listen to an already defined Playlist or a new Song.
 Search for new songs in the Search engine.
 Follow your instinct, it might be self-explanatory.
 Have fun!!!



 */



public class HelloController extends Application {
    @FXML
    private Label welcomeText;
    public Button HelloButton;
    public Label song_name_song_play;
    public Button Play_pause;
    public VBox songmenubar;
    public MenuItem fileloader;
    public ScrollPane songmenuscroll;

    Play play = new Play();


    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");
        summonTheMightyHBox("new song would look like this");
        summonTheMightyHBox("or this");
        summonTheMightyHBox("the song of doom!!!");
    }
    @FXML
    protected void onPlayPauseClick()
    {
        play.playinit();
        if (play.getPlaystatus()==1) {
            play.pauseplay();
        } else if(play.startplay()){
            Play_pause.setText("Pause");

        }
    }

    @FXML
    protected void summonTheMightyHBox(String labelText) {
        //resize vBox just in case
        songmenubar.setFillWidth(true);

        // Create a new HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefHeight(25);


        Label label = new Label(labelText);
        HBox.setHgrow(label,Priority.ALWAYS);
        label.setWrapText(false);
        label.setEllipsisString("...");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        Button button = new Button("Button");

        hbox.getChildren().addAll(label, spacer, button);

        // Add the HBox to the VBox in the ScrollPane
        songmenubar.getChildren().add(hbox);
    }



    protected void rateSong(Song song, double rating) {
        song.calculateReview(rating);
    }

    protected void createPlaylist() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Walter!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}