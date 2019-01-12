package com.balaz;

public class MainApp {
    public static void main(String[] args) {


//        FindPathInputReaderRandomGenerated findPathInputReaderRandomGenerated = new FindPathInputReaderRandomGenerated();
//        Maze maze1 = findPathInputReaderRandomGenerated.createMaze(15, 5);
//
//
//        FindPathInputReaderFile findPathInputReaderFile = new FindPathInputReaderFile();
//        Maze maze = findPathInputReaderFile.createMaze("myFile.txt");
//
//        MazeResolver mazeResolver = new MazeResolver();
//
//        if (!maze.isMazeInvalid()) {
//            mazeResolver.solveTheMaze(maze);
//        }

        FindPathInputReaderStdIn findPathInputReaderStdIn = new FindPathInputReaderStdIn();
        MazeResolver mazeResolver = new MazeResolver();
        Maze maze = findPathInputReaderStdIn.createMaze();
        if (!maze.isMazeInvalid()) {
            mazeResolver.solveTheMaze(maze);
//        }

        }

    }
}