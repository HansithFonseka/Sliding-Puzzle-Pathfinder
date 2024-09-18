import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Slideway {
    private final char[][] puzzle; // puzzle array
    private final boolean[][] visitedArray; // array to hold the visited positions
    private Places start; // starting position
    private Places end; // ending position
    private final ArrayList<Places> nodeQueue = new ArrayList<>(); // arraylist to hold the nodes to be evaluated

}