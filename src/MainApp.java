import java.awt.*;

public class MainApp {
    public static void main(String[] args) {

        Maze maze= new Maze(5,5);
        maze.checkoutMaze();

        Point point = new Point(5, 5);
        maze.getPossiblePositions(point);





    }
}
