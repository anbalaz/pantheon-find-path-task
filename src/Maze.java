import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Maze {

    private int width;
    private int length;
    private String[][] maze;
    private boolean[][] usedPoints;
    private Random mazeRandomizer = new Random();
    private Point startPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    private static final String FREE_ELEMENT = ".";
    private static final String BLOCKED_ELEMENT = "#";
    private static final String START_POSITION = "S";
    private static final String TARGET_POSITION = "X";

    private ArrayList<String> pathDirectionString = new ArrayList<>();
    private ArrayList<ArrayList<String>> allPlausablePaths = new ArrayList<>();

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new String[width][length];
        this.usedPoints = new boolean[width][length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeObstaclesFreeField();
                if (maze[i][j].equals(BLOCKED_ELEMENT)) {
                    usedPoints[i][j] = true;
                } else {
                    usedPoints[i][j] = false;
                }
            }
        }

        this.startPoint = this.randomStartFinishPoint(START_POSITION, TARGET_POSITION);
        this.randomStartFinishPoint(TARGET_POSITION, START_POSITION);
    }

    public void checkoutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private Point randomStartFinishPoint(String insertField, String otherField) {
        int randomX = -1;
        int randomY = -1;

        do {
            randomX = mazeRandomizer.nextInt(width);
            randomY = mazeRandomizer.nextInt(length);
        }
        while (maze[randomX][randomY].equals(otherField));
        maze[randomX][randomY] = insertField;
        usedPoints[randomX][randomY] = false;
        return new Point(randomX, randomY);
    }

    private String randomMazeObstaclesFreeField() {
        String field;
        if (mazeRandomizer.nextInt(10) < 4) {
            field = FREE_ELEMENT;
        } else {
            field = BLOCKED_ELEMENT;
        }
        return field;
    }

    private ArrayList<Point> getPossiblePositions(Point point) {
        int x = point.x;
        int y = point.y;

        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(x + 1, y));
        points.add(new Point(x - 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x, y - 1));

        for (int i = 0; i < points.size(); i++) {
            Point currPoint = points.get(i);

            if (currPoint.x < 0 || currPoint.x > width - 1 || currPoint.y < 0 || currPoint.y > length - 1 || usedPoints[currPoint.x][currPoint.y]) {
                points.remove(currPoint);
                i--;
            }
        }
        return points;
    }

    public void findPath(Point sourcePoint) {
        usedPoints[sourcePoint.x][sourcePoint.y] = true;

        if (isPathSolved(sourcePoint)) {
            System.out.println();
            for (String a : pathDirectionString) {
                System.out.print(a + ",");
            }
            System.out.println();
            allPlausablePaths.add(new ArrayList<>(pathDirectionString));
        }
        ArrayList<Point> possiblePositions = getPossiblePositions(sourcePoint);

        if (!possiblePositions.isEmpty()) {
            for (int i = 0; i < possiblePositions.size(); i++) {
                Point movePoint = possiblePositions.get(i);
                this.pathDirectionString.add(this.directionResolver(sourcePoint, movePoint));
                this.findPath(movePoint);
            }
        }
        usedPoints[sourcePoint.x][sourcePoint.y] = false;
        if (!pathDirectionString.isEmpty()) {
            this.pathDirectionString.remove(this.pathDirectionString.size() - 1);
        }
    }

    public boolean isPathSolved(Point sourcePoint) {
        if (maze[sourcePoint.x][sourcePoint.y].equals(TARGET_POSITION)) {
            return true;
        }
        return false;
    }


    public String directionResolver(Point sourcePoint, Point movePoint) {
        String direction;
        Point subtractPoint = (subtract(sourcePoint, movePoint));

        if (subtractPoint.x == 0) {
            if (subtractPoint.y == 1) {
                direction = "l";
            } else {
                direction = "r";
            }
        } else {
            if (subtractPoint.x == 1) {
                direction = "u";
            } else {
                direction = "d";
            }
        }
        return direction;
    }

    public Point subtract(Point sourcePoint, Point movePoint) {
        return new Point(sourcePoint.x - movePoint.x, sourcePoint.y - movePoint.y);
    }

    public void solutionAnswer() {
        if (allPlausablePaths.isEmpty()) {
            System.out.println("Error, there is no possible path in this maze!");
        }
    }

    public void shortestPath() {
        if (!allPlausablePaths.isEmpty()) {
            sortListByLength();
            System.out.println("Shortest path is:");
            for (String a : allPlausablePaths.get(0)) {
                System.out.print(a + ",");
            }
        }
    }

    public void sortListByLength() {
        Collections.sort(allPlausablePaths, (path1, path2) -> {
            int returnVal = 0;
            if (path1.size() < path2.size()) {
                returnVal = -1;
            } else if (path1.size() > path2.size()) {
                returnVal = 1;
            } else if (path1.size() == path2.size()) {
                returnVal = 0;
            }
            return returnVal;
        });
    }

}
