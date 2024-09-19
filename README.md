# Sliding-Puzzle-Pathfinder

This project is a Java implementation for solving sliding puzzles, where the player navigates from a start point ('S') to a finish point ('F') on a grid covered with frictionless ice. The player slides in the chosen direction until they hit a wall or an obstacle ('0').

Features
Map Representation: Represents the puzzle map in a 2D array.
Parser: Reads and interprets puzzle maps from input files.
Pathfinding Algorithm: Finds and displays the shortest path from the start to the finish considering sliding mechanics.
Classes
Main: Handles input file reading, puzzle initialization, and validation. Starts the pathfinding process.
Places: Represents a position on the puzzle grid with row and column numbers and the direction of movement.
Slideway: Contains the logic for pathfinding, including sliding mechanics and displaying the solution path.
Usage
Prepare the Puzzle File: Create a text file with the puzzle map. The file should use '.' for empty squares, '0' for obstacles, 'S' for the start, and 'F' for the finish.

Example puzzle file (example.txt):

Copy code
.....0...S
....0.....
0.....0..0
...0....0.
.F......0.
.0........
.......0..
.0.0..0..0
0.........
.00.....0.
Compile the Code: Compile the Java files.

bash
Copy code
javac Main.java Places.java Slideway.java
Run the Program: Execute the Main class, providing the name of the puzzle file (without extension) as input.

bash
Copy code
java Main
Example:

bash
Copy code
Enter File Name
example
View the Solution: The program will output the steps to solve the puzzle, showing the moves from start to finish.

Example Output
scss
Copy code
01. Start at (10,1)
02. Move Left To (7,1)
03. Move Down To (7,2)
04. Move Left To (6,2)
...
18. Done!
Notes
Ensure the puzzle file is placed in the benchmarks/ directory.
The solution will handle puzzles with a guaranteed solution.
If the puzzle file is invalid (missing 'S' or 'F'), the program will notify you.
Contributing
Feel free to fork the repository and submit pull requests. Issues and feature requests are welcome.
