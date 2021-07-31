package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker {

    private static int width;
    private static int height;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();


    public static Maze generateMaze(int w, int h) {
        width = w;
        height = h;
        maze = new Maze(width, height);

        //4. select a random cell to start
        Random rand = new Random();
        int r1 = rand.nextInt(maze.getWidth());
        int r2 = rand.nextInt(maze.getWidth());

        //5. call selectNextPath method with the randomly selected cell
        selectNextPath(maze.getCell(r1, r2));

        return maze;
    }

    //6. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {
        //A. mark cell as visited
        currentCell.setBeenVisited(true);
        //B. Get an ArrayList of unvisited neighbors using the current cell and the method below
        ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);
        //C. if has unvisited neighbors,
        if (unvisited.size() > 0) {
            Random r = new Random();
            int nextCell = r.nextInt(unvisited.size());
            Cell c = unvisited.get(nextCell);
            uncheckedCells.push(c);
            removeWalls(c, currentCell);
            currentCell = c;
            currentCell.setBeenVisited(true);
            selectNextPath(currentCell);
        }
        //C1. select one at random.

        //C2. push it to the stack

        //C3. remove the wall between the two cells

        //C4. make the new cell the current cell and mark it as visited

        //C5. call the selectNextPath method with the current cell


        //D. if all neighbors are visited
        if (uncheckedCells.size() > 0) {
            Cell popCell = uncheckedCells.pop();
            currentCell = popCell;
            selectNextPath(currentCell);
        }
        //D1. if the stack is not empty

        // D1a. pop a cell from the stack

        // D1b. make that the current cell

        // D1c. call the selectNextPath method with the current cell


    }

    //7. Complete the remove walls method.
    //   This method will check if c1 and c2 are adjacent.
    //   If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getY() == c2.getY() &&  Math.abs(c1.getX()-c2.getX()) == 1) {
            if (c1.getX() > c2.getX()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c1.setEastWall(false);
                c2.setWestWall(false);
            }
        } else if (c1.getX() == c2.getX() && Math.abs(c1.getY() - c2.getY()) == 1) {
            if (c1.getY() > c2.getY()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c1.setSouthWall(false);
                c2.setNorthWall(false);
            }
        }
    }

    //8. Complete the getUnvisitedNeighbors method
    //   Any unvisited neighbor of the passed in cell gets added
    //   to the ArrayList
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        ArrayList<Cell> allUnvisitedNeighbors = new ArrayList<>();
        if (!c.hasBeenVisited()) {
            //todo: complete this logic
            allUnvisitedNeighbors.add(c);
        }
        return allUnvisitedNeighbors;
    }
}
