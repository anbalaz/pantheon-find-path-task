package com.balaz;

import java.util.List;

public abstract class AbstractFindPathInputReader {


    public boolean validateLengthOfLines(List<String> mazeStrings) {
        int lineLength = mazeStrings.get(0).length();

        String aloowedCharsRegex = String.format("^[%s-%s-%s-%s]+$",
                MazeAllowedChar.FREE_ELEMENT, MazeAllowedChar.BLOCKED_ELEMENT,
                MazeAllowedChar.START_POSITION, MazeAllowedChar.TARGET_POSITION);

        String startPositionString = Character.toString(MazeAllowedChar.START_POSITION);
        String targetPositionString = Character.toString(MazeAllowedChar.TARGET_POSITION);

        int startPositionCount = 0;
        int targetPositionCount = 0;

        for (String line : mazeStrings) {
            if (lineLength != line.length()) {
                System.out.println("Uneven length of lines");
                return false;
            }

            if (!line.matches("aloowedCharsRegex")) {
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
        if (!(startPositionCount > 0 && startPositionCount < 2) && !(targetPositionCount > 0 && targetPositionCount < 2)) {
            return false;
        }

        return true;
    }

}
