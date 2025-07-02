package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;


/**
 DOCUMENTATION: Authors: Alex, Linus, Andreas.

 Usage of this Tune-Flow application:

 Start the app by running the main method.
 Decide whether you want to listen to an already defined Playlist or a new Song.
 Search for new songs in the Search engine. (WIP)
 Follow your instinct, it might be self-explanatory.
 Have fun!!!



 **/



public class HellController extends Application {
    @FXML
    private Label welcomeText;
    public Button HelloButton;
    public Label song_name_song_play;
    public ImageView Song_logo_play;
    public Label artist_name_song_play;
    public Button Play_pause;
    public VBox songmenubar;
    public MenuItem fileloader;
    public ScrollPane songmenuscroll;
    public Button searchButton;
    public TextField searchField;

    public Playlist playlistPapa;

    Play play = new Play();
    fileReader biteSnacker = new fileReader();

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");

        for(int i=0; i<playlistPapa.playlist.size();i++){
            summonTheMightyHBox(playlistPapa.playlist.get(i));

        }
    }
    @FXML
    protected void onPlayPauseClick()
    {
        if(play.getPlaystatus()==0){
            play.playinit();
            play.startplay();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getSong());
        }else if (play.getPlaystatus()==1) {
            play.pauseplay();
            Play_pause.setText("▷");
            return;
        }else {
            play.startplay();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getSong());
        }

    }

    @FXML
    protected void summonTheMightyHBox(Song song) {
        //resize vBox just in case
        songmenubar.setFillWidth(true);

        // Create a new HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefHeight(25);


        Label label = new Label(song.getName());
        HBox.setHgrow(label,Priority.ALWAYS);

                label.setWrapText(false);
        label.setEllipsisString("...");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        double ratingdouble = song.getRating();
        String rstr = Double.toString(ratingdouble);
        Label rating = new Label(rstr);


        Button button = new Button("Play");
        button.setOnAction(e->{
            play.changeSong(song.getName());
            onPlayPauseClick();
        });

        hbox.getChildren().addAll(label, spacer, rating, button);

        // Add the HBox to the VBox in the ScrollPane
        songmenubar.getChildren().add(hbox);
    }



    protected void rateSong(@NotNull Song song, double rating) {
        song.calculateReview(rating);
    }

    protected void createPlaylist() {
    }

    @FXML
    protected void on_search_clicked(){
        //System.out.println("search lol");
        songmenubar.getChildren().clear();

        String str = searchField.getText();
        System.out.println(str);

        for(int i = 0; i < playlistPapa.playlist.size(); i++){
            System.out.println(playlistPapa.playlist.get(i).getName());
            if(calculateCloseness(playlistPapa.playlist.get(i).getName(), str) >= 40||calculateCloseness(playlistPapa.playlist.get(i).getArtist(), str) >=40||calculateCloseness(playlistPapa.playlist.get(i).getGenre(), str) >=40||calculateCloseness(playlistPapa.playlist.get(i).getGenre2(), str) >=40){

                summonTheMightyHBox(playlistPapa.playlist.get(i));
            }


        }
    }
    // Methode zur Berechnung der Levenshtein-Distanz
    public static int levenshteinDistance(String str1, String str2) {
        int lenStr1 = str1.length();
        int lenStr2 = str2.length();

        int[][] dp = new int[lenStr1 + 1][lenStr2 + 1];

        // Fülle die Basisfälle
        for (int i = 0; i <= lenStr1; i++) {
            for (int j = 0; j <= lenStr2; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Wenn str1 leer ist, sind die Operationen nur Einfügungen
                } else if (j == 0) {
                    dp[i][j] = i; // Wenn str2 leer ist, sind die Operationen nur Löschungen
                } else {
                    int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;

                    // Berechne die minimale Anzahl an Operationen (Einfügen, Löschen, Ersetzen)
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }

        return dp[lenStr1][lenStr2]; // Der Wert in der unteren rechten Ecke ist die Levenshtein-Distanz
    }

    // Methode zur Berechnung des Ähnlichkeitswerts (0 bis 100)
    public static int calculateCloseness(String str1, String str2) {
        int distance = levenshteinDistance(str1, str2);

        // Berechne den maximalen Abstand
        int maxLength = Math.max(str1.length(), str2.length());

        // Wenn beide Strings leer sind, ist die Nähe 100%
        if (maxLength == 0) {
            return 100;
        }

        // Skaliere die Levenshtein-Distanz zu einem Wert von 0 bis 100
        double similarity = (1.0 - (double) distance / maxLength) * 100;

        return (int) similarity; // Rückgabe als ganzzahliger Wert
    }


    protected void hellishSongInitializer(){
        for(int i=0; i<playlistPapa.playlist.size();i++){
            summonTheMightyHBox(playlistPapa.playlist.get(i));
            System.out.println("code 1");
        }
    }

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HellController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Tune-Flow");
        stage.setScene(scene);


        stage.show();
        HellController controller = fxmlLoader.getController();
        controller.playlistPapa = new Playlist(0, "default");
        System.out.println(controller.playlistPapa.playlist.size());
        controller.hellishSongInitializer();




    }

    public static void main(String[] args) {
        launch();
    }

}