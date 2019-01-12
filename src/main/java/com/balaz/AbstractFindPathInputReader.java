package com.balaz;

import java.util.List;

public abstract class AbstractFindPathInputReader {


    public boolean validateMazeInput(List<String> mazeStrings) {
        int lineLength = mazeStrings.get(0).length();

        String startPositionString = Character.toString(MazeAllowedChar.START_POSITION);
        String targetPositionString = Character.toString(MazeAllowedChar.TARGET_POSITION);

        int startPositionCount = 0;
        int targetPositionCount = 0;

        for (String line : mazeStrings) {
            if (lineLength != line.length()) {
                System.out.println("Uneven length of lines");
                return false;

            }

            if ((isSignsOutOfRange(line))) {
                System.out.println("There have been used invalid characters");
                return false;

            }

            if (line.contains(startPositionString)) {
                startPositionCount++;
            }

            if (line.contains(targetPositionString)) {
                targetPositionCount++;
            }
        }
        if (!(startPositionCount==1) || !(targetPositionCount==1)) {
            System.out.println("There is not valid amount of start or end position");
            return false;
        }

        return true;
    }

    public boolean isSignsOutOfRange(String line) {

        for (int i = 0; i < line.length(); i++) {
            char lineChar = line.charAt(i);
            if (!MazeAllowedChar.isAllowedChar(lineChar)) {
                return true;
            }
        }
        return false;

    }

}
