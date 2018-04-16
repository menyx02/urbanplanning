package Database.DAOs;

import Database.DatabaseManager;
import Model.City;
import Model.Coordinates;
import Model.Place;
import Model.ZipCode;


import java.sql.*;
import java.util.ArrayList;

public class PlaceDao {

    private DatabaseManager dbManager;


    public PlaceDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addPlace(Place place) {
        dbManager.startTransaction();
        Connection conn = dbManager.getConnection();

        PreparedStatement smt = null;

        try {
            String command = "INSERT INTO Place (Name, ZipCode, CityId, Coordinates, Type, Population, Dimension," +
                    "IndexGrid) VALUES(?,?,?,?,?,?,?,?)";


            smt = conn.prepareStatement(command);

            smt.setString(1, place.getName());

            smt.setInt(2, 123);

            smt.setString(3, place.getAdmin2()); //place.getCity().getName());

            smt.setString(4, place.getCoordinates().getCoordinate());

            smt.setString(5, place.getFeature_code());//place.getType());

            smt.setInt(6, place.getPopulation());
            smt.setDouble(7, place.getDimension());
            smt.setString(8, place.getIndexGrid());


            if(smt.executeUpdate() == 1) {
                dbManager.safeClose(smt);
                dbManager.endTransaction(true);
            }
            else {
                dbManager.safeClose(smt);
                dbManager.endTransaction(false);
                System.out.println("Could not add place");
            }
        }
        catch (Exception e) {
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            e.printStackTrace();
            System.out.println("Could not add place " + place.getName());
        }
    }

    public Place getPlaceByName(String name) {

        Place queriedPlace = null;

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Place WHERE Name = ?";
            smt = con.prepareStatement(query);
            smt.setString(1, name);

            rs = smt.executeQuery();

            while(rs.next()) {
                ZipCode zp = new ZipCode();
                Coordinates coo = new Coordinates();
                City city = new City();

                queriedPlace = new Place();
                queriedPlace.setName(rs.getString("Name"));

                zp.setCode(rs.getInt("ZipCode"));
                queriedPlace.setZipcode(zp);

                city.setName(rs.getString("City"));
                queriedPlace.setCity(city);

                coo.setCoordinate(rs.getString("Coordinates"));
                queriedPlace.setCoordinates(coo);

                queriedPlace.setType(rs.getString("Type"));
                queriedPlace.setPopulation(Integer.toString(rs.getInt("Population")));
                queriedPlace.setDimension(rs.getDouble("Dimension"));
                queriedPlace.setIndexGrid(rs.getString("IndexGrid"));


                //TODO When bringing in the city and code, only name and code are brought in but the rest
                //of the information is incomplete and the objects are half empty. Is it important to have those
                //complete????? If it's then after this query is done, the transaction is closed, and before
                //returning from the method, call the different daos to get that information.
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could get the place by the name of: " + name);
        }

        return queriedPlace;
    }

    public ArrayList<Place> getAllPlaces() {

        ArrayList<Place> allPlaces = new ArrayList<Place>();

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Place";
            smt = con.prepareStatement(query);

            rs = smt.executeQuery();

            while(rs.next()) {
                Place queriedPlace = new Place();

                ZipCode zp = new ZipCode();
                Coordinates coo = new Coordinates();
                City city = new City();

                queriedPlace = new Place();
                queriedPlace.setName(rs.getString("Name"));

                zp.setCode(rs.getInt("ZipCode"));
                queriedPlace.setZipcode(zp);

                city.setName(rs.getString("City"));
                queriedPlace.setCity(city);

                coo.setCoordinate(rs.getString("Coordinates"));
                queriedPlace.setCoordinates(coo);

                queriedPlace.setType(rs.getString("Type"));
                queriedPlace.setPopulation(Integer.toString(rs.getInt("Population")));
                queriedPlace.setDimension(rs.getDouble("Dimension"));
                queriedPlace.setIndexGrid(rs.getString("IndexGrid"));

                allPlaces.add(queriedPlace);
                //TODO When bringing in the city and code, only name and code are brought in but the rest
                //of the information is incomplete and the objects are half empty. Is it important to have those
                //complete????? If it's then after this query is done, the transaction is closed, and before
                //returning from the method, call the different daos to get that information.
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not get all places");
        }

        return allPlaces;
    }

    public ArrayList<Place> getAllPlacesByCityId(String cityId) {

        ArrayList<Place> allPlaces = new ArrayList<Place>();

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Place WHERE CityId = ?";
            smt = con.prepareStatement(query);
            smt.setString(1, cityId);
            rs = smt.executeQuery();

            while(rs.next()) {
                Place queriedPlace = new Place();

                ZipCode zp = new ZipCode();
                Coordinates coo = new Coordinates();
                City city = new City();

                queriedPlace = new Place();
                queriedPlace.setName(rs.getString("Name"));

                zp.setCode(rs.getInt("ZipCode"));
                queriedPlace.setZipcode(zp);

                city.setName(rs.getString("CityId"));
                queriedPlace.setCity(city);

                coo.setCoordinate(rs.getString("Coordinates"));
                queriedPlace.setCoordinates(coo);

                queriedPlace.setType(rs.getString("Type"));
                queriedPlace.setPopulation(Integer.toString(rs.getInt("Population")));
                queriedPlace.setDimension(rs.getDouble("Dimension"));
                queriedPlace.setIndexGrid(rs.getString("IndexGrid"));

                allPlaces.add(queriedPlace);
                //TODO When bringing in the city and code, only name and code are brought in but the rest
                //of the information is incomplete and the objects are half empty. Is it important to have those
                //complete????? If it's then after this query is done, the transaction is closed, and before
                //returning from the method, call the different daos to get that information.
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not get all places");
        }

        return allPlaces;
    }





}
