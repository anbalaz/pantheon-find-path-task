import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int width;
    private int length;
    private String[][] maze;
    private boolean[][] isUsed;
    private Random randomNumber = new Random();
    private static final String FREE_ELEMENT = ".";
    private static final String BLOCKED_ELEMENT = "#";
    private static final String START_POSITION = "S";
    private static final String TARGET_POSITION = "X";
    private String field;


    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new String[width][length];
        this.isUsed = new boolean[width][length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeObstaclesFreeField();
                if (maze[i][j].equals(BLOCKED_ELEMENT)) {
                    isUsed[i][j] = true;
                } else {
                    isUsed[i][j] = false;
                }
            }
        }

        this.randomMazeField(START_POSITION, TARGET_POSITION);
        this.randomMazeField(TARGET_POSITION, START_POSITION);
    }

    public void checkoutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < isUsed.length; i++) {
            for (int j = 0; j < isUsed[i].length; j++) {
                System.out.print(isUsed[i][j] + "\t");
            }
            System.out.println();
        }

    }

    private void randomMazeField(String insertField, String otherField) {
        int randomX = -1;
        int randomY = -1;

        do {
            randomX = randomNumber.nextInt(width);
            randomY = randomNumber.nextInt(length);
        }
        while (maze[randomX][randomY].equals(otherField));
        maze[randomX][randomY] = insertField;

    }

    private String randomMazeObstaclesFreeField() {
        if (randomNumber.nextInt(10) < 9) {
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

        points.add(new Point(x + 1, y));
        points.add(new Point(x - 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x, y - 1));

        for (int i = 0; i < points.size(); i++) {
            Point currPoint = points.get(i);

            if (currPoint.x < 0 || currPoint.x > width - 1 || currPoint.y < 0 || currPoint.y > length - 1 || isUsed[currPoint.x][currPoint.y]) {
                points.remove(currPoint);
                i--;
            }
            for (Point pint : points) {
                System.out.println(pint);
            }
            System.out.println();
        }
        return points;
    }

    public void findPath(Point sourcePoint) {

        isUsed[sourcePoint.x][sourcePoint.y] = true;

        if (solvedPath(sourcePoint)) {
            System.out.println("Mame hotovo vole!!!");
            System.out.println();
            System.out.println("dobra praca");
            this.checkoutMaze();

        }

        ArrayList<Point> possiblePositions = getPossiblePositions(sourcePoint);

        if (possiblePositions.isEmpty()) {
            isUsed[sourcePoint.x][sourcePoint.y] = false;
            return;
        } else {
            for (int i = 0; i < possiblePositions.size(); i++) {
                Point movePoint = possiblePositions.get(i);
                System.out.println("---------------------------------");
                System.out.println(movePoint);
                System.out.println("---------------------------------");
                this.findPath(movePoint);
            }
            isUsed[sourcePoint.x][sourcePoint.y] = false;
            return;
        }
    }

    public boolean solvedPath(Point sourcePoint) {
        if (maze[sourcePoint.x][sourcePoint.y].equals(TARGET_POSITION)) {
            System.out.println(sourcePoint);
            return true;
        }
        return false;
    }


}
