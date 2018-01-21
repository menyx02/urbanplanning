package Database.DAOs;

import java.net.ConnectException;
import java.sql.*;

public class PlaceDao {

    private Connection connection;

    public PlaceDao() {}

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
