import Database.DAOs.CityDao;
import Database.DatabaseManager;
import Model.City;
import Model.Coordinates;
import Model.Place;
import Model.ZipCode;
import Representation.BaseCity;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        //Initialize knowledge base ******************************
        DatabaseManager db = new DatabaseManager();
        db.initializeConnectionDatabase();

        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^
        CityDao cityDao = db.getCityDao();
        List<City> cities = cityDao.getAllCities();
        List<BaseCity> population = generatePopulation(cities);

    }

    private static List<BaseCity> generatePopulation(List<City> cities) {
        List<BaseCity> population = new ArrayList<>();
        for (City instance : cities) {
            //todo: code for generating cities instances (BaseCity class)
        }
        return population;
    }
}