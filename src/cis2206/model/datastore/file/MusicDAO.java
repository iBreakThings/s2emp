package cis2206.model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cis2206.model.Music;
import cis2206.model.IMusicDAO;

/**
 * EmployeeDAO (Data Access Object) handles all interactions with the data
 * store. This version uses a file to store the data. It is not multiuser safe.
 *
 * @author John Phillips
 * @version 20160920
 *
 */
public class MusicDAO implements IMusicDAO {

    protected String fileName = null;
    protected final List<Music> myList;

    public MusicDAO() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("res/file/db.properties"));
            this.fileName = props.getProperty("DB_FILENAME");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.myList = new ArrayList<>();
        try {
            Files.createFile(Paths.get(fileName));
        } catch (FileAlreadyExistsException fae) {
            ;
        } catch (IOException ioe) {
            System.out.println("Create file error with " + ioe.getMessage());
        }
        readList();
    }

    @Override
    public void createRecord(Music music) {
        myList.add(music);
        writeList();
    }

    @Override
    public Music retrieveRecordById(int id) {
        for (Music employee : myList) {
            if (employee.getMusicId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Music> retrieveAllRecords() {
        return myList;
    }

    @Override
    public void updateRecord(Music updatedMusic) {
        for (Music music : myList) {
            if (music.getMusicId() == updatedMusic.getMusicId()) {
                music.setTitle(updatedMusic.getTitle());
                music.setArtist(updatedMusic.getArtist());
                music.setAlbum(updatedMusic.getAlbum());
                music.setYear(updatedMusic.getYear());
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(int id) {
        for (Music employee : myList) {
            if (employee.getMusicId() == id) {
                myList.remove(employee);
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(Music music) {
        myList.remove(music);
        writeList();
    }

    private void readList() {
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String artist = data[2];
                String album = data[3];
                double salary = Double.parseDouble(data[4]);
                Music music = new Music(id, title, artist, album, salary);
                myList.add(music);
            }
        } catch (IOException ioe) {
            System.out.println("Read file error with " + ioe.getMessage());
        }
    }

    private void writeList() {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Music music : myList) {
                writer.write(String.format("%d,%s,%s,%s,%.2f\n",
                        music.getMusicId(),
                        music.getTitle(),
                        music.getArtist(),
                        music.getAlbum(),
                        music.getYear()));
            }
        } catch (IOException ioe) {
            System.out.println("Write file error with " + ioe.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Music employee : myList) {
            sb.append(String.format("%5d : %s, %s, %s, %.2f\n",
                    employee.getMusicId(),
                    employee.getTitle(),
                    employee.getAlbum(),
                    employee.getArtist(),
                    employee.getYear()));
        }

        return sb.toString();
    }
}
