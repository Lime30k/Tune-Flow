package com.example.walter;

import java.util.ArrayList;

public class Playlist {

    public String name;
    public ArrayList<String> playListStr = new ArrayList<>();
    public ArrayList<Song> playlist = new ArrayList<>();
    private ArrayList<String> papa;
    private Song song = null;
    private fileReader convertReader = new fileReader();


    public Playlist(int status, String n)
    {
        if(status == 0){

            fileReader papaReader = new fileReader();
            papaReader.read("songinit.txt");
            for(int  i = 0; i < papaReader.byteStash.size(); i++){

                String another = papaReader.byteStash.get(i);
                AddSong(another);

            }

        }


        playlist=new ArrayList<Song>();
        name=n;
    }

    private void AddSong(String str){

        if(playListStr.contains(str)){
            return;
        }else{
            SongConvert(str);
            playListStr.add(str);
            playlist.add(song);

        }
    }
    private void SongConvert(String str){
        convertReader.read(str+".txt");

        if(convertReader.byteStash.size()<19){
            System.out.println("Insufficient data for Song Conversion!");
            return;
        }

        String songname = convertReader.byteStash.get(1);
        String genre = convertReader.byteStash.get(3);
        String genre2 = convertReader.byteStash.get(5);
        int amount = Integer.parseInt(convertReader.byteStash.get(7));
        double length = Double.parseDouble(convertReader.byteStash.get(9));
        String artist = convertReader.byteStash.get(11);
        String album = convertReader.byteStash.get(13);
        double rating = Double.parseDouble(convertReader.byteStash.get(15));
        String mood1 = convertReader.byteStash.get(17);
        String mood2 = convertReader.byteStash.get(19);

        song=new Song(songname, genre,genre2, length, artist, album, rating, amount, mood1, mood2);
    }
}
