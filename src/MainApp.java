
public class MainApp {
    public static void main(String[] args) {

        Maze maze= new Maze(4,4);
        maze.checkoutMaze();

        maze.findPath(maze.getStartPoint());





    }
}
