package com.example.walter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Play {
    String bip = null;
    String song = "never_gonna_give_you_up";
    public Play()
    {
        try {
            bip = getClass().getResource("recources/"+song+".mp3").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    Media hit = new Media(new File(bip).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(hit);
    public void startplay()
    {
        mediaPlayer.play();
    }
}


