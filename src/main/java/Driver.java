import Database.DAOs.CityDao;
import Database.DatabaseManager;
import Representation.BaseCity;
import Utils.Randomizer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        //Initialize knowledge base ******************************
        DatabaseManager db = new DatabaseManager();
        db.initializeConnectionDatabase();

        //ONLY run this command first time using database
        //db.initializeTables();
        //^^^^^^^^^^^^^^^^^^^^^^^^^
        CityDao cityDao = db.getCityDao();
        List<BaseCity> population = cityDao.getAllCities();
        runSimulation(population, Integer.getInteger(args[0]), Integer.getInteger(args[1]));
    }

    @SuppressWarnings("Since15")
    private static void runSimulation(List<BaseCity> population, int generations, int populationSize) {
        Comparator<BaseCity> sorter = new Comparator<BaseCity>() {
            @Override
            public int compare(BaseCity o1, BaseCity o2) {
                if (o1.getValue() == o2.getValue()) {
                    return 0;
                }
                return o1.getValue() > o2.getValue() ? 1 : -1;
            }
        };
        List<Double> probabilities;
        population.sort(sorter);
        for (int i = 0; i < generations; ++i) {
            probabilities = generateProbabilityList(population);
            int indexOne = Randomizer.randomWeightedIndex(probabilities);
            int indexTwo;
            do {
                indexTwo = Randomizer.randomWeightedIndex(probabilities);
            } while (indexOne == indexTwo);
            double alpha = Math.exp(-(i+1)/ generations) - .15;
            population.get(indexOne).breed(population.get(indexTwo), population, alpha);
            population.sort(sorter);
            while(population.size() > populationSize)  {
                population.remove(population.size() - 1);
            }
        }
    }

    private static List<Double> generateProbabilityList(List<BaseCity> population) {
        double max = population.get(0).getValue();
        double min = population.get(population.size() - 1).getValue();
        List<Double> probabilities = new ArrayList<Double>();
        for (int i = 0; i < population.size(); ++i) {
            probabilities.add((population.get(i).getValue() - min) / max);
        }
        return probabilities;
    }
}