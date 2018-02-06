package Model;

public class City {

    private String name;
    private Coordinates coordinates;
    private double area;
    private int population;


    //Empty constructor for DAO
    public City() {}


    //Partial initialization
    public City(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        area = 0;
        population = 0;
    }

    //Full initialization
    public City(String name, Coordinates coordinates, double area, int population) {
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
