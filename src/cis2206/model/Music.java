package cis2206.model;

/**
 * The Employee class represents a single employee.
 *
 * @author John Phillips
 * @version 20151015
 *
 */
public class Music {

    private int musicId;
    private String title;
    private String artist;
    private String album;
    private double year;

    public Music() {
        musicId = 0;
        title = "";
        artist = "";
        album = "";
        year = 0;
    }

    public Music(int musicId, String Title, String artist, String album, double year) {
        this.musicId = musicId;
        this.title = Title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%5d : %s, %s, %s, %.2f", this.getMusicId(), this.getTitle(),
                this.getArtist(), this.getAlbum(), this.getYear());
    }
}
