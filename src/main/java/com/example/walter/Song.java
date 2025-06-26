package com.example.walter;

public class Song {
    private String name;
    private String genre;
    private String genre2;
    private double length;
    private String artist;
    private String album;
    private double rating;
    private int amount;
    private String mood1;
    private String mood2;


    public Song(String name, String genre, String genre2, double length, String artist, String album, double rating, int amount, String mood1, String mood2) {
        this.setName(name);
        this.setGenre(genre);
        this.setGenre2(genre2);
        this.setArtist(artist);
        this.setLength(length);
        this.setAlbum(album);
        this.setRating(rating);
        this.setReview(amount);
        this.setMood1(mood1);
        this.setMood2(mood2);
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getArtist() {
        return artist;
    }

    public double getLength() {
        return length;
    }

    public String getAlbum() {
        return album;
    }

    public double getRating() {
        return rating;
    }

    public int getReview() {
        return amount;
    }

    public String getMood1() {
        return mood1;
    }

    public String getMood2() {
        return mood2;
    }


    public void setName(String s) {
        this.name = s;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setMood1(String mood1) {
        this.mood1 = mood1;
    }

    public void setMood2(String mood2) {
        this.mood2 = mood2;
    }

    public void setReview(int amount) {
        this.amount = amount;
    }

    public void calculateReview(double newrating) {

        double convert = amount;

        rating = (rating * convert + newrating) / (convert + 1);
        amount++;


    }

}