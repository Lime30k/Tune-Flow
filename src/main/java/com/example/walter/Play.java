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
    private int Playstatus=0;

    public Play()
    {
    }

    //sets the new song run this if you initialize a new song not just the first time but every time you want a new song
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

    //starts to play music if there is no music playing atm
    public boolean startplay()
    {
        if(Playstatus==0) {
            Playstatus = 1;
            mediaPlayer.play();
            System.out.println("Song "+ song + " started playing playing!");

            return true;
        }
        System.out.println("incorrect player status for playing");
        return false;
    }
    public boolean pauseplay()
    {
        if(Playstatus==1) {
            Playstatus = 0;
            mediaPlayer.pause();
            System.out.println("Song "+ song + " is currently paused!");

            return true;
        }
        System.out.println("incorrect player status for pausing");
        return false;
    }

    public int getPlaystatus() {
        return Playstatus;
    }

    //Andreas what is that for?
    public void changeSong(String s){
        song = s;

    }
    /*public void pause()
    {
        mediaPlayer.setOnPaused(hit);
    }*/

}


