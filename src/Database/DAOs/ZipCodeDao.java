package Database.DAOs;

import Model.ZipCode;

import java.sql.*;

public class ZipCodeDao {

    private Connection connection;

    public ZipCodeDao () {}

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
