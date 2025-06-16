package com.example.walter;

import java.util.ArrayList;

public class Playlist {

    public String name;
    public ArrayList<String> playliststr;
    public ArrayList<Song> playlist;
    private ArrayList<String> papa;
    private Song song = null;



    public Playlist(int status, String n)
    {
        if(status == 0){
            fileReader papaReader = new fileReader();
            papaReader.read(songinit.txt);
            for(int  i = 0; i < papaReader.byteStash.size(); i++){



            }

        }


        playlist=new ArrayList<Song>();
        name=n;
    }
    public void Play(int Index){
        playlist.get(Index);
    }
    private void AddSong(String str){

        if(playliststr.contains(str)){

            return;

        }else{
            SongConvert(str);
            playliststr.add(str);
            playlist.add(song);

        }
    }
    public void SongConvert(String str){


    }


}
