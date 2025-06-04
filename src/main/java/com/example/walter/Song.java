package com.example.walter;

public class Song {
    String name;
    String genre;
    double length;
    String artist;
    String album;
    int likes;
    String filepath;
    int rating;

    public Song(String name, String genre, double length, String artist, int likes, String filepath, String album, int rating)
    {
        this.setName(name);
        this.setGenre(genre);
        this.setArtist(artist);
        this.setLenght(length);
        this.setLikes(likes);
        this.setFilepath(filepath);
        this.setAlbum(album);
        this.setRating(rating);
    }
    public String getName()
    {
        return name;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public int getLikes()
    {
        return likes;
    }

    public double getLenght()
    {
        return length;
    }

    public String getAlbum()
    {
        return album;
    }

    public int getRating()
    {
        return rating;
    }

    public void setName(String s)
    {
        this.name=s;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setLenght(double lenght)
    {
        this.length = lenght;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public void setAlbum(String album)
    {
       this.album = album;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }


    public void changeRating(int rating)
    {

    }
}
