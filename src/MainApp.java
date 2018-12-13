import java.awt.*;

public class MainApp {
    public static void main(String[] args) {

        Maze maze= new Maze(10,10);
        maze.checkoutMaze();

        Point point = new Point(2, 2);
        maze.findPath(point);





    }
}
