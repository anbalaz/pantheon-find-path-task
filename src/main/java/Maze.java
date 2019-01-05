import lombok.Getter;

import java.awt.*;
import java.util.Random;

@Getter
public class Maze {

    private int width;
    private int length;
    private char[][] maze;
    private boolean[][] usedPoints;
    private Random mazeRandomizer = new Random();
    private Point startPoint;

    private static final char FREE_ELEMENT = '.';
    private static final char BLOCKED_ELEMENT = '#';
    private static final char START_POSITION = 'S';
    public static final char TARGET_POSITION = 'X';


    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new char[width][length];
        this.usedPoints = new boolean[width][length];
    }

    /**
     * This method is giving maze array[][] string values randomly, and also usedPoints array[][] is given booleans,
     * which are depending on string values.
     */
    public void generateRandomly() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomObstacleOrFreeField();
                if (maze[i][j] == BLOCKED_ELEMENT) {
                    usedPoints[i][j] = true;
                } else {
                    usedPoints[i][j] = false;
                }
            }
        }
        this.randomStartFinishPoint(START_POSITION, TARGET_POSITION);
    }

    public void print() {
        for (char[] fields : maze) {
            for (char field : fields) {
                System.out.print(field + "\t");
            }
            System.out.println();
        }
    }

    /**
     * This method is randomly giving Start and finish position to maze array.
     */
    private void randomStartFinishPoint(char startPosition, char targetPosition) {
        int randomStartX = -1;
        int randomStartY = -1;
        int randomTargetX = -1;
        int randomTargetY = -1;

        do {
            randomStartX = mazeRandomizer.nextInt(width);
            randomStartY = mazeRandomizer.nextInt(length);
            randomTargetX = mazeRandomizer.nextInt(width);
            randomTargetY = mazeRandomizer.nextInt(length);
        } while (maze[randomStartX][randomStartY] == (maze[randomTargetX][randomTargetY]));

        maze[randomStartX][randomStartY] = startPosition;
        maze[randomTargetX][randomTargetY] = targetPosition;

        this.startPoint = new Point(randomStartX, randomStartY);
        usedPoints[randomStartX][randomStartY] = false;
        usedPoints[randomTargetX][randomTargetY] = false;
    }

    /**
     * This method is randomly giving 2 string constants.
     */
    private char randomObstacleOrFreeField() {
        char field;
        if (mazeRandomizer.nextInt(10) < 7) {
            field = FREE_ELEMENT;
        } else {
            field = BLOCKED_ELEMENT;
        }
        return field;
    }
}
