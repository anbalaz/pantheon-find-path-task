package com.balaz;

public class MazeAllowedChar {

    public static final char FREE_ELEMENT = '.';
    public static final char BLOCKED_ELEMENT = '#';
    public static final char START_POSITION = 'S';
    public static final char TARGET_POSITION = 'X';

    public static boolean isAllowedChar(char charToCheck) {
        return (charToCheck == FREE_ELEMENT ||
                charToCheck == BLOCKED_ELEMENT ||
                charToCheck == START_POSITION ||
                charToCheck == TARGET_POSITION);
    }
}
