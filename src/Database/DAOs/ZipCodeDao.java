package Database.DAOs;

import Database.DatabaseManager;
import Model.ZipCode;

import java.sql.*;

public class ZipCodeDao {

    private DatabaseManager dbManager;

    public ZipCodeDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
}
