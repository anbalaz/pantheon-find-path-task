import java.awt.*;
import java.util.ArrayList;

public class MazeResolver {

    private ArrayList<String> pathDirectionString;
    private ArrayList<ArrayList<String>> allPossiblePaths;

    private PathToString pathResolver;
    private Maze maze;


    public MazeResolver(int width, int length) {
        this.pathDirectionString = new ArrayList<>();
        this.allPossiblePaths = new ArrayList<>();
        this.pathResolver = new PathToString();
        this.maze = new Maze(width, length);
    }

    /**
     * This method runs whole maze solution from creating maze, through find paths and printing them out.
     */
    public void runTheMaze() {
        maze.mazeInitializer();
        maze.printOutMaze();
        this.findPath(maze.getStartPoint());
        this.printOutErrorAndShortestPath();
        this.printOutShortestPath();
    }

    /**
     * This method printOut to console error message if there is no path or beginning sentence for Shortest path.
     */
    private void printOutErrorAndShortestPath() {
        if (allPossiblePaths.isEmpty()) {
            System.out.println("Error, there is no possible path in this maze!");
        } else {
            System.out.println("\nShortest path/s is/are:");
        }
    }

    private void printOutShortestPath() {
        if (!allPossiblePaths.isEmpty()) {
            sortListOfPossiblePathsByLength();
            for (int i = 0; i < allPossiblePaths.size(); i++) {
                if (allPossiblePaths.get(0).size() == allPossiblePaths.get(i).size()) {
                    for (String a : allPossiblePaths.get(i)) {
                        System.out.print(a + ",");
                    }
                    System.out.println();
                }
            }

        }
    }

    /**
     * This method via recursion is looking for every possible paths from start to finish.
     */
    private void findPath(Point sourcePoint) {
        maze.getUsedPoints()[sourcePoint.x][sourcePoint.y] = true;

        if (this.isPathSolved(maze, sourcePoint)) {
            this.printOutPathDirectionString();
            allPossiblePaths.add(new ArrayList<>(pathDirectionString));
        }

        ArrayList<Point> possiblePositions = PositionsToMove.getPossiblePositions(maze, sourcePoint);

        if (!possiblePositions.isEmpty()) {
            for (int i = 0; i < possiblePositions.size(); i++) {
                Point movePoint = possiblePositions.get(i);
                this.pathDirectionString.add(pathResolver.directionResolver(sourcePoint, movePoint));
                this.findPath(movePoint);
            }
        }

        maze.getUsedPoints()[sourcePoint.x][sourcePoint.y] = false;
        if (!pathDirectionString.isEmpty()) {
            this.pathDirectionString.remove(this.pathDirectionString.size() - 1);
        }
    }

    private void sortListOfPossiblePathsByLength() {
        allPossiblePaths.sort((path1, path2) -> {
            int returnVal = 0;
            if (path1.size() < path2.size()) {
                returnVal = -1;
            } else if (path1.size() > path2.size()) {
                returnVal = 1;
            }
            return returnVal;
        });
    }

    /**
     * This method is checking if we did find TARGET_POSITION=finish.
     */
    private boolean isPathSolved(Maze maze, Point sourcePoint) {
        if (maze.getMaze()[sourcePoint.x][sourcePoint.y] == Maze.TARGET_POSITION) {
            return true;
        }
        return false;
    }

    private void printOutPathDirectionString() {
        System.out.println();
        for (String a : pathDirectionString) {
            System.out.print(a + ",");
        }
        System.out.println();
    }
}
