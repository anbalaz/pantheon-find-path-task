package com.balaz;

import java.util.Random;

public class FindPathInputReaderRandomGenerated extends AbstractFindPathInputReader {

    private Random mazeRandomizer = new Random();

    public Maze createMaze(int rowsCount, int colsCount) {
        return generateRandomly(rowsCount, colsCount);
    }

    public Maze generateRandomly(int rowsCount, int colsCount) {
        Maze maze = new Maze(rowsCount, colsCount);

        for (int i = 0; i < maze.getRowsCount(); i++) {
            for (int j = 0; j < maze.getColsCount(); j++) {
                maze.setMazeField(i, j, randomObstacleOrFreeField());
            }
        }
        this.randomStartFinishPoint(maze);

        return maze;
    }

    private void randomStartFinishPoint(Maze maze) {
        int randomStartX = -1;
        int randomStartY = -1;
        int randomTargetX = -1;
        int randomTargetY = -1;

        do {
            randomStartX = mazeRandomizer.nextInt(maze.getRowsCount());
            randomStartY = mazeRandomizer.nextInt(maze.getColsCount());
            randomTargetX = mazeRandomizer.nextInt(maze.getRowsCount());
            randomTargetY = mazeRandomizer.nextInt(maze.getColsCount());
        } while (randomStartX == randomTargetX || randomStartY == randomTargetY);

        maze.setMazeField(randomStartX, randomStartY, MazeAllowedChar.START_POSITION);
        maze.setMazeField(randomStartX, randomTargetY, MazeAllowedChar.TARGET_POSITION);
    }

    private char randomObstacleOrFreeField() {
        char field;
        if (mazeRandomizer.nextInt(10) < 7) {
            field = MazeAllowedChar.FREE_ELEMENT;
        } else {
            field = MazeAllowedChar.BLOCKED_ELEMENT;
        }
        return field;
    }
}
