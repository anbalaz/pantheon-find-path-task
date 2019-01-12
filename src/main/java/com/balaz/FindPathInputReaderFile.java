package com.balaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {

    private List<String> mazeStrings = new ArrayList<>();

    public List<String> readFile(String nameOfFile) {

        try {
            File file = new File(nameOfFile);
            Scanner scanner = new Scanner(file);
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                mazeStrings.add(line);
            }
            mazeStrings.forEach(System.out::println);
        } catch (FileNotFoundException exception) {
            System.out.println("The File was not found");
        }
        return mazeStrings;
    }

    public Maze createMaze(String nameOfFile) {

        boolean isValidationOk = validateMazeInput(readFile(nameOfFile));

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
}
