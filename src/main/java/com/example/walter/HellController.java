package com.example.walter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 DOCUMENTATION:
 Authors: Alex, Linus, Andreas

 Usage of this Tune-Flow application:

 Start the app by running the main method.
 Decide whether you want to listen to a single song or to create a queue.
 Search for songs in the Search engine.

 Follow your instinct, it might be self-explanatory.
 Try out every button on the GUI to experience all features.
 If you want to add new songs to Tune-Flow check out the Help-button on the GUI.
 Have fun!!!

 **/

/**
 * <p>
 * <h3>Authors: Alex, Linus, Andreas</h3>
 * <p>
 * The main controller class for the Tune-Flow music player application.
 * <p>
 * This class manages the UI components and user interactions such as
 * playing songs, managing the song queue, searching, and handling ratings.
 * It also includes an easter egg: the Konami code detection activates a special mode.
 * <p>
 * <h1>Key Features:</h1>
 * <ul>
 *     <li>Song playback control (play, pause, next, previous)</li>
 *     <li>Queue management for songs</li>
 *     <li>Search functionality with approximate matching</li>
 *     <li>Rating system for songs</li>
 *     <li>Dynamic UI updates including featured songs and Konami code mode</li>
 * </ul>
 * <p>
 * <h2>Usage:</h2>
 * <ul>
 *     <li>Launch the application via the main method.</li>
 *     <li>Interact with the UI to play songs, create queues, search songs, and rate them.</li>
 *     <li>Enter the Konami code sequence on the keyboard to unlock a hidden "Konami Mode".</li>
 * </ul>
 *<p>Note: Ensure that song media files and their cover images are placed in the 'data' folder</p>
 *
 */


public class HellController extends Application {
    @FXML
    public BorderPane rootPane;

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
    public VBox topBar;

    public HBox bottomBar;

    public MenuItem fileloader;

    public ScrollPane songmenuscroll;

    public TextField searchField;

    public ProgressBar progressBar;

    public Slider volumeSlider;

    public Playlist defaultPlaylist;
    private int queuePosition = 0;
    private Song featuredSong;

    Play play = new Play();
    private fileReader byteSnacker;
    public ArrayList<Song> Queue;
    public ArrayList<Playlist> playlistlist;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Label click works!");
    }
    @FXML
    protected void onPlayPauseClick()
    {
        if(play.getPlaystatus()==0){
            play.playInit();
            setupProgressBarTracking();
            play.startplay();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getDisplayName());
            artist_name_song_play.setText(play.getArtist());

            String imagePath = "data/" + play.getSong() + ".png";  // or relative path like "data/..."

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Image img = new Image(imageFile.toURI().toString());
                Song_logo_play.setImage(img);
            } else {
                System.out.println("Image file not found: " + imageFile.getAbsolutePath());
                imageFile = new File("data/default_song_cover.png");
                Image img = new Image(imageFile.toURI().toString());
                Song_logo_play.setImage(img);
                // optionally set a default/fallback image here
            }
            //Song_logo_play.setImage(new Image("/data/"+play.getSong()+".png"));
        }else if (play.getPlaystatus()==1) {
            play.pauseplay();

            Play_pause.setText("▷");
            return;
        }else {
            play.startplay();
            setupProgressBarTracking();
            Play_pause.setText("⏸");
            song_name_song_play.setText(play.getDisplayName());
            artist_name_song_play.setText(play.getArtist());
        }

    }

    @FXML
    protected void buildSongFormVBox() {
        songmenubar.getChildren().clear();
        VBox root = new VBox(10);

        root.setStyle("-fx-border-color: black; -fx-padding: 10;");

        // Fields
        TextField nameField = new TextField();
        TextField genreField = new TextField();
        TextField genre2Field = new TextField();
        TextField displayNameField = new TextField();
        TextField artistField = new TextField();
        TextField albumField = new TextField();
        TextField averageRatingField = new TextField();
        TextField mood1Field = new TextField();
        TextField mood2Field = new TextField();

        // Add labels and fields
        root.getChildren().addAll(
                new Label("filenames without .mp3/.png:"), nameField,
                new Label("Genre:"), genreField,
                new Label("Genre2:"), genre2Field,
                new Label("Display Name:"), displayNameField,
                new Label("Artist:"), artistField,
                new Label("Album:"), albumField,
                new Label("Initial Rating (0-5):"), averageRatingField,
                new Label("Mood1:"), mood1Field,
                new Label("Mood2:"), mood2Field
        );

        // Save button
        Button saveButton = new Button("Save Song");
        Label statusLabel = new Label();

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Name is required!");
                return;
            }
            byteSnacker.byteStash.clear();
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(nameField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(genreField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(genre2Field.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(Integer.toString(1),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(displayNameField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(artistField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(albumField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(averageRatingField.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(mood1Field.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(":",name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(mood2Field.getText().trim(),name);
            byteSnacker.writeToFile(name);
            byteSnacker.addLine(name,"1songinit");
            byteSnacker.writeToFile("1songinit");
            defaultPlaylist.AddSong(name);
            loadAllPressed();
        });

        root.getChildren().addAll(saveButton, statusLabel);
        songmenubar.getChildren().addAll(root);

    }

    @FXML
    protected void summonTheMightyHBox(Song song) {
        //resize vBox just in case
        songmenubar.setFillWidth(true);

        // Create a new HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefHeight(25);


        Label label = new Label(song.getDisplayName()+ "   -   "+song.getArtist());
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

        Button ratingButton = new Button("Rate");
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

        Label songName = new Label(song.getDisplayName());
        songName.setWrapText(false);
        songName.setEllipsisString("...");

        hbox.getChildren().addAll(songName);
        QueueField.getChildren().addAll(hbox);
    }

    protected void rateSong(Song song, double rating) {
        song.calculateReview(rating);
        System.out.println(Double.toString(rating));
    }

    protected void createPlaylist() {
    }

    @FXML
    protected void on_search_clicked(){
        //System.out.println("search lol");
        songmenubar.getChildren().clear();

        String str = searchField.getText();
        System.out.println(str);

        for(int i = 0; i < defaultPlaylist.playlist.size(); i++){
            Song song = defaultPlaylist.playlist.get(i);
            System.out.println(song.getName());

            String[] fields = {
                    song.getName(),
                    song.getDisplayName(),
                    song.getArtist(),
                    song.getGenre(),
                    song.getGenre2(),
                    song.getAlbum(),
                    song.getMood1(),
                    song.getMood2()
            };

            boolean match = false;
            for (String field : fields) {
                if (calculateCloseness(field, str) >= 40) {
                    match = true;
                    break;
                }
            }

            if (match) {
                summonTheMightyHBox(defaultPlaylist.playlist.get(i));
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
        System.out.println("New rating for " + song.getName() + ": " + rating);
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
        queuePosition = 0;
    }
    @FXML
    protected void saveQueue(){
        playlistlist.add(new Playlist(69,"playlist "+playlistlist.size()));
        byteSnacker.replaceLine(0,Integer.toString(playlistlist.size()),"1listinit");
        for(int i=0;i<Queue.size();i++){
            playlistlist.getLast().addNewSong(Queue.get(i));
        }
        byteSnacker.replaceLine(0,Integer.toString(playlistlist.size()),"1listinit");
        byteSnacker.writeToFile("1listinit");
    }

    @FXML
    protected void foreward_pressed(){
        if(queuePosition + 1 < Queue.size()){
            play.changeSong(Queue.get(queuePosition +1));
            queuePosition++;
            onPlayPauseClick();
        }else {
            play.pauseplay();
            play.playInit();
            play.startplay();
        }
    }
    @FXML
    protected void backward_pressed(){
        if(!Queue.isEmpty()&& queuePosition >0){
            play.changeSong(Queue.get(queuePosition -1));
            queuePosition--;
            onPlayPauseClick();
        }else {
            play.pauseplay();
            play.playInit();
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
        Label label2 = new Label("Program written by: Andreas, Alex, Linus\n \nWe hope you enjoy the use of this Software\n \nIf you wish any new songs to be added paste the mp3 and (optional) cover png in the data folder.\nAfter that fill out the form in the program\n\nSincerely, Tune-flow Studio");
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
        for(int i = 0; i< defaultPlaylist.playlist.size(); i++){
            summonTheMightyHBox(defaultPlaylist.playlist.get(i));
            System.out.println("code 1");
        }
    }

    protected void featuredSong(){
        if (defaultPlaylist == null || defaultPlaylist.playlist == null || defaultPlaylist.playlist.isEmpty()) {
            System.err.println("defaultPlaylist.playlist is null or empty!");
            return;
        }
        int random = (int)(Math.random() * defaultPlaylist.playlist.size());
        featuredSong = defaultPlaylist.playlist.get(random);
        featuredSongLabel.setText(featuredSong.getDisplayName());
    }

    @FXML
    protected void featuredPlay(){
        play.changeSong(featuredSong);
        onPlayPauseClick();
    }

    private static final List<KeyCode> KONAMI_CODE = List.of(
            KeyCode.UP, KeyCode.UP,
            KeyCode.DOWN, KeyCode.DOWN,
            KeyCode.LEFT, KeyCode.RIGHT,
            KeyCode.LEFT, KeyCode.RIGHT,
            KeyCode.B, KeyCode.A
    );

    private final List<KeyCode> inputBuffer = new ArrayList<>();

    public void registerKey(KeyCode code) {
        if (!KONAMI_CODE.contains(code)) {
            inputBuffer.clear();  // Reset on wrong input
            return;
        }

        inputBuffer.add(code);

        if (inputBuffer.size() > KONAMI_CODE.size()) {
            inputBuffer.removeFirst();
        }

        if (inputBuffer.equals(KONAMI_CODE)) {
            onKonamiCodeEntered();
            inputBuffer.clear();
        }
    }

    private void onKonamiCodeEntered() {
        System.out.println("Konami Code Activated!");
        featuredSongLabel.setText("✨ KONAMI MODE ✨");
        // Change root background
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom right, hotpink, purple);");

        // Change top bar
        topBar.setStyle("-fx-background-color: black;");
        topBar.lookupAll(".label").forEach(node -> node.setStyle("-fx-text-fill: lime; -fx-font-weight: bold;"));

        // Change bottom bar
        bottomBar.setStyle("-fx-background-color: black;");
        bottomBar.getChildren().filtered(node -> node instanceof Label).forEach(node -> {
            ((Label) node).setText("KONAMI MODE ENABLED");
            ((Label) node).setTextFill(Color.LIMEGREEN);
            ((Label) node).setFont(Font.font("Consolas", 14));
        });


        // Change progress bar color
        progressBar.setStyle("-fx-accent: lime;");

        Label cheatLabel = new Label(" CHEAT MODE ACTIVATED ");
        cheatLabel.setFont(Font.font("Consolas", 24));
        cheatLabel.setTextFill(Color.RED);
        cheatLabel.setStyle("-fx-font-weight: bold;");
        songmenubar.getChildren().add(0, cheatLabel);
        defaultPlaylist.specialSongConvert();
        play.changeSong(defaultPlaylist.specialSong);
        play.playInit();
        onPlayPauseClick();
    }

    public void playlistInit(){
        fileReader mamaReader = new fileReader();
        mamaReader.read("1listinit");
        for(int  i = 0; i < mamaReader.byteStash.size(); i++){

            playlistlist.add(new Playlist(69,mamaReader.byteStash.get(i)));

        }
    }


    @FXML
    public void initialize() {
        Platform.runLater( () -> rootPane.requestFocus() );
        rootPane.setOnKeyPressed(e -> {
            System.out.println("Key pressed: " + e.getCode());
            registerKey(e.getCode());
            Platform.runLater( () -> rootPane.requestFocus() );
        });
        Queue = new ArrayList<Song>();
        byteSnacker = new fileReader();
        byteSnacker.read("1listinit");
        int listSize = Integer.parseInt(byteSnacker.byteStash.get(0));
        playlistlist = new ArrayList<Playlist>(listSize);
        for(int i=0;i<listSize;i++){
            playlistlist.add(i,new Playlist(69,"Playlist "+i));
        }

        play.setOnSongEndListener(() -> {
            foreward_pressed();
            setupProgressBarTracking();
        });
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double sliderValue = newVal.doubleValue() / 100.0;

            // Apply a perceptual curve (gamma ~2.5 works well)
            double perceptualVolume = Math.pow(sliderValue, 2.5);
            System.out.printf("Slider: %.1f%% → Volume: %.3f%n", sliderValue * 100, perceptualVolume);

            play.setVolume(perceptualVolume);
        });
    }

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HellController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.getIcons().add(new Image("/tune-flow-high-resolution-logo.png"));
        stage.setTitle("Tune-Flow");
        stage.setScene(scene);



        stage.show();


        HellController controller = fxmlLoader.getController();
        controller.defaultPlaylist = new Playlist(0, "default");
        controller.hellishSongInitializer();
        controller.featuredSong();



    }





    public static void main(String[] args) {
        launch();
    }

}