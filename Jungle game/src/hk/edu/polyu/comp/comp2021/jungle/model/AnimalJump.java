package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

abstract class AnimalJump extends Animal implements Serializable {

    // 4rd check
    // To be continued
    boolean isOneMove (Board.Cell[][] board, Position start, Position end) {

        // horizontally /. vertically
        if (end.y == start.y || end.x == start.x) {
            int cur_x;
            int cur_y;
            // move to right
            if (end.x > start.x) {
                 cur_x = start.x + 1;
                 cur_y = start.y;
                 // the cell in the right of the piece is river?
                 if (isRiver(board[cur_x][cur_y])) {
                    while (isRiver(board[cur_x][cur_y])) {
                        if (board[cur_x][cur_y].animal != null) {
                            return false;
                        }
                        cur_x ++;
                    }
                    return true;
                 }
                 else return notJump(start, end);
            }

            // move to left
            if (end.x < start.x) {
                cur_x = start.x -1;
                cur_y = start.y;
                // the cell in the left of the piece is river?
                if (isRiver(board[cur_x][cur_y])) {
                    while (isRiver(board[cur_x][cur_y])) {
                        if (board[cur_x][cur_y].animal != null) {
                            return false;
                        }
                        cur_x --;
                    }
                    return true;
                }
                else return notJump(start, end);
            }

            // move upward
            if (end.y > start.y) {
                cur_x = start.x;
                cur_y = start.y + 1;
                // the cell above the piece is river?
                if (isRiver(board[cur_x][cur_y])) {
                    while (isRiver(board[cur_x][cur_y])) {
                        if (board[cur_x][cur_y].animal != null) {
                            return false;
                        }
                        cur_y ++;
                    }
                    return true;
                }
                else return notJump(start, end);
            }

            // move downward
            if (end.y < start.y) {
                cur_x = start.x;
                cur_y = start.y - 1;
                // the cell below the piece is river?
                if (isRiver(board[cur_x][cur_y])) {
                    while (isRiver(board[cur_x][cur_y])) {
                        if (board[cur_x][cur_y].animal != null) {
                            return false;
                        }
                        cur_y --;
                    }
                    return true;
                }
                else return notJump(start, end);
            }
        }
        return false;
    }

    // Check whether the type of a cell is river
    boolean isRiver (Board.Cell cell) {
        return cell.cellType == Board.CellType.river;
    }

    // if the piece (tiger/lion) is not moving through a river, move normally as other animal
    boolean notJump (Position start, Position end) {

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
