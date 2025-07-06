package com.example.walter;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Play {
    private String bip = null;
    private String song = "never_gonna_give_you_up";
    private MediaPlayer mediaPlayer;
    private Media hit =null;
    private int Playstatus=0;
    private String artist = "Rick Astley";
    private String displayName = "Never Gonna Give You Up";
    private double lastVolume = 0.5;

    private Runnable onSongEndListener;

    public void setOnSongEndListener(Runnable listener) {
        this.onSongEndListener = listener;
    }
    public Play()
    {
    }

    public String getSong() {
        return song;
    }
    public String getArtist() {
        return artist;
    }
    public MediaPlayer getMediaPlayer(){
        return  mediaPlayer;
    }
    public String getDisplayName(){
        return displayName;
    }

    //sets the new song run this if you initialize a new song not just the first time but every time you want a new song
    public void playInit() {

        File file = new File("data/" + song + ".mp3");
        if (!file.exists()) {
            throw new IllegalStateException("File not found: " + file.getAbsolutePath());
        }
        String bip = file.toURI().toString();  // converts file path to URI string

        hit = new Media(bip);
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setVolume(lastVolume);

        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("Song finished!");

            if (onSongEndListener != null) {
                onSongEndListener.run();
                //glaub hier ist das Progress-Bar Problem
            }
        });
    }
    public void changeSong(Song str){
        if (mediaPlayer!=null) {
            mediaPlayer.pause();
            Playstatus = 0;
            System.out.println("Song " + song + " is currently paused!");
        }
        song = str.getName();
        artist= str.getArtist();
        displayName = str.getDisplayName();
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            lastVolume = volume;
            mediaPlayer.setVolume(volume);
        }
    }

    //starts to play music if there is no music playing atm
    public boolean startplay()
    {
        if(mediaPlayer==null){
            System.out.println("Mediaplayer was not initialized");
        }
        if(Playstatus==2||Playstatus==0) {
            Playstatus = 1;
            mediaPlayer.play();
            System.out.println("Song "+ song + " started playing!");

            return true;
        }
        System.out.println("incorrect player status for playing");
        return false;
    }
    public boolean pauseplay()
    {
        if(Playstatus==1) {
            mediaPlayer.pause();
            Playstatus = 2;
            System.out.println("Song " + song + " is currently paused!");
            return true;
        }
        System.out.println("incorrect player status for pausing");
        return false;
    }


    public int getPlaystatus() {
        return Playstatus;
    }

}


