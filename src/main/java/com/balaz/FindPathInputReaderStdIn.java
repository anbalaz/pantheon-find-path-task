package com.balaz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {

    private Scanner scanner = new Scanner(System.in);
    private List<String> mazeStrings = new ArrayList<>();


    public Maze createMaze() {

        System.out.println("Welcome, please enter number of rows that maze is going to have, then press enter");
        enterTheString(consoleInt());

        mazeStrings.forEach(System.out::println);

        boolean isValidationOk = validateMazeInput(mazeStrings);

        if (!isValidationOk) {
            return new Maze(0, 0);
        }

        int rowsCount = mazeStrings.size();
        int colsCount = mazeStrings.get(0).length();

        Maze maze = new Maze(rowsCount, colsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {
                maze.setMazeField(i, j, mazeStrings.get(i).charAt(j));
            }
        }

        return maze;
    }


    public int consoleInt() {

        boolean continueInput = true;
        int consoleIntInput = 0;
        do {
            try {

                consoleIntInput = scanner.nextInt();
                continueInput = false;

            } catch (InputMismatchException ex) {
                System.out.println("Try again. (" +
                        "Incorrect input: an integer is required)");
                scanner.nextLine();
            }
        }
        while (continueInput);
        return consoleIntInput;
    }


    public void enterTheString(int numberOfRepeats) {
        for (int i = 0; i < numberOfRepeats; i++) {
            System.out.println("Please insert values for maze row, remember, you can use only this signs ., #, S, X !");
            String input = consoleString();
            mazeStrings.add(input);
        }
    }


    private String consoleString() {
        return scanner.next();
    }

}
