package Model;

public class ZipCode {

    private int code;
    private City city;
    private Coordinates coordinates;
    private int population;
    private int medianAge;
    private int education;
    private int housingUnits;
    private int medianIncome;

    //Partial initialization
    public ZipCode(int code, City city, Coordinates coordinates) {
        this.code = code;
        this.city = city;
        this.coordinates = coordinates;
    }

    //Full initialization
    public ZipCode(int code, City city, Coordinates coordinates, int population, int medianAge, int education,
                   int housingUnits, int medianIncome) {
        this.code = code;
        this.city = city;
        this.coordinates = coordinates;
        this.population = population;
        this.medianAge = medianAge;
        this.education = education;
        this.housingUnits = housingUnits;
        this.medianIncome = medianIncome;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(int medianAge) {
        this.medianAge = medianAge;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(int housingUnits) {
        this.housingUnits = housingUnits;
    }

    public int getMedianIncome() {
        return medianIncome;
    }

    public void setMedianIncome(int medianIncome) {
        this.medianIncome = medianIncome;
    }
}
