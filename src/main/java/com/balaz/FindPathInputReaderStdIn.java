package com.balaz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {

    private Scanner scanner = new Scanner(System.in);
    private List<String> mazeStrings = new ArrayList<>();


    public Maze createMaze() {

        System.out.println("Welcome to maze resolver, please enter number of rows that maze is going to have, then press enter");
        enterTheString(consoleInt());

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
        String mazeExplanationOfInput = String.format("There are couple of rules when inserting values into maze rows:\n" +
                "1.There are allowed only 4 signs in maze: \n" +
                "\tfree element: %s \n" +
                "\tobstacle element: %s \n" +
                "\tstarting position: %s \n" +
                "\ttarget position: %s \n" +
                "2.There has to be one and only one starting position and only one target position.\n" +
                "3.When inserting values, please insert even number of signs into each row or the maze will not run.\n" +
                "4.After every row, please press enter, thank you and enjoy!\n", MazeAllowedChar.FREE_ELEMENT, MazeAllowedChar.BLOCKED_ELEMENT, MazeAllowedChar.START_POSITION, MazeAllowedChar.TARGET_POSITION);

        System.out.println(mazeExplanationOfInput);

        for (int i = 0; i < numberOfRepeats; i++) {
            System.out.println("Please insert signs for the row:");
            String input = consoleString();
            mazeStrings.add(input);
        }
    }


    private String consoleString() {
        return scanner.next();
    }

}
