package Database.DAOs;

import Database.DatabaseManager;
import Model.City;
import Model.Coordinates;
import Model.ZipCode;

import java.sql.*;
import java.util.ArrayList;

public class ZipCodeDao {

    private DatabaseManager dbManager;

    public ZipCodeDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addZipCode(ZipCode zipCode) {
        dbManager.startTransaction();

        Connection conn = dbManager.getConnection();

        PreparedStatement smt = null;

        try {
            String command = "INSERT INTO ZipCode (Code, City, Coordinates, Population, MedianAge, Education," +
                    "HousingUnits, MedianIncome) VALUES(?,?,?,?,?,?,?,?)";

            smt = conn.prepareStatement(command);
            smt.setInt(1, zipCode.getCode());
            smt.setString(2, zipCode.getCity().getName());
            smt.setString(3, zipCode.getCoordinates().getCoordinate());
            smt.setInt(4, zipCode.getPopulation());
            smt.setInt(5, zipCode.getMedianAge());
            smt.setInt(6, zipCode.getEducation());
            smt.setInt(7, zipCode.getHousingUnits());
            smt.setInt(8, zipCode.getMedianIncome());


            if(smt.executeUpdate() == 1) {
                dbManager.safeClose(smt);
                dbManager.endTransaction(true);
            }
            else {
                dbManager.safeClose(smt);
                dbManager.endTransaction(false);
                System.out.println("Could not add ZipCode");
            }
        }
        catch (Exception e) {
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not add ZipCode " + zipCode.getCode());
        }
    }

    public ZipCode getZipCodebyCode(int code) {

        ZipCode queriedZipCode = null;

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM ZipCode WHERE Code = ?";
            smt = con.prepareStatement(query);
            smt.setInt(1, code);

            rs = smt.executeQuery();

            while(rs.next()) {
                City city = new City();
                Coordinates coo = new Coordinates();
                queriedZipCode = new ZipCode();

                queriedZipCode.setCode(rs.getInt("Code"));

                city.setName(rs.getString("City"));
                queriedZipCode.setCity(city);

                coo.setCoordinate(rs.getString("Coordinates"));
                queriedZipCode.setCoordinates(coo);

                queriedZipCode.setPopulation(rs.getInt("Population"));
                queriedZipCode.setMedianAge(rs.getInt("MedianAge"));
                queriedZipCode.setEducation(rs.getInt("Education"));
                queriedZipCode.setEducation(rs.getInt("HousingUnits"));
                queriedZipCode.setMedianIncome(rs.getInt("MedianIncome"));

            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not get the zipcode by the code of: " + code);
        }

        return queriedZipCode;
    }


    public ArrayList<ZipCode> getAllZipCodes() {
        ArrayList<ZipCode> allZipCodes = new ArrayList<ZipCode>();

        dbManager.startTransaction();
        Connection con = dbManager.getConnection();

        PreparedStatement smt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM ZipCode";
            smt = con.prepareStatement(query);
            rs = smt.executeQuery();

            while(rs.next()) {
                City city = new City();
                Coordinates coo = new Coordinates();
                ZipCode queriedZipCode = new ZipCode();

                queriedZipCode.setCode(rs.getInt("Code"));

                city.setName(rs.getString("City"));
                queriedZipCode.setCity(city);

                coo.setCoordinate(rs.getString("Coordinates"));
                queriedZipCode.setCoordinates(coo);

                queriedZipCode.setPopulation(rs.getInt("Population"));
                queriedZipCode.setMedianAge(rs.getInt("MedianAge"));
                queriedZipCode.setEducation(rs.getInt("Education"));
                queriedZipCode.setEducation(rs.getInt("HousingUnits"));
                queriedZipCode.setMedianIncome(rs.getInt("MedianIncome"));

                allZipCodes.add(queriedZipCode);
            }

            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(true);
        }
        catch (Exception e) {
            dbManager.safeClose(rs);
            dbManager.safeClose(smt);
            dbManager.endTransaction(false);
            System.out.println("Could not get all ZipCodes");
        }

        return allZipCodes;
    }
}
