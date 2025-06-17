package com.example.walter;

import java.util.ArrayList;

public class Playlist {

    public String name;
    public ArrayList<String> playliststr;
    public ArrayList<Song> playlist;
    private ArrayList<String> papa;
    private Song song = null;
    private fileReader convertReader = new fileReader();


    public Playlist(int status, String n)
    {
        if(status == 0){

            fileReader papaReader = new fileReader();
            papaReader.read("songinit.txt");
            for(int  i = 0; i < papaReader.byteStash.size(); i++){

                String another = papaReader.byteStash.get(i - 1);
                AddSong(another);

            }

        }


        playlist=new ArrayList<Song>();
        name=n;
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
    private void SongConvert(String str){
        convertReader.read(str+".txt");
        for(int i = 0; i<= convertReader.byteStash.size();i++){

        }

    }


}
