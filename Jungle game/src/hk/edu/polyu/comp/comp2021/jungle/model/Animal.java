package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public abstract class Animal implements Serializable {
    String name;
    int rank;
    Player owner;

    // 4rd check
    abstract boolean isOneMove (Board.Cell[][] board, Position start, Position end);

    // 5th check: player's piece can go onto a water square?
    // Overridden in 'Rat' class
    boolean canSwim(Board.Cell end) {

        // cannot go onto a water square if the piece is not rat
        if (end.cellType == Board.CellType.river) return false;
        return true;
    }

    // 6th check: player's piece move to his/her own den?
    public boolean isOwnDen (Board.Cell endCell, Animal animal) {

        // player's piece to his/her own den
        if (endCell.cellType == Board.CellType.den && endCell.side == animal.owner.side) {
            return true;
        }
        return false;
    }

    // 9th check: player's piece's rank >= opponent's piece's rank or rat capture elephant??
    boolean canCapture (Board.Cell endCell) {
        // Opponent's piece in player's trap can be captured by any player's piece regardless of order
        if (endCell.cellType == Board.CellType.trap && endCell.side == this.owner.side) return true;
        Animal other = endCell.animal;

        // Elephant cannot capture Rat
        if (this.name.equals("Elephant") && other.name.equals("Rat")) return false;

        // Rat can capture Elephant;
        if (this.name.equals("Rat") && other.name.equals("Elephant")) return true;

        if (this.rank >= other.rank) return true;
        return false;
    }
}
