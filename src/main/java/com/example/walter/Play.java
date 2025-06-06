package com.example.walter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Play {
    private String bip = null;
    private String song = "never_gonna_give_you_up";
    private MediaPlayer mediaPlayer;
    private Media hit;
    public Play()
    {
    }
    public void playinit()
    {
        try
        {
            bip = getClass().getResource("recources/"+song+".mp3").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        hit = new Media(new File(bip).toURI().toString());

        mediaPlayer = new MediaPlayer(hit);

    }
    public void startplay()
    {
        mediaPlayer.play();
    }
    public void changeSong(String s){
        song = s;

    }
    /*public void pause()
    {
        mediaPlayer.setOnPaused(hit);
    }*/

}


