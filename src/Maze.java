import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private int width;
    private int length;
    private String[][] maze;
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


        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeObstaclesFreeField();
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

}
