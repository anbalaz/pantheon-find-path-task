import java.util.Collections;

public class MainApp {
    public static void main(String[] args) {

        Maze maze = new Maze(3, 3);
        System.out.println("This is our maze:");
        maze.checkoutMaze();
        System.out.println("Possible paths:");
        maze.findPath(maze.getStartPoint());
        maze.solutionAnswer();
        maze.shortestPath();



    }
}
