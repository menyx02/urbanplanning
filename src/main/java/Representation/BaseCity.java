package Representation;

import Model.Coordinates;
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

    private BaseCity(){
        this.boundary = null;
        normalized = true;
        features = new HashMap<String, Matrix>();
    }

    public BaseCity(BoundingBox boundary){
        this.boundary = boundary;
        normalized = false;
        features = new HashMap<String, Matrix>();
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
                if (kept.contains(type)) {
                    offspringOne.features.put(type, features.get(type).generateMutatedCopy(alpha));
                    offspringTwo.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
                }
                else {
                    offspringOne.features.put(type, partner.features.get(type).generateMutatedCopy(alpha));
                    offspringTwo.features.put(type, features.get(type).generateMutatedCopy(alpha));
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
        return -1;
    }
}
