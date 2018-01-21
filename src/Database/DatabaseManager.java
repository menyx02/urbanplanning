package Database;

import java.sql.*;


/*
import databasemanagement.daos.CarretesTerminadosDAO;
import databasemanagement.daos.ClientesDAO;
import databasemanagement.daos.MateriaPrimaDAO;
import databasemanagement.daos.ModelosDAO;
import databasemanagement.daos.ProcesosDAO;
import databasemanagement.daos.SalidaCarretesDAO;
import databasemanagement.daos.UsuariosDAO;*/

public class DatabaseManager {


    private static final String DATABASE_FILE = "UrbanPlanning.sqlite";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_FILE;
    private Connection connection;


/*
    private CarretesTerminadosDAO carretesTerminadosDAO;
    private ClientesDAO clientesDAO;
    private MateriaPrimaDAO materiaPrimaDAO;
    private ModelosDAO modelosDAO;
    private ProcesosDAO procesosDAO;
    private SalidaCarretesDAO salidaCarretesDAO;
    private UsuariosDAO usuariosDAO;*/


    public DatabaseManager() {
        connection = null;
       /* this.carretesTerminadosDAO = new CarretesTerminadosDAO(this);
        this.clientesDAO = new ClientesDAO(this);
        this.materiaPrimaDAO = new MateriaPrimaDAO(this);
        this.modelosDAO = new ModelosDAO(this);
        this.procesosDAO = new ProcesosDAO(this);
        this.salidaCarretesDAO = new SalidaCarretesDAO(this);
        this.usuariosDAO = new UsuariosDAO(this);*/
    }

    public void initializeDatabase() {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Could not load database driver");
        }
    }

    public void initializeTables() {
        startTransaction();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("DROP TABLE IF EXISTS City");
            stmt.execute("DROP TABLE IF EXISTS ZipCode");
            stmt.execute("DROP TABLE IF EXISTS Place");

            endTransaction(true);
        }
        catch(SQLException e) {
            endTransaction(false);
            System.out.println("Could not drop tables");
        }
        finally {
            safeClose(connection);
        }

        createCity();
        createZipCode();
        createPlace();
    }

    private void createCity() {
        startTransaction();

        Statement st = null;
        try {
            st = connection.createStatement();
            String query = "CREATE  TABLE \"City\" (\"Name\" VARCHAR NOT NULL , \"Coordinates\" VARCHAR " +
                    "NOT NULL  UNIQUE , \"Area\" INTEGER, \"Population\" INTEGER, PRIMARY KEY " +
                    "(\"Name\", \"Coordinates\"))";
            st.executeUpdate(query);
            endTransaction(true);
        }
        catch(SQLException e) {
            endTransaction(false);
            System.out.println("Could not create City table");
        }
        finally {
            safeClose(st);
            safeClose(connection);
        }
    }

    private void createZipCode() {
        startTransaction();

        Statement st = null;
        try {
            st = connection.createStatement();
            String query = "CREATE  TABLE \"ZipCode\" (\"Code\" INTEGER NOT NULL  UNIQUE , \"City\" " +
                    "VARCHAR NOT NULL , \"Coordinates\" VARCHAR NOT NULL  UNIQUE , \"Population\" INTEGER, " +
                    "\"Median Age\" INTEGER, \"Education\" INTEGER, \"Housing Units\" INTEGER, \"Median Income\" " +
                    "INTEGER, PRIMARY KEY (\"Code\", \"City\"))";
            st.executeUpdate(query);
            endTransaction(true);
        }
        catch(SQLException e) {
            endTransaction(false);
            System.out.println("Could not create ZipCode table");
        }
        finally {
            safeClose(st);
            safeClose(connection);
        }
    }

    private void createPlace() {
        startTransaction();

        Statement st = null;
        try {
            st = connection.createStatement();
            String query = "CREATE  TABLE \"Place\" (\"Name\" VARCHAR NOT NULL , \"ZipCode\" INTEGER NOT NULL ," +
                    " \"City\" INTEGER NOT NULL , \"Coordinates\" VARCHAR NOT NULL , \"Type\" VARCHAR NOT NULL , " +
                    "\"Population\" INTEGER, \"Dimension\" DOUBLE, \"IndexGrid\" VARCHAR, PRIMARY KEY (\"Name\", " +
                    "\"ZipCode\", \"City\"))";
            st.executeUpdate(query);
            endTransaction(true);
        }
        catch(SQLException e) {
            endTransaction(false);
            System.out.println("Could not create Place table");
        }
        finally {
            safeClose(st);
            safeClose(connection);
        }
    }



    public void startTransaction() {
        try {
            assert (connection == null);
            connection = DriverManager.getConnection(DATABASE_URL);
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            System.out.println("Could not connect to database. Make sure " +
                    DATABASE_FILE + " is available in ./" + e.toString());
        }

    }

    //ECTORY, e
    public void endTransaction(boolean commit) {
        if (connection != null) {
            try {
                if (commit) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                System.out.println("Could not end transaction");
                e.printStackTrace();
            }
            finally {
                safeClose(connection);
                connection = null;
            }
        }
    }

    public void safeClose(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                // ...
            }
        }
    }

    public void safeClose(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) {
                // ...
            }
        }
    }

    public void safeClose(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) {
                // ...
            }
        }
    }

    public void safeClose(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                // ...
            }
        }
    }







/*

    public CarretesTerminadosDAO getCarretesTerminadosDAO() {
        return carretesTerminadosDAO;
    }

    public ClientesDAO getClientesDAO() {
        return clientesDAO;
    }

    public MateriaPrimaDAO getMateriaPrimaDAO() {
        return materiaPrimaDAO;
    }

    public ModelosDAO getModelosDAO() {
        return modelosDAO;
    }

    public ProcesosDAO getProcesosDAO() {
        return procesosDAO;
    }

    public SalidaCarretesDAO getSalidaCarretesDAO() {
        return salidaCarretesDAO;
    }

    public UsuariosDAO getUsuariosDAO() {
        return usuariosDAO;
    }

    public Connection getConnection() {
        return connection;
    }

}*/

}
