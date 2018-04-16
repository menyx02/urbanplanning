package Representation;

import Model.Coordinates;
import Model.Place;
import Utils.Randomizer;
import org.geonames.BoundingBox;

import java.util.*;

/**
 * Created by edoyle on 1/23/18.
 */
public class BaseCity {
    private Map<String, Matrix> features;
    private boolean normalized;
    private BoundingBox boundary;
    private String name;
    private Coordinates coordinates;
    private double area;
    private int population;
    private String id;

    public BaseCity(){
        this.boundary = new BoundingBox(Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE);
        normalized = false;
        features = new HashMap<String, Matrix>();
    }

    public BaseCity(BoundingBox boundary){
        this.boundary = boundary;
        normalized = false;
        features = new HashMap<String, Matrix>();
    }

    public BaseCity(List<Place> places) {
        features = new HashMap<String, Matrix>();
        double west = Double.MIN_VALUE;
        double east = Double.MAX_VALUE;
        double north = Double.MIN_VALUE;
        double south = Double.MAX_VALUE;
        for (Place p : places) {
            if(!features.containsKey(p.getType())){
                Matrix m = new Matrix(p.getCoordinates());
                features.put(p.getType(), m);
            }
            else {
                features.get(p.getType()).addEntry(p.getCoordinates());
            }
            double longitude = p.getCoordinates().getLongitude();
            double latitude = p.getCoordinates().getLatitude();
            if(longitude > west) {
                west = longitude;
            }
            if (longitude < east){
                east = longitude;
            }
            if(latitude > north) {
                north = latitude;
            }
            if (latitude < south) {
                south = latitude;
            }
        }
        this.boundary = new BoundingBox(west, east, south, north);
        normalized = false;
    }

    protected void setBuildingType (String type, List<Coordinates> coordinates) {
        features.put(type, new Matrix(coordinates));
    }

    public void breed(BaseCity partner, List<BaseCity> population, Double alpha) {
        if (!normalized) {
            normalize();
        }
        Double featureProbability = Randomizer.randomProbability();
        Set<String> kept = new HashSet<String>();
        for (String type : features.keySet()){
            if(featureProbability < Randomizer.randomProbability()) {
                kept.add(type);
            }
        }
        //Breeding logic
        generateOffspring(kept, partner, population, alpha);
    }

    private void normalize() {
        if(!normalized) {
            for (Matrix matrix : features.values()) {
                matrix.normalize(boundary);
            }
        }

    }

    private void generateOffspring(Set<String> kept, BaseCity partner, List<BaseCity> population, Double alpha) {
        BaseCity offspringOne = new BaseCity();
        BaseCity offspringTwo = new BaseCity();
        for (String type : features.keySet()){
            if(features.containsKey(type) && partner.features.containsKey(type)) {
                if (kept.contains(type)) {
                    offspringOne.features.put(type, features.get(type).generateMutatedCopy(alpha));
                    offspringTwo.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
                } else {
                    offspringOne.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
                    offspringTwo.features.put(type, features.get(type).generateMutatedCopy(alpha));
                }
            }
            else if (features.containsKey(type)) {
                offspringOne.features.put(type, features.get(type).generateMutatedCopy(alpha));
                offspringTwo.features.put(type, features.get(type).generateMutatedCopy(alpha));
            }
            else {
                offspringOne.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
                offspringTwo.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
            }
        }
        population.add(offspringOne);
        population.add(offspringTwo);
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

    public int getValue() {
        //todo: incorporate value code
        return features.entrySet().size();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){return id;}

    public void addAllPlaces(List<Place> places) {
        double west = boundary.getWest();
        double east = boundary.getEast();
        double north = boundary.getNorth();
        double south = boundary.getSouth();
        for (Place p : places) {
            if(!features.containsKey(p.getType())){
                Matrix m = new Matrix(p.getCoordinates());
                features.put(p.getType(), m);
            }
            else {
                features.get(p.getType()).addEntry(p.getCoordinates());
            }
            double longitude = p.getCoordinates().getLongitude();
            double latitude = p.getCoordinates().getLatitude();
            if(longitude > west) {
                west = longitude;
            }
            if (longitude < east){
                east = longitude;
            }
            if(latitude > north) {
                north = latitude;
            }
            if (latitude < south) {
                south = latitude;
            }
        }
        this.boundary = new BoundingBox(west, east, south, north);
    }
}
