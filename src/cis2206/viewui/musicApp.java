package cis2206.viewui;

import cis2206.util.Validator;
import java.util.Scanner;

import cis2206.model.Music;
//import cis2206.model.datastore.file.EmployeeDAO;
import cis2206.model.datastore.mysql.MusicDAO;
import cis2206.model.IMusicDAO;

/**
 * EmployeeApp is the starting point for running this console-oriented
 * menu-driven employee management program. This program demonstrates two
 * solutions. The first is file based. The second is MySQL based.
 *
 * @author John Phillips
 * @version 20160920
 *
 */
public class musicApp {

    IMusicDAO musicList = new MusicDAO();
    Scanner sc = new Scanner(System.in);

    public musicApp() {
        menuLoop();
    }

    private void menuLoop() {
        int id;
        String title, artist, album;
        double year;
        String choice = "1";
        while (!choice.equals("0")) {
            System.out.println("\nEmployee App");
            System.out.println("0 = Quit");
            System.out.println("1 = List All Records");
            System.out.println("2 = Create New Record");
            System.out.println("3 = Retrieve Record");
            System.out.println("4 = Update Record");
            System.out.println("5 = Delete Record");
            choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

            switch (choice) {
                case "1":
                    System.out.println(musicList.toString());
                    break;
                case "2":
                    id = Validator.getInt(sc, "New music ID: ");
                    title = Validator.getLine(sc, "Title: ");
                    artist = Validator.getLine(sc, "Artist: ");
                    album = Validator.getLine(sc, "Album: ");
                    year = Validator.getDouble(sc, "Year: ");
                    musicList.createRecord(new Music(id, title, artist, album, year));
                    break;
                case "3":
                    id = Validator.getInt(sc, "Music id to retrieve: ");
                    System.out.println(musicList.retrieveRecordById(id));
                    break;
                case "4":
                    id = Validator.getInt(sc, "Music ID to update: ");
                    title = Validator.getLine(sc, "Title: ");
                    artist = Validator.getLine(sc, "Artist: ");
                    album = Validator.getLine(sc, "Album: ");
                    year = Validator.getDouble(sc, "Year: ");
                    musicList.updateRecord(new Music(id, title, artist, album, year));
                    break;
                case "5":
                    id = Validator.getInt(sc, "Music ID to delete: ");
                    System.out.println(musicList.retrieveRecordById(id));
                    String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        musicList.deleteRecord(id);
                    }
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new musicApp();
    }
}
