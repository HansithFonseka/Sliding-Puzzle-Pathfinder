import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Slideway {
    private final char[][] puzzle; // puzzle array
    private final boolean[][] visitedArray; // array to hold the visited positions
    private Places start; // starting position
    private Places end; // ending position
    private final ArrayList<Places> nodeQueue = new ArrayList<>(); // arraylist to hold the nodes to be evaluated

    /**
     * constructor
     * @param puzzle the puzzle
     */
    
    public Slideway(char[][] puzzle){
        this.visitedArray = new boolean[puzzle.length][puzzle.length];
        this.puzzle = puzzle;
    }

    /**
     * Method to find the starting position
     */

    private void findStartPlace() {
        for( int i = 0 ; i<puzzle.length; i++){
            for ( int j = 0; j < puzzle[i].length; j++){
                String start = "S";
                if (puzzle[i][j] == start.charAt(0)){
                    this.start = new Places(i,j);
                }
            }
        }
    }

    /**
     * Method to find the ending position
     */
    
    private void findEndPlace() {
        for( int i = 0 ; i<puzzle.length; i++){
            for ( int j = 0; j < puzzle[i].length; j++){
                String start = "F";
                if (puzzle[i][j] == start.charAt(0)){
                    end = new Places(i,j);
                }
            }
        }
    }