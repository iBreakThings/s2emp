package cis2206.model;

import java.util.List;

/**
 * IEmployeeDAO is the interface for the Employee Data Access Object. The
 * interface defines the methods that will be used in all DAO implementations
 * for this application. This program currently has both file and database DAO
 * implementations. However, the application code does not care which is used as
 * everything is designed to work through this interface.
 *
 * @author John Phillips
 * @version 20151009
 *
 */
public interface IMusicDAO {

    void createRecord(Music music);

    Music retrieveRecordById(int id);

    List<Music> retrieveAllRecords();

    void updateRecord(Music updatedMusic);

    void deleteRecord(int id);

    void deleteRecord(Music music);

    @Override
    String toString();

}
