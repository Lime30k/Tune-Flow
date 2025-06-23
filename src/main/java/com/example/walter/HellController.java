package com.example.walter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

import java.io.IOException;


/**
 DOCUMENTATION: Authors: Alex, Linus, Andreas.

 Usage of this Tune-Flow application:

 Start the app by running the main method.
 Decide whether you want to listen to an already defined Playlist or a new Song.
 Search for new songs in the Search engine. (WIP)
 Follow your instinct, it might be self-explanatory.
 Have fun!!!



 */



public class HellController extends Application {
    @FXML
    private Label welcomeText;
    public Button HelloButton;
    public Label song_name_song_play;
    public Button Play_pause;
    public VBox songmenubar;
    public MenuItem fileloader;
    public ScrollPane songmenuscroll;

    public Playlist playlistPapa;

    Play play = new Play();
    fileReader biteSnacker = new fileReader();

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");
        summonTheMightyHBox("new song would look like this");
        summonTheMightyHBox("or this");
        summonTheMightyHBox("the song of doom!!!");
        for(int i=0; i<playlistPapa.playListStr.size();i++){
            summonTheMightyHBox(playlistPapa.playListStr.get(i));

        }
    }
    @FXML
    protected void onPlayPauseClick()
    {
        if(play.getPlaystatus()==0){
            play.playinit();
            play.startplay();
            Play_pause.setText("Pause");
        }else if (play.getPlaystatus()==1) {
            play.pauseplay();
            return;
        }else {
            play.startplay();
            Play_pause.setText("Pause");
        }

    }

    @FXML
    protected void summonTheMightyHBox(String labelText) {
        //resize vBox just in case
        songmenubar.setFillWidth(true);

        // Create a new HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefHeight(25);


        Label label = new Label(labelText);
        HBox.setHgrow(label,Priority.ALWAYS);
        label.setWrapText(false);
        label.setEllipsisString("...");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        int ratingInt = 1;
        String rstr = Integer.toString(ratingInt);
        Label rating = new Label(rstr);


        Button button = new Button("Button");

        hbox.getChildren().addAll(label, spacer, rating, button);

        // Add the HBox to the VBox in the ScrollPane
        songmenubar.getChildren().add(hbox);
    }



    protected void rateSong(Song song, double rating) {
        song.calculateReview(rating);
    }

    protected void createPlaylist() {
    }

    protected void hellishSongInitializer(){
        for(int i=0; i<playlistPapa.playlist.size();i++){
            summonTheMightyHBox(playlistPapa.playListStr.get(i));
            System.out.println("code 1");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
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