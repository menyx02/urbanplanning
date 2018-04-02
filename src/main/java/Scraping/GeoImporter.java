package Scraping;

import Database.DAOs.CityDao;
import Database.DAOs.PlaceDao;
import Database.DatabaseManager;
import Model.Place;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.apache.commons.lang3.StringUtils;


import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edoyle on 2/6/18.
 */
public class GeoImporter {

    public static void main(String[] args) {
        List<Place> placeList;
        try {
            placeList = readPlacesFromFile("/Users/edoyle/Downloads/US.txt");
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
        System.out.println("dad");
        db.initializeConnectionDatabase();
        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^

        PlaceDao placeDao = db.getPlaceDao();
        for (Place place : placeList) {
            placeDao.addPlace(place);
            System.out.println(place.getCity());
        }
    }

    public static List<Place> readPlacesFromFile(String pathTofileToImport)
            throws FileNotFoundException {
            // Reading Records One by One in a String array
        List<Place> places = null;
        CSVReader reader = new CSVReader(new FileReader(pathTofileToImport), '\t');
        String[] nextRecord;
        try {
            places = new ArrayList<Place>();
            int i = 1;
            while (((nextRecord = reader.readNext()) != null) && i < 10) {
                Place place = new Place(nextRecord);
                System.out.println(nextRecord[1]);
                places.add(place);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Place.class);
        System.out.println("here");
      /*  String[] columns = new String[] { "geonameid", "name", "asciiname", "alternatenames", "latitude", "longitude",
                "feature_class", "feature_code", "country_code", "cc2", "admin1", "admin2", "admin3", "admin4", "population", "elevation",
        "dem", "timezone", "modification_date"};*/
      /*  strat.setColumnMapping(columns);

        CsvToBean csv = new CsvToBean() {
            protected Object convertValue(String value, PropertyDescriptor prop) throws InstantiationException, IllegalAccessException {
                if(StringUtils.isEmpty(value)) {
                    value = null;
                }
                return super.convertValue(value, prop);
            }
        };
        List<Place> places = csv.parse(strat, reader);*/
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        return places;
    }
}
