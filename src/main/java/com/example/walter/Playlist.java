package com.example.walter;

import java.util.ArrayList;

public class Playlist {

    public String name;
    public ArrayList<String> playListStr;
    public ArrayList<Song> playlist;
    private Song song;
    private fileReader convertReader = new fileReader();
    public Song specialSong;

    public Playlist(int status, String n)
    {
        playListStr = new ArrayList<>();
        playlist = new ArrayList<Song>();


        fileReader papaReader = new fileReader();
        if(status == 0){
            papaReader.read("1songinit");
        }else if(papaReader.getFileInfo(n)==1){
            papaReader.read(n);
        }
        if(papaReader.getFileInfo(n)==0){
            papaReader.writeToFile(n);
        }
        if(papaReader.getFileInfo(n)==1||status==0) {
            for (int i = 0; i < papaReader.byteStash.size(); i++) {

                AddSong(papaReader.byteStash.get(i));

            }
        }
        if(status > 0||status<0){
            for(int i = 0; i< playlist.size();i++){
                papaReader.addLine(playlist.get(i).getName(),n);
                papaReader.writeToFile(n);
            };
        }

        name=n;
        System.out.println("Final playlist size: " + playlist.size());
    }

    public void AddSong(String str){
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

    public void addNewSong(Song song){
        if(playlist.contains(song)){
            return;
        }


        if(song != null){
            playListStr.add(song.getName());
            playlist.add(song);
            System.out.println(song.getDisplayName() + " was added");
            convertReader.addLine(song.getName(),name);
            convertReader.writeToFile(name);
        }
    }

    private Song SongConvert(String str){
        convertReader.read(str);


        if(convertReader.byteStash.size()==20) {
            String songname = convertReader.byteStash.get(1);
            String genre = convertReader.byteStash.get(3);
            String genre2 = convertReader.byteStash.get(5);
            int amount = Integer.parseInt(convertReader.byteStash.get(7));
            String displayName = convertReader.byteStash.get(9);
            String artist = convertReader.byteStash.get(11);
            String album = convertReader.byteStash.get(13);
            double rating = Double.parseDouble(convertReader.byteStash.get(15));
            String mood1 = convertReader.byteStash.get(17);
            String mood2 = convertReader.byteStash.get(19);

            song = new Song(songname, genre, genre2, displayName, artist, album, rating, amount, mood1, mood2);
            System.out.println(song);
            return song;
        }else {
            System.out.println("Insufficient data for Song Conversion!");
            System.out.println(convertReader.byteStash.size());
            return null;
        }
    }
    public void specialSongConvert(){
        specialSong =new Song("secret","","","Konami Code", "","",100000000,1000000000,"","");
    }
}
