package Database.DAOs;

import Database.DatabaseManager;

import javax.xml.crypto.Data;
import java.net.ConnectException;
import java.sql.*;

public class PlaceDao {

    private DatabaseManager dbManager;


    public PlaceDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
}
