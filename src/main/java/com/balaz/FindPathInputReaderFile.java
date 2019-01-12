package com.balaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderFile extends AbstractFindPathInputReader{

    private char[][] maze;
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
}