package cis2206.model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cis2206.model.Music;
import cis2206.model.IMusicDAO;

/**
 * EmployeeDAO (Data Access Object) handles all interactions with the data
 * store. This version uses a MySQL database to store the data. It is multiuser
 * safe.
 *
 * @author John Phillips
 * @version 20160920
 *
 */
public class MusicDAO implements IMusicDAO {

    protected final static boolean DEBUG = true;

    @Override
    public void createRecord(Music music) {
        final String QUERY = "insert into music "
                + "(musicId, title, artist, album, year) "
                + "VALUES (null, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY);) {
            stmt.setString(1, music.getTitle());
            stmt.setString(2, music.getArtist());
            stmt.setString(3, music.getAlbum());
            stmt.setDouble(4, music.getYear());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("createRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public Music retrieveRecordById(int id) {
        final String QUERY = "select musicId, title, artist, ablum, "
                + "year from music where musicId = " + id;
        // final String QUERY = "select empId, lastName, firstName, homePhone,
        // salary from employee where empId = ?";
        Music music = null;

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            // stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            if (rs.next()) {
                music = new Music(
                        rs.getInt("musicId"), 
                        rs.getString("Title"),
                        rs.getString("Artist"),
                        rs.getString("Album"), 
                        rs.getDouble("Year"));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveRecordById SQLException: " 
                    + ex.getMessage());
        }

        return music;
    }

    @Override
    public List<Music> retrieveAllRecords() {
        final List<Music> myList = new ArrayList<>();
        final String QUERY = "select musicId, title, artist, album, "
                + "year from music";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                myList.add(new Music(
                        rs.getInt("musicId"), 
                        rs.getString("title"), 
                        rs.getString("artist"),
                        rs.getString("alubm"), 
                        rs.getDouble("year")));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
        }

        return myList;
    }

    @Override
    public void updateRecord(Music updatedMusic) {
        final String QUERY = "update music set title=?, artist=?, "
                + "album=?, year=? where musicId=?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setString(1, updatedMusic.getTitle());
            stmt.setString(2, updatedMusic.getArtist());
            stmt.setString(3, updatedMusic.getAlbum());
            stmt.setDouble(4, updatedMusic.getYear());
            stmt.setInt(5, updatedMusic.getMusicId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("updateRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(int id) {
        final String QUERY = "delete from employee where musicId = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(Music music) {
        final String QUERY = "delete from music where musicId = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, music.getMusicId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Music music : retrieveAllRecords()) {
            sb.append(music.toString()).append("\n");
        }

        return sb.toString();
    }
}
