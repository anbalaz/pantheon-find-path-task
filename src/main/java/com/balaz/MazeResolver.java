package com.balaz;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MazeResolver {

    private ArrayList<String> pathDirectionString;
    private ArrayList<ArrayList<String>> allPossiblePaths;

    private PathToString pathResolver;
    private Maze maze;


    public MazeResolver(int width, int length) {
        this.pathDirectionString = new ArrayList<>();
        this.allPossiblePaths = new ArrayList<>();
        this.pathResolver = new PathToString();
        this.maze = new Maze(width, length);
    }

    /**
     * Runs whole maze solution from creating maze, through find paths and printing them out.
     */
    public void runTheMaze() {
        maze.generateRandomly();
        maze.print();
        findPath(maze.getStartPoint());
        printOutShortestPath();
    }

    /**
     * Printout shortest paths or error message if there is none.
     */
    private void printOutShortestPath() {
        if (!allPossiblePaths.isEmpty()) {

            System.out.println("\nShortest path/s is/are:\n");
            sortListOfPossiblePathsByLength();

            allPossiblePaths.forEach(possiblePath -> {
                if (possiblePath.size() == allPossiblePaths.get(0).size()) {
                    possiblePath.forEach(a ->
                            System.out.print(a + ",")
                    );
                    System.out.println();
                }
            });
        } else {
            System.out.println("Error, there is no possible path in this maze!");
        }
    }

    /**
     * Via recursion this method is looking for every possible paths from start to finish.
     */
    private void findPath(Point sourcePoint) {
        maze.getUsedPoints()[sourcePoint.x][sourcePoint.y] = true;

        if (isPathSolved(maze, sourcePoint)) {
            printOutPathDirectionString();
            allPossiblePaths.add(new ArrayList<>(pathDirectionString));
        }

        ArrayList<Point> possiblePositions = PositionsToMove.getPossiblePositions(maze, sourcePoint);

        possiblePositions.forEach(movePoint -> {
            pathDirectionString.add(pathResolver.directionResolver(sourcePoint, movePoint));
            findPath(movePoint);
        });

        if (!pathDirectionString.isEmpty()) {
            pathDirectionString.remove(pathDirectionString.size() - 1);
        }

        maze.getUsedPoints()[sourcePoint.x][sourcePoint.y] = false;
    }

    private void sortListOfPossiblePathsByLength() {
        allPossiblePaths.sort(Comparator.comparingInt(ArrayList::size));
    }


    private boolean isPathSolved(Maze maze, Point sourcePoint) {

        return (maze.getMaze()[sourcePoint.x][sourcePoint.y] == Maze.TARGET_POSITION);
    }

    private void printOutPathDirectionString() {
        System.out.println();

        pathDirectionString.forEach((pathDirection) -> System.out.print(pathDirection + ","));

        System.out.println();
    }
}
