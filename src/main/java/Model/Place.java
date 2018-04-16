package Model;

import com.opencsv.bean.CsvBindByName;

public class Place {
    @CsvBindByName
    private String dem;
    @CsvBindByName
    private String timezone;
    @CsvBindByName
    private String modification_date;
    @CsvBindByName
    private String geonameid;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String asciiname;
    @CsvBindByName
    private String alternatenames;
    @CsvBindByName
    private String feature_class;
    @CsvBindByName
    private String feature_code;
    @CsvBindByName
    private String country_code;
    @CsvBindByName
    private String cc2;
    @CsvBindByName
    private String admin1;
    @CsvBindByName
    private String admin2;
    @CsvBindByName
    private String admin3;
    @CsvBindByName
    private String admin4;
    @CsvBindByName
    private String latitude;
    @CsvBindByName
    private String longitude;
    @CsvBindByName
    private ZipCode zipcode;
    private City city;
    private Coordinates coordinates;

    private String type;
    @CsvBindByName
    private String population;
    @CsvBindByName
    private String elevation;
    private double dimension;
    private String indexGrid;

    //Empty constructor for DAO
    public Place() {}

    //Partial initialization
    public Place(String name, ZipCode zipcode, City city, Coordinates coordinates, String type) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.coordinates = coordinates;
        this.type = type;
        population = String.valueOf(0);
        dimension = 0;
        indexGrid = "";
    }

    //Full initialization
    public Place(String name, ZipCode zipcode, City city, Coordinates coordinates, String type,
                 String population, double dimension, String indexGrid) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.coordinates = coordinates;
        this.type = type;
        this.population = population;
        this.dimension = dimension;
        this.indexGrid = indexGrid;
    }

    public Place(String[] nextRecord) {
        setGeonameid(nextRecord[0]);
        setName(nextRecord[1]);
        setAsciiname(nextRecord[2]);
        setAlternatenames(nextRecord[3]);
        setCoordinates(new Coordinates(nextRecord[4] + "," + nextRecord[5]));
        setFeature_class(nextRecord[6]);
        setFeature_code(nextRecord[7]);
        setCountry_code(nextRecord[8]);
        setCc2(nextRecord[9]);
        setAdmin1(nextRecord[10]);
        setAdmin2(nextRecord[11]);
        setAdmin3(nextRecord[12]);
        setAdmin4(nextRecord[13]);
        setPopulation(nextRecord[14]);
        setElevation(nextRecord[15]);
        setDem(nextRecord[16]);
        setTimezone(nextRecord[17]);
        setModification_date(nextRecord[18]);
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
        return Integer.valueOf(population);
    }

    public void setPopulation(String population) {
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

    public String getDem() {
        return dem;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getModification_date() {
        return modification_date;
    }

    public void setModification_date(String modification_date) {
        this.modification_date = modification_date;
    }

    public String getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(String geonameid) {
        this.geonameid = geonameid;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public String getFeature_class() {
        return feature_class;
    }

    public void setFeature_class(String feature_class) {
        this.feature_class = feature_class;
    }

    public String getFeature_code() {
        return feature_code;
    }

    public void setFeature_code(String feature_code) {
        this.feature_code = feature_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    public String getAdmin4() {
        return admin4;
    }

    public void setAdmin4(String admin4) {
        this.admin4 = admin4;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}
