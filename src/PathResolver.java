import java.awt.*;

public class PathResolver {


    public String directionResolver(Point sourcePoint, Point movePoint) {
        String direction;
        Point subtractPoint = (subtract(sourcePoint, movePoint));

        if (subtractPoint.x == 0) {
            if (subtractPoint.y == 1) {
                direction = "l";
            } else {
                direction = "r";
            }
        } else {
            if (subtractPoint.x == 1) {
                direction = "u";
            } else {
                direction = "d";
            }
        }
        return direction;
    }

    public Point subtract(Point sourcePoint, Point movePoint) {
        return new Point(sourcePoint.x - movePoint.x, sourcePoint.y - movePoint.y);
    }

    public boolean isPathSolved(Maze maze, Point sourcePoint) {
        if (maze.getMaze()[sourcePoint.x][sourcePoint.y].equals(Maze.TARGET_POSITION)) {
            return true;
        }
        return false;
    }
}
