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

    public Point getStartPoint() {
        return startPoint;
    }

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new String[width][length];
        this.usedPoints = new boolean[width][length];
    }

    public void mazeInitializer() {
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
        this.randomStartFinishPoint(START_POSITION, TARGET_POSITION);
    }

    public void printOutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void randomStartFinishPoint(String startPosition, String targetPosition) {
        int randomStartX = -1;
        int randomStartY = -1;
        int randomTargetX = -1;
        int randomTargetY = -1;

        do {
            randomStartX = mazeRandomizer.nextInt(width);
            randomStartY = mazeRandomizer.nextInt(length);
            randomTargetX = mazeRandomizer.nextInt(width);
            randomTargetY = mazeRandomizer.nextInt(length);
        }

        while (maze[randomStartX][randomStartY].equals(maze[randomTargetX][randomTargetY]));

        maze[randomStartX][randomStartY] = startPosition;
        maze[randomTargetX][randomTargetY] = targetPosition;

        this.startPoint = new Point(randomStartX, randomStartY);
        usedPoints[randomStartX][randomStartY] = false;
        usedPoints[randomTargetX][randomTargetY] = false;

    }

    private String randomMazeObstaclesAndFreeField() {
        String field;
        if (mazeRandomizer.nextInt(10) < 7) {
            field = FREE_ELEMENT;
        } else {
            field = BLOCKED_ELEMENT;
        }
        return field;
    }
}
