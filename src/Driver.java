import Communications.Python;
import Database.DAOs.CityDao;
import Database.DatabaseManager;
import Model.*;
import Representation.BaseCity;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        //Initialize knowledge base ******************************
       // DatabaseManager db = new DatabaseManager();
        //db.initializeConnectionDatabase();

        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^
//        CityDao cityDao = db.getCityDao();
//        List<City> cities = cityDao.getAllCities();
//        List<BaseCity> population = generatePopulation(cities);

        ArrayList<Segment> segments = new ArrayList<Segment>();
        segments.add(new Segment(new Point(10, 10), new Point(10, 50)));
        /*segments.add(new Segment(new Point(30, 10), new Point(30, 50)));
        segments.add(new Segment(new Point(50, 10), new Point(50, 50)));
        segments.add(new Segment(new Point(70, 10), new Point(70, 50)));
        segments.add(new Segment(new Point(90, 10), new Point(90, 50)));
        segments.add(new Segment(new Point(110, 10), new Point(110, 50)));
        segments.add(new Segment(new Point(130, 10), new Point(130, 50)));
        segments.add(new Segment(new Point(150, 10), new Point(150, 50)));
        segments.add(new Segment(new Point(170, 10), new Point(170, 50)));
        segments.add(new Segment(new Point(190, 10), new Point(190, 50)));
        segments.add(new Segment(new Point(210, 10), new Point(210, 50)));
        segments.add(new Segment(new Point(230, 10), new Point(230, 50)));
        segments.add(new Segment(new Point(250, 10), new Point(250, 50)));
        segments.add(new Segment(new Point(270, 10), new Point(270, 50)));
        segments.add(new Segment(new Point(290, 10), new Point(290, 50)));
        segments.add(new Segment(new Point(310, 10), new Point(310, 50)));
        segments.add(new Segment(new Point(330, 10), new Point(330, 50)));*/

        Python.translateToPythonFile("script.py", null, segments);

    }

    private static List<BaseCity> generatePopulation(List<City> cities) {
        List<BaseCity> population = new ArrayList<>();
        for (City instance : cities) {
            //todo: code for generating cities instances (BaseCity class)
        }
        return population;
    }
}
