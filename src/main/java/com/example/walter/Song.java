package com.example.walter;

public class Song {
    String name;
    String genre;
    double length;
    String artist;
    String album;
    int likes;
    String filepath;
    double rating;
    int amount;



    public Song(String name, String genre, double length, String artist, int likes, String filepath, String album, double rating, int amount)
    {
        this.setName(name);
        this.setGenre(genre);
        this.setArtist(artist);
        this.setLenght(length);
        this.setLikes(likes);
        this.setFilepath(filepath);
        this.setAlbum(album);
        this.setRating(rating);
        this.setReview(amount);
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

    public double getRating() {  return rating; }

    public int getReview(){  return amount; }


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

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public void changeRating(double rating) {  this.rating = rating;  }

    public void setReview(int amount) {this.amount = amount;  }

    public void calculateReview(double newrating) {

        double convert = amount;

        rating = (rating * convert + newrating) / (convert+1);
        amount++;


    }

}
