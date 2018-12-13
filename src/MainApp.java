
public class MainApp {
    public static void main(String[] args) {

        Maze maze= new Maze(5,5);
        maze.checkoutMaze();

        maze.findPath(maze.getStartPoint());





    }
}
