package com.example.walter;

import java.util.ArrayList;

public class Playlist {

    public String name;
    public ArrayList<String> playListStr;
    public ArrayList<Song> playlist;
    private ArrayList<String> papa;
    private Song song;
    private fileReader convertReader = new fileReader();


    public Playlist(int status, String n)
    {
        playListStr = new ArrayList<>();
        playlist = new ArrayList<Song>();
        if(status == 0){

            fileReader papaReader = new fileReader();
            papaReader.read("songinit.txt");
            for(int  i = 0; i < papaReader.byteStash.size(); i++){

                AddSong(papaReader.byteStash.get(i));

            }

        }

        name=n;
        System.out.println("Final playlist size: " + playlist.size());
    }

    private void AddSong(String str){
        if(playListStr.contains(str)){
            return;
        }

        Song newSong = SongConvert(str);
        if(newSong != null){
            playListStr.add(str);
            playlist.add(newSong);
            System.out.println(str + " was added");
        }
    }

    private Song SongConvert(String str){
        convertReader.read(str+".txt");


        if(convertReader.byteStash.size()==20) {
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

            song = new Song(songname, genre, genre2, length, artist, album, rating, amount, mood1, mood2);
            System.out.println(song);
            return song;
        }else {
            System.out.println("Insufficient data for Song Conversion!");
            System.out.println(convertReader.byteStash.size());
            return null;
        }
    }
}
