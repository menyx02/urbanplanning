import Database.DatabaseManager;

public class Driver {

    public static void main(String[] args) {

        //Initialize knowledge base ******************************
        DatabaseManager db = new DatabaseManager();
        db.initializeConnectionDatabase();

        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^


    }
}