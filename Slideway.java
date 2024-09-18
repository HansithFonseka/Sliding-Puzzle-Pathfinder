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

        /**
     * Method to slide the node
     * @param position the node to slide
     * @param x the increment of the x value
     * @param y the increment of the y value
     */

    public void slipNode(Places position, int x, int y) {
        int row = position.getRowNumber();
        int column = position.getColumnNumber();

        while(true) {
            row += y;
            column += x;

            if (!checkValidPlace(row, column)) { //checking if the position is valid or not
                break;
            }

            if (puzzle[row][column] == 'F') {  //if the position is the end position
                Places nextItem = new Places(row, column); // create a position object and store the x and y values
                nextItem.setParent(position); // set the parent or the previous position of the new node
                nodeQueue.add(0, nextItem);    //add the node to the front of the queue
                nextItem.direction = getDirection(position,nextItem);   //get the direction of the node movement
                visitedArray[row][column] = true; //set visited in the array true
                break;
            }

            int nextRow  = row + y;
            int nextColumn = column + x;
            // if the next position is a wall or a rock the position before is stored
            if ((nextRow < 0 || nextColumn < 0) || (nextRow >= puzzle.length || nextColumn >= puzzle.length) || (puzzle[nextRow][nextColumn] == '0')) {
                Places nextItem = new Places(row, column);
                nextItem.setParent(position);
                nodeQueue.add(nextItem);
                nextItem.direction = getDirection(position,nextItem);
                visitedArray[row][column] = true;
                break;
            }
        }
    }

    /**
     * Method to get the direction
     * @param node current position of the node
     * @param neighbour next position of the node
     * @return the direction of the node has moved from the previous node
     */
    private String getDirection(Places node, Places neighbour) {
        String direction = "";
        if (neighbour.getColumnNumber() < node.getColumnNumber()){
            direction = "Left";
        }
        if (neighbour.getColumnNumber() > node.getColumnNumber()){
            direction = "Right";
        }
        if (neighbour.getRowNumber() > node.getRowNumber()){
            direction = "Down";
        }
        if (neighbour.getRowNumber() < node.getRowNumber()){
            direction = "Up";
        }
        return direction;
    }

    /**
     * Method to display the path
     * @param currentPosition the final position used to back track to the start position to find the path
     */
    private void displayPath(Places currentPosition) throws IOException {

        ArrayList<Places> path = new ArrayList<>();
        Places currentNode = currentPosition;
        while (currentNode.getParent() != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        System.out.println("01. Start at (" + (start.getColumnNumber()+1) + "," + (start.getRowNumber()+1) + ")");

        int count = 2;
        for (Places places : path) {
            if (count < 10){
                System.out.println("0" + count  + ". " + "Move " + places.direction + " To (" + (places.getColumnNumber()+1) + "," + (places.getRowNumber()+1) + ")");
            }
            else {
                System.out.println(count  + ". " + "Move " + places.direction + " To (" + (places.getColumnNumber()+1) + "," + (places.getRowNumber()+1) + ")");
            }
            count++;
        }
        System.out.println(count + ". "  + "Done!");
    }
}
