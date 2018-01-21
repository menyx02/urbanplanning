package Database.DAOs;

import java.sql.*;

public class CityDao {

    private Connection connection;

    public CityDao() {}

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
