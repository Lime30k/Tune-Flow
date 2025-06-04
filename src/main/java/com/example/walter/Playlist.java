package com.example.walter;

import java.util.ArrayList;

public class Playlist {
public String name;
ArrayList<Song> playlist;
public Playlist(String n)
{
    playlist=new ArrayList<Song>();
    name=n;
}
public void Play(int Index){/*Startet Song bei bestimmtem Index und spielt ihn ab*/
    playlist.get(Index);

}
}
