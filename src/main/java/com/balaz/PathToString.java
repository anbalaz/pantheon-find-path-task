package com.balaz;

import java.awt.*;

/**
 * This class changes pointers through subtraction to strings.
 */
public class PathToString {

    private static final String DIRECTION_LEFT = "l";
    private static final String DIRECTION_RIGHT = "r";
    private static final String DIRECTION_UP = "u";
    private static final String DIRECTION_DOWN = "d";


    public String directionResolver(Point sourcePoint, Point movePoint) {
        String direction;
        Point subtractPoint = (subtract(sourcePoint, movePoint));

        if (subtractPoint.x == 0) {
            if (subtractPoint.y == 1) {
                direction = DIRECTION_LEFT;
            } else {
                direction = DIRECTION_RIGHT;
            }

        } else {
            if (subtractPoint.x == 1) {
                direction = DIRECTION_UP;
            } else {
                direction = DIRECTION_DOWN;
            }
        }

        return direction;
    }

    private Point subtract(Point sourcePoint, Point movePoint) {
        return new Point(sourcePoint.x - movePoint.x, sourcePoint.y - movePoint.y);
    }
}
