import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private int width;
    private int length;
    private String[][] maze;
    private List<String> mazeFields;
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
        this.createListOfMazeFields();


        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeObstaclesFreeField();
            }
        }
    }

    public void checkoutMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    private List createListOfMazeFields() {
        this.mazeFields = new ArrayList<>();
        mazeFields.add(".");
        mazeFields.add("#");
        mazeFields.add("S");
        mazeFields.add("X");
        return mazeFields;
    }

    private void randomMazeField() {

        maze[randomNumber.nextInt(width)][randomNumber.nextInt(length)] = START_POSITION;
        maze[randomNumber.nextInt(width)][randomNumber.nextInt(length)] = TARGET_POSITION;

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
