import java.awt.*;
import java.util.ArrayList;

public class MazeResolver {

    private ArrayList<String> pathDirectionString = new ArrayList<>();
    private ArrayList<ArrayList<String>> allPossiblePaths = new ArrayList<>();

    private PositionsToMove positionsToMove = new PositionsToMove();
    private PathResolver pathResolver = new PathResolver();
    private Maze maze = new Maze();

    public void runTheMaze() {
        maze.mazeInitializer(5, 5);
        maze.printOutMaze();
        this.findPath(maze.getStartPoint());

        this.solutionAnswer();
        this.shortestPath();
    }


    public void solutionAnswer() {
        if (allPossiblePaths.isEmpty()) {
            System.out.println("Error, there is no possible path in this maze!");
        }
    }

    public void shortestPath() {
        if (!allPossiblePaths.isEmpty()) {
            sortListByLength();
            System.out.println("Shortest pathResolver is:");
            for (String a : allPossiblePaths.get(0)) {
                System.out.print(a + ",");
            }
        }
    }

    public void findPath(Point sourcePoint) {
        maze.getUsedPoints()[sourcePoint.x][sourcePoint.y] = true;

        if (pathResolver.isPathSolved(maze, sourcePoint)) {
            System.out.println();
            for (String a : pathDirectionString) {
                System.out.print(a + ",");
            }
            System.out.println();
            allPossiblePaths.add(new ArrayList<>(pathDirectionString));
        }
        ArrayList<Point> possiblePositions = positionsToMove.getPossiblePositions(maze, sourcePoint);

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

    public void sortListByLength() {
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
}
