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
        String vals[] = coordinate.split(",");
        lat = Double.valueOf(vals[0]);
        longitude = Double.valueOf(vals[1]);
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
