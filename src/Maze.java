import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private String[][] maze;
    private List<String> mazeFields;

    public Maze(int x, int y) {
        this.maze = new String[x][y];
        this.mazeFields = new ArrayList<>();
        mazeFields.add(".");
        mazeFields.add("#");
        mazeFields.add("S");
        mazeFields.add("X");

        Random randomNumber = new Random();
        String randomElement;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = mazeFields.get(randomNumber.nextInt(mazeFields.size()));
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
}
