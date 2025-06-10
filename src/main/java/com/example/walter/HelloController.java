package com.example.walter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloController {
    @FXML
    private Label welcomeText;
    public Button HelloButton;
    public Label song_name_song_play;
    public Button Play_pause;


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

}