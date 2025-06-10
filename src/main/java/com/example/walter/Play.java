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
    private Media hit =null;
    public Play()
    {
    }

    public void playinit() {
        try {
            var resource = getClass().getResource("/" + song + ".mp3");
            if (resource == null) {
                throw new IllegalStateException("Resource not found: /" + song + ".mp3");
            }
            bip = resource.toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URI for: " + song, e);
        }
        hit = new Media(bip);  // no need to wrap in File
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


