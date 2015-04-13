package MainPackage;

import java.util.Random;

/**
 *
 * @author santiago
 */
public class Comun {
    
    
    String macRandom() {

        Random r = new Random();
        char[] posibilities = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String result = "";

        for (int i = 0; i < 6; i++) {

            result = result + posibilities[r.nextInt(16)] + posibilities[r.nextInt(16)] + ":";

        }
        return result.substring(0, 17);
    }
    
    
}
