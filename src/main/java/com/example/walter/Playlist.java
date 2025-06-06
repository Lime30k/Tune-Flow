package com.example.walter;

import java.util.ArrayList;

public class Playlist {
    public String name;
    public ArrayList<Song> playlist;
    public Playlist(String n)
    {
        playlist=new ArrayList<Song>();
        name=n;
    }
    public void Play(int Index){
        playlist.get(Index);
    }
}
