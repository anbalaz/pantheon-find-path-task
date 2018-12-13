import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<String> pathDirectionString;


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

        this.startPoint=this.randomStartFinishPoint(START_POSITION, TARGET_POSITION);
        this.randomStartFinishPoint(TARGET_POSITION, START_POSITION);
    }

    public void checkoutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < usedPoints.length; i++) {
            for (int j = 0; j < usedPoints[i].length; j++) {
                System.out.print(usedPoints[i][j] + "\t");
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
        return new Point(randomX,randomY);
    }

    private String randomMazeObstaclesFreeField() {
        String field;
        if (mazeRandomizer.nextInt(10) < 9) {
            field = FREE_ELEMENT;
        } else {
            field = BLOCKED_ELEMENT;
        }
        return field;
    }

    private ArrayList<Point> getPossiblePositions(Point point) {
        int x = point.x;
        int y = point.y;

        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(x + 1, y));//d
        points.add(new Point(x - 1, y));//u
        points.add(new Point(x, y + 1));//r
        points.add(new Point(x, y - 1));//l

        for (int i = 0; i < points.size(); i++) {
            Point currPoint = points.get(i);

            if (currPoint.x < 0 || currPoint.x > width - 1 || currPoint.y < 0 || currPoint.y > length - 1 || usedPoints[currPoint.x][currPoint.y]) {
                points.remove(currPoint);
                i--;
            }
//            for (Point pint : points) {
//                System.out.println(pint);
//            }
//            System.out.println();
        }
        return points;
    }

    public void findPath(Point sourcePoint) {

        usedPoints[sourcePoint.x][sourcePoint.y] = true;

        if (isPathSolved(sourcePoint)) {
            System.out.println();
            System.out.println("dobra praca");
            this.checkoutMaze();

        }

        ArrayList<Point> possiblePositions = getPossiblePositions(sourcePoint);

        if (!possiblePositions.isEmpty()) {
            for (int i = 0; i < possiblePositions.size(); i++) {
                Point movePoint = possiblePositions.get(i);
//                System.out.println("---------------------------------");
//                System.out.println(movePoint);
//                System.out.println("---------------------------------");
                this.findPath(movePoint);
            }
        }
        usedPoints[sourcePoint.x][sourcePoint.y] = false;
        return;
    }

    public boolean isPathSolved(Point sourcePoint) {
        if (maze[sourcePoint.x][sourcePoint.y].equals(TARGET_POSITION)) {
            System.out.println(sourcePoint);
            return true;
        }
        return false;
    }
    public String direction(){

        return null;
    }

}
