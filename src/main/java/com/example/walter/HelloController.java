package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

public class HelloController {
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
        addSongItem("new song would look like this");
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

    protected void addSongItem(String labelText) {
        // Create a new HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10); // optional
        hbox.setPrefHeight(25);

        Label label = new Label(labelText);

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

}