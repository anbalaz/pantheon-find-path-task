import java.awt.*;
import java.util.Random;

public class Maze {

    private int width;
    private int length;
    private String[][] maze;
    private boolean[][] usedPoints;
    private Random mazeRandomizer = new Random();
    private Point startPoint;

    private static final String FREE_ELEMENT = ".";
    private static final String BLOCKED_ELEMENT = "#";
    private static final String START_POSITION = "S";
    public static final String TARGET_POSITION = "X";

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public boolean[][] getUsedPoints() {
        return usedPoints;
    }

    public String[][] getMaze() {
        return maze;
    }

    public static String getTargetPosition() {
        return TARGET_POSITION;
    }

    public Point getStartPoint() {
        return startPoint;
    }


    public void mazeInitializer(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new String[width][length];
        this.usedPoints = new boolean[width][length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeObstaclesAndFreeField();
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

    public void printOutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private Point randomStartFinishPoint(String fieldToInsert, String fieldToAvoid) {
        int randomX = -1;
        int randomY = -1;

        do {
            randomX = mazeRandomizer.nextInt(width);
            randomY = mazeRandomizer.nextInt(length);
        }
        while (maze[randomX][randomY].equals(fieldToAvoid));
        maze[randomX][randomY] = fieldToInsert;
        usedPoints[randomX][randomY] = false;
        return new Point(randomX, randomY);
    }

    private String randomMazeObstaclesAndFreeField() {
        String field;
        if (mazeRandomizer.nextInt(10) < 4) {
            field = FREE_ELEMENT;
        } else {
            field = BLOCKED_ELEMENT;
        }
        return field;
    }
}
