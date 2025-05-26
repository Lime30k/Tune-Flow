public class Song {
    String name;
    String genre;
    double lenght;
    String artist;
    int likes;
    String filepath;

    public Song(){

    }
    public String getName() {
        return name;
    }

    public String getGenre(){
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilepath() {
        return filepath;
    }

    public int getLikes() {
        return likes;
    }

    public double getLenght() {
        return lenght;
    }


    public void setName(String s){
        this.name=s;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
