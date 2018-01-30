package Representation;

import Model.Coordinates;
import Utils.Randomizer;

import java.util.*;

/**
 * Created by edoyle on 1/23/18.
 */
public class BaseCity {
    private Map<String, Matrix> features;

    public BaseCity(){
        features = new HashMap<>();
    }

    protected void setBuildingType (String type, List<Coordinates> coordinates) {
        features.put(type, new Matrix(coordinates));
    }

    protected void breed(BaseCity partner, List<BaseCity> population, Double alpha) {
        Double featureProbability = Randomizer.randomProbability();
        Set<String> kept = new HashSet<>();
        for (String type : features.keySet()){
            if(featureProbability < Randomizer.randomProbability()) {
                kept.add(type);
            }
        }
        //Breeding logic
        generateOffspring(kept, partner, population, alpha);
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

}
