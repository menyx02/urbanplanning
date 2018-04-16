package Database.DAOs;

import Database.DatabaseManager;
import Model.City;
import Model.Coordinates;
import Representation.BaseCity;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    private DatabaseManager dbManager;

    public CityDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }


    public void addCity(City city) {
        dbManager.startTransaction();
        Connection conn = dbManager.getConnection();

        PreparedStatement smt = null;

        try {
            String command = "INSERT INTO City (Name, Coordinates, Id, Area, Population) VALUES(?,?,?,?,?)";

            smt = conn.prepareStatement(command);
            smt.setString(1, city.getName());
            smt.setString(2, city.getCoordinates().getCoordinate());
            smt.setString(3, city.getId());
            smt.setDouble(4, city.getArea());
            smt.setInt(5, city.getPopulation());

            if(smt.executeUpdate() == 1) {
                dbManager.safeClose(smt);
                dbManager.endTransaction(true);
            }
            else {
                dbManager.safeClose(smt);
                dbManager.endTransaction(false);
                System.out.println("Could not add city");
            }
        }
        catch (Exception e) {
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not add city " + city.getName() + " '" + city.getId() + "'");
            e.printStackTrace();
        }
    }

    public BaseCity getCityByName(String name) {

        BaseCity queriedCity = null;

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM City WHERE Name = ?";
            smt = con.prepareStatement(query);
            smt.setString(1, name);

            rs = smt.executeQuery();

            while(rs.next()) {
               queriedCity = new BaseCity();
               queriedCity.setName(rs.getString("Name"));
               queriedCity.setCoordinates(new Coordinates(rs.getString("Coordinates")));
               queriedCity.setPopulation(rs.getInt("Population"));
               queriedCity.setArea(rs.getDouble("Area"));
               queriedCity.setId(rs.getString("Id"));
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could get the city by the name of: " + name);
        }

       return queriedCity;
    }

    public List<BaseCity> getAllCities() {
        ArrayList<BaseCity> allCities = new ArrayList<BaseCity>();

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM City";
            smt = con.prepareStatement(query);
            rs = smt.executeQuery();

            while(rs.next()) {
                BaseCity x = new BaseCity(); //todo:update
                x.setName(rs.getString("Name"));
                x.setCoordinates(new Coordinates(rs.getString("Coordinates")));
                x.setArea(rs.getDouble("Area"));
                x.setId(rs.getString("Id"));
                x.setPopulation(rs.getInt("Population"));
                allCities.add(x);
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not get all Cities");
        }

        return allCities;
    }

}
