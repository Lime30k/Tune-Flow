package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloController {
    @FXML
    private Label welcomeText;
    public Button HelloButton;
    public Label song_name_song_play;
    public Button Play_pause;
    public HBox songmenubar;
    public MenuItem fileloader;

    Play play = new Play();


    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");
    }
    @FXML
    protected void onPlayPauseClick()
    {
        play.playinit();
        play.startplay();
    }

    protected void rateSong(Song song, double rating) {
        song.calculateReview(rating);
    }

    protected void createPlaylist() {
    }

}