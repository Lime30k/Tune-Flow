package com.example.walter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import static java.awt.SystemColor.menu;


/**
 DOCUMENTATION: Authors: Alex, Linus, Andreas.

 Usage of this Tune-Flow application:

 Start the app by running the main method.
 Decide whether you want to listen to an already defined Playlist or a new Song.
 Search for new songs in the Search engine.
 Follow your instinct, it might be self-explanatory.
 Have fun!!!



 **/



public class HellController extends Application {
    @FXML
    public ImageView Song_logo_play;

    public Label song_name_song_play;
    public Label artist_name_song_play;
    public Label featuredSongLabel;
    public Label currentTimeLabel;
    public Label totalTimeLabel;

    public Button backward;
    public Button Play_pause;
    public Button foreward;
    public Button searchButton;

    public VBox songmenubar;
    public VBox QueueField;

    public MenuItem fileloader;

    public ScrollPane songmenuscroll;

    public TextField searchField;

    public ProgressBar progressBar;

    public Playlist playlistPapa;
    private int QueuePosition = 0;


    Play play = new Play();
    fileReader biteSnacker = new fileReader();
    public ArrayList<Song> Queue;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");
    }
    @FXML
    protected void onPlayPauseClick()
    {
        if(play.getPlaystatus()==0){
            play.playinit();
            setupProgressBarTracking();
            play.startplay();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getSong());
            artist_name_song_play.setText(play.getArtist());
            Song_logo_play.setImage(new Image(play.getSong()+".png"));
        }else if (play.getPlaystatus()==1) {
            play.pauseplay();

            Play_pause.setText("▷");
            return;
        }else {
            play.startplay();
            setupProgressBarTracking();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getSong());
            artist_name_song_play.setText(play.getArtist());
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


        Label label = new Label(song.getDisplayName());
        HBox.setHgrow(label,Priority.ALWAYS);

        label.setWrapText(false);
        label.setEllipsisString("...");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        double ratingdouble = song.getRating();
        String rstr = String.format("%.1f",ratingdouble);
        Label rating = new Label("★"+rstr);


        Button addToQueue =new Button("Add to Queue");
        addToQueue.setOnAction(e->{
            addQueue(song);
        });


        Button button = new Button("Play");
        button.setOnAction(e->{
            play.changeSong(song);
            onPlayPauseClick();
        });

        Button ratingButton = new Button();
        ContextMenu fileMenu = new ContextMenu();



        ratingButton.setOnAction(e -> fileMenu.show(ratingButton, Side.BOTTOM, 0, 0));;

        for(int i = 0; i <=5; i++){
           MenuItem item = new MenuItem(Integer.toString(i));
            int finalI = i;
            item.setOnAction(e-> newRating(finalI,song));
           fileMenu.getItems().add(item);

        }

        hbox.getChildren().addAll(label, spacer, rating, button, addToQueue, ratingButton);

        // Add the HBox to the VBox in the ScrollPane
        songmenubar.getChildren().add(hbox);
    }

    protected void summonQueueBox(Song song){
        QueueField.setFillWidth(true);
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefHeight(25);

        Label songname = new Label(song.getName());
        songname.setWrapText(false);
        songname.setEllipsisString("...");

        hbox.getChildren().addAll(songname);
        QueueField.getChildren().addAll(hbox);
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

    protected void newRating(int rating, Song song){
        song.calculateReview(rating);
    }



    protected void addQueue(Song s){
        Queue.add(s);
        System.out.println(s+" has been added to queue!");
        summonQueueBox(s);
    }

    @FXML
    protected void clearQueue(){
        Queue.clear();
        QueueField.getChildren().clear();
        QueuePosition = 0;
    }

    @FXML
    protected void foreward_pressed(){
        if(QueuePosition + 1 < Queue.size()){
            play.changeSong(Queue.get(QueuePosition+1));
            QueuePosition++;
            onPlayPauseClick();
        }else {
            play.pauseplay();
            play.playinit();
            play.startplay();
        }
    }
    @FXML
    protected void backward_pressed(){
        if(!Queue.isEmpty()&&QueuePosition>0){
            play.changeSong(Queue.get(QueuePosition-1));
            QueuePosition--;
            onPlayPauseClick();
        }else {
            play.pauseplay();
            play.playinit();
            play.startplay();
        }
    }

    @FXML
    protected void loadAllPressed(){
        songmenubar.getChildren().clear();
        hellishSongInitializer();
    }

    @FXML
    protected void aboutPressed(){
        songmenubar.getChildren().clear();

        Label label1 = new Label("About Us:");
        label1.setFont(Font.font("System.Bold"));
        Label label2 = new Label("Program written by: Andreas, Alex, Linus\n \nWe hope you enjoy the use of this Software\n \nIf you wish any new songs to be added feel free to contact us.\n\n Sincerely Tune-flow Studio");
        songmenubar.getChildren().addAll(label1, label2);
    }

    private String formatDuration(Duration duration) {
        int seconds = (int) duration.toSeconds();
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }




    private void setupProgressBarTracking() {
        MediaPlayer mediaPlayer = play.getMediaPlayer();

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            Duration total = mediaPlayer.getTotalDuration();

            if (total != null && total.toMillis() > 0) {
                double progress = newTime.toMillis() / total.toMillis();
                Platform.runLater(() -> progressBar.setProgress(progress));

                Platform.runLater(() -> {
                    currentTimeLabel.setText(formatDuration(newTime));
                    totalTimeLabel.setText(formatDuration(total));
                });
            }
        });

    }
    protected void hellishSongInitializer(){
        for(int i=0; i<playlistPapa.playlist.size();i++){
            summonTheMightyHBox(playlistPapa.playlist.get(i));
            System.out.println("code 1");
        }
    }

    @FXML
    public void initialize() {
        Queue = new ArrayList<Song>();
        play.setOnSongEndListener(() -> {
            // Run on JavaFX Application Thread:
            Platform.runLater(this::foreward_pressed);
            setupProgressBarTracking();
        });


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