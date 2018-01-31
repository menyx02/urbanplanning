package Utils;

import java.util.Random;

/**
 * Created by edoyle on 1/23/18.
 */
public class Randomizer {
    private static final Random random = new Random();
    public static Double randomProbability() {
        return random.nextDouble();
    }
    public static Integer randomInteger(int min, int max) {
        return min + random.nextInt(max);
    }

    public static double generateGaussianValue(double mu) {
        double valueOne = randomProbability();
        double valueTwo = randomProbability();
        double outcomeOne = Math.sqrt(-2.0 * Math.log(valueOne)) * Math.cos((2 * Math.PI) * valueTwo);
        double outcomeTwo = Math.sqrt(-2.0 * Math.log(valueOne)) * Math.sin((2 * Math.PI) * valueTwo);
        return ((randomProbability() > .5) ? outcomeOne : outcomeTwo) * .1 + mu;
    }

}
