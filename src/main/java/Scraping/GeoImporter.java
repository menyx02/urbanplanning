package Scraping;

import Database.DAOs.CityDao;
import Database.DAOs.PlaceDao;
import Database.DatabaseManager;
import Model.Place;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by edoyle on 2/6/18.
 */
public class GeoImporter {

    public static void main(String[] args) {
        List<Place> placeList;
        try {
            placeList = readPlacesFromFile(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Info Not Loaded!");
            e.printStackTrace();
            return;
        }
        storePlaces(placeList);
    }

    private static void storePlaces(List<Place> placeList) {
        //Initialize knowledge base ******************************
        DatabaseManager db = new DatabaseManager();
        db.initializeConnectionDatabase();
        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^
        PlaceDao placeDao = db.getPlaceDao();
        for (Place place : placeList) {
            placeDao.addPlace(place);
        }
    }

    public static List<Place> readPlacesFromFile(String pathTofileToImport)
            throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader(pathTofileToImport), '\t');

        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Place.class);
        String[] columns = new String[] { "countryCode", "postalCode",
                "placeName", "adminName1", "adminCode1", "adminName2",
                "adminCode2", "adminName3", "adminCode3", "latitude",
                "longitude", "accuracy" };
        strat.setColumnMapping(columns);

        CsvToBean csv = new CsvToBean();
        return csv.parse(strat, reader);
    }
}
