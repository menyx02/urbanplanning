package Model;

public class Coordinates {

    private String coordinate;
    private double lat;
    private double longitude;

    //Empty constructor for DAO
    public Coordinates() {}

    public Coordinates (String coordinate) {
        this.coordinate = coordinate;
        parseCoordinate(coordinate);
    }

    private void parseCoordinate(String coordinate) {
        //TODO implement once I have the formate of the coordinates
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
        parseCoordinate(coordinate);
    }
}