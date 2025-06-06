package com.example.walter;
import java.util.Arrays;
public class User {
    String username;
    int ID;
    Playlist[] playlists;

    public User(String name) {
        username = name;
        ID = 1;
    }

    public void createPlaylist() {
    }

    public void rateSong(Song song, double rating) {
        song.changeRating(rating);
    }

    public void calculateReview(Song song, double rating, int amount){

           /*
            Hier muss noch was gescheites hin!!!
            rating = rating*amount + 1* rating;
            */

    }







    /*if (i < 5) {

            System.out.println("This Playlist is not the yellow from the egg!");

        } else {

            System.out.println("This Playlist sounds nice!");

        }
    */




    }