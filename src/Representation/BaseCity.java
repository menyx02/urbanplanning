package Representation;

import Utils.Randomizer;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.*;

/**
 * Created by edoyle on 1/23/18.
 */
public class BaseCity {
    private Map<String, Matrix> features;
    public void breed(BaseCity partner, List<BaseCity> population, Double alpha) {
        List<String> subset = new ArrayList<>();
        Set<String> used = new HashSet<>();
        while (used.size() < features.size()) {
            subset.clear();
            for (String type : features.keySet()){
                if(!used.contains(type) && Randomizer.randomProbability() < alpha){
                    subset.add(type);
                    used.add(type);
                }
            }

        }
        //Breeding logic
    }

}
