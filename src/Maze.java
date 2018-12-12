import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private String[][] maze;
    private List<String> mazeFields;

    public Maze(int x, int y) {
        this.maze = new String[x][y];
        this.createListOfMazeFields();


        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = randomMazeField();
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

    private String randomMazeField() {
        Random randomNumber = new Random();
        String field = mazeFields.get(randomNumber.nextInt(mazeFields.size()));
        if(field.equals("X")|| field.equals("S")){
            mazeFields.remove(field);
        }
        return field;
    }

}
