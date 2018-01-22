package Model;

public class Coordinates {

    private String coordinate;
    private double lat;
    private double longitude;

    public Coordinates (String coordinate) {
        this.coordinate = coordinate;
        parseCoordinate(coordinate);
    }

    private void parseCoordinate(String coordinate) {
        //TODO implement once I have the formate of the coordinates
    }

    public double getLatitute() {
        return lat;
    }

    public double getLongitute() {
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
