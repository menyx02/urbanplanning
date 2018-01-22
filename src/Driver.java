import Database.DatabaseManager;
import Model.City;
import Model.Coordinates;
import Model.Place;
import Model.ZipCode;

import java.awt.*;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        //Initialize knowledge base ******************************
        DatabaseManager db = new DatabaseManager();
        db.initializeConnectionDatabase();

        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^


    }
}