package com.balaz;

public class MainApp {
    public static void main(String[] args) {

        MazeResolver mazeResolver = new MazeResolver(4, 4);
        mazeResolver.runTheMaze();
    }
}
