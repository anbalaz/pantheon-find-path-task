package com.balaz;

public class MainApp {
    public static void main(String[] args) {


        FindPathInputReaderRandomGenerated findPathInputReaderRandomGenerated= new FindPathInputReaderRandomGenerated();
        Maze maze= findPathInputReaderRandomGenerated.createMaze(15,5);

        MazeResolver mazeResolver = new MazeResolver();
        mazeResolver.runTheMaze(maze);

//        FindPathInputReaderFile findPathInputReaderFile= new FindPathInputReaderFile();
//        findPathInputReaderFile.validateLengthOfLines(findPathInputReaderFile.readFile("myFile.txt"));
    }
}
