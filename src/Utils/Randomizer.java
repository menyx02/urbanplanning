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
}
