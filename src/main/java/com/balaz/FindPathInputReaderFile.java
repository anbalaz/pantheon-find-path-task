package com.balaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {

    private List<String> mazeStrings = new ArrayList<>();

    private List<String> readFile(String nameOfFile) {
        try {
            File file = new File(nameOfFile);
            Scanner scanner = new Scanner(file);
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                mazeStrings.add(line);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("The File was not found, please check if you put correct name or put it in the right directory");
        }

        return mazeStrings;
    }

    public Maze createMaze(String nameOfFile) {

        boolean isValidationOk = validateMazeInput(readFile(nameOfFile));
        if (!isValidationOk) {
            return new Maze(0, 0);
        }
        return super.parseListToArray(mazeStrings);
    }
}
