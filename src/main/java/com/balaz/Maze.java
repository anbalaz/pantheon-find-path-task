package com.balaz;

import lombok.Getter;

import java.awt.*;

@Getter
public class Maze {

    private int rowsCount;
    private int colsCount;
    private char[][] maze;
    private boolean[][] usedPoints;

    public Maze(int rowsCount, int colsCount) {
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        this.maze = new char[rowsCount][colsCount];
        this.usedPoints = new boolean[rowsCount][colsCount];
    }

    /**
     * Giving maze array[][] string values randomly, and also usedPoints array[][] is given booleans,
     * which are depending on string values.
     */
    public void generateUsedPointsArray() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                usedPoints[i][j] = (maze[i][j] == MazeAllowedChar.BLOCKED_ELEMENT ||
                        maze[i][j] == MazeAllowedChar.START_POSITION);
            }
        }
    }

    public void print() {
        String mazeExplanation = String.format("Here is a layout of the maze and meaning of signs:\n" +
                "\tfree element: %s \n" +
                "\tobstacle element: %s \n" +
                "\tstarting position: %s \n" +
                "\ttarget position: %s \n", MazeAllowedChar.FREE_ELEMENT, MazeAllowedChar.BLOCKED_ELEMENT, MazeAllowedChar.START_POSITION, MazeAllowedChar.TARGET_POSITION);
        System.out.println(mazeExplanation);

        for (char[] fields : maze) {
            for (char field : fields) {
                System.out.print(field + "\t");
            }
            System.out.println();
        }
    }

    public void setMazeField(int row, int column, char obstacleOrField) {
        maze[row][column] = obstacleOrField;
    }

    /**
     * Randomly giving 2 string constants.
     */
    public Point getStartPoint() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == MazeAllowedChar.START_POSITION) {

                    return new Point(i, j);
                }
            }
        }

        return new Point(0, 0);
    }

    public boolean isMazeInvalid() {
        return colsCount == 0 || rowsCount == 0;
    }
}