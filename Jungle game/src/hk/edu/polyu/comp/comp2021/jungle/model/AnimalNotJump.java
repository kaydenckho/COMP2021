package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public abstract class AnimalNotJump extends Animal implements Serializable {

    // 4rd check: player's piece move only one square horizontally or vertically?
    boolean isOneMove (Board.Cell[][] board, Position start, Position end) {

        // move horizontally by one square
        if (end.x - start.x == 1 || end.x - start.x == -1 && end.y == start.y) {
            return true;
        }

        // move vertically by one square
        if (end.y - start.y == 1 || end.y - start.y == -1 && end.x == start.x) {
            return true;
        }

        return false;
    }


}
