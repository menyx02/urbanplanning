package Model;

public class Place {

    private String name;
    private ZipCode zipcode;
    private City city;
    private Coordinates coordinates;
    private String type;
    private int population;
    private double dimension;
    private String indexGrid;


    //Partial initialization
    public Place(String name, ZipCode zipcode, City city, Coordinates coordinates, String type) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.coordinates = coordinates;
        this.type = type;
    }

    //Full initialization
    public Place(String name, ZipCode zipcode, City city, Coordinates coordinates, String type,
                 int population, double dimension, String indexGrid) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.coordinates = coordinates;
        this.type = type;
        this.population = population;
        this.dimension = dimension;
        this.indexGrid = indexGrid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZipCode getZipcode() {
        return zipcode;
    }

    public void setZipcode(ZipCode zipcode) {
        this.zipcode = zipcode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public String getIndexGrid() {
        return indexGrid;
    }

    public void setIndexGrid(String indexGrid) {
        this.indexGrid = indexGrid;
    }
}
