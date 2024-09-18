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

        /**
     * Method to check if the position noe position is valid
     * @param row  row number of the node
     * @param column column number of the node
     * @return true or false according to the valid positioning of the node
     */
    
    public boolean checkValidPlace(int row, int column) {
        return row >= 0 && column >= 0
                && row < puzzle.length && column < puzzle[0].length
                && puzzle[row][column] != '0'
                && !visitedArray[row][column];
    }

    /**
     * Method to find sliding path in the puzzle
     */

    public void findPlace() throws IOException {
        findStartPlace();    // identifying the starting position
        findEndPlace();      // identifying the ending position

        nodeQueue.add(start);    //adding the starting position to the queue to be evaluated
        visitedArray[start.getRowNumber()][start.getColumnNumber()] = true;   //setting the position true in the visited array
        Places currentPosition;
        boolean pathFound = false;

        while (!nodeQueue.isEmpty()) {      //loop until all the nodes in the queue are evaluated
            currentPosition = nodeQueue.remove(0);  //removing the first node
            int rowNumberVisited = currentPosition.getRowNumber();  //getting the row number of that node
            int columnNumberVisited = currentPosition.getColumnNumber();    //getting the column number of that node

            if (puzzle[rowNumberVisited][columnNumberVisited] == 'F') { //if the row number and column number at the position F the path is found
                System.out.println("Path Found");
                displayPath(currentPosition);   //displaying the path
                pathFound = true;
                break;
            }
            slipNode(currentPosition, -1, 0);      //sliding the node to the left
            slipNode(currentPosition, 1, 0);       //sliding the node to the right
            slipNode(currentPosition, 0, 1);       //sliding the node down
            slipNode(currentPosition, 0, -1);      //sliding the node up
        }

        if (!pathFound){ //if path not found
            System.out.println("No Path Found");
        }
    }