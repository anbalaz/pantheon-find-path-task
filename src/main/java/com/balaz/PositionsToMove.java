package com.balaz;

import java.awt.*;
import java.util.ArrayList;

public class PositionsToMove {
    /**
     * This method looks for every possible moves from Point where it is currently pointing.
     */
    public static ArrayList<Point> getPossiblePositions(Maze maze, Point point) {
        int x = point.x;
        int y = point.y;

        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(x + 1, y));
        points.add(new Point(x - 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x, y - 1));

        points.removeIf(currPoint->(currPoint.x < 0 ||
                currPoint.x > (maze.getWidth() - 1) ||
                currPoint.y < 0 ||
                currPoint.y > (maze.getLength() - 1) ||
                maze.getUsedPoints()[currPoint.x][currPoint.y]));

        return points;
    }
}
