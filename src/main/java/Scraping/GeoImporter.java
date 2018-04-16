package Scraping;

import Database.DAOs.CityDao;
import Database.DAOs.PlaceDao;
import Database.DatabaseManager;
import Model.City;
import Model.Place;
import au.com.bytecode.opencsv.CSVReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edoyle on 2/6/18.
 */
public class GeoImporter {
    private static DatabaseManager db;
    public static void main(String[] args) {
        db = new DatabaseManager();
        db.initializeConnectionDatabase();

        db.initializeTables();
        try {
            readPlacesFromFile("src/test/US.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Info Not Loaded!");
            e.printStackTrace();
            return;
        }
    }

    public static void readPlacesFromFile(String pathTofileToImport)
            throws FileNotFoundException {
            // Reading Records One by One in a String array
        int count = 3000000;
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("wc -l US.txt");
            InputStream is = pr.getInputStream();
            count = Integer.parseInt(new java.util.Scanner(is).next().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Place> places = null;
        List<City> cities = null;
        CSVReader reader = new CSVReader(new FileReader(pathTofileToImport), '\t');
        String[] nextRecord;
        try {
            places = new ArrayList<Place>();
            cities = new ArrayList<City>();
            int i = 1;
            while (((nextRecord = reader.readNext()) != null)) {
                Place place = new Place(nextRecord);
                if(place.getFeature_code().contains("PPLA"))
                {
                    cities.add(new City(place.getName(), place.getAdmin2(), place.getCoordinates(), 0, place.getPopulation()));
                }
                else {
                    places.add(place);
                }
                i++;
                if(i % 2000 == 0) {
                    storeData(places, cities);
                    places.clear();
                    cities.clear();
                }
                if(i % 10000 == 0) {
                    System.out.println((((double)i)/count) * 100 + "% loaded");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        storeData(places, cities);
    }

    private static void storeData(List<Place> places, List<City> cities) {
        CityDao cityDao = db.getCityDao();
        for (City city : cities) {
            cityDao.addCity(city);
        }

        PlaceDao placeDao = db.getPlaceDao();
        for (Place place : places) {
            placeDao.addPlace(place);
        }
    }
}
