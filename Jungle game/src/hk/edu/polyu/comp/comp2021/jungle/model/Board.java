package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.Animal;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import java.io.Serializable;

public class Board implements Serializable {

    // A cell in a board
    class Cell implements Serializable{

        // Type of a cell
        public CellType cellType;

        // Piece in it (null: no piece in it)
        public Animal animal;
        public Cell (CellType cellType) {
            this.cellType = cellType;
        }
    }

    // Cell Type
    enum CellType {
        normal, river, trap, den;
        // only used for trap and den;
        boolean side;
    }

    // The board
    public Cell[][] board;
    // true when one player wins
    boolean victory;

    public Board (hk.edu.polyu.comp.comp2021.jungle.model.Player playerX, hk.edu.polyu.comp.comp2021.jungle.model.Player playerY) {

        // playerX is in side X of the board
        playerX.side = true;
        // playerY is in side Y of the board
        playerY.side = false;

        // Initialize the board
        board = new Cell [7][9];
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 9; j ++) {
                board [i][j] = new Cell (CellType.normal);
            }
        }

        // river
        // B4
        board [Position.B4.x][Position.B4.y] = new Cell(CellType.river);
        // B5
        board [Position.B5.x][Position.B5.y] = new Cell(CellType.river);
        // B6
        board [Position.B6.x][Position.B6.y] = new Cell(CellType.river);
        // C4
        board [Position.C4.x][Position.C4.y] = new Cell(CellType.river);
        // C5
        board [Position.C5.x][Position.C5.y] = new Cell(CellType.river);
        // C6
        board [Position.C6.x][Position.C6.y] = new Cell(CellType.river);
        // E4
        board [Position.E4.x][Position.E4.y] = new Cell(CellType.river);
        // E5
        board [Position.E5.x][Position.E5.y] = new Cell(CellType.river);
        // E6
        board [Position.E6.x][Position.E6.y] = new Cell(CellType.river);
        // F4
        board [Position.F4.x][Position.F4.y] = new Cell(CellType.river);
        // F5
        board [Position.F5.x][Position.F5.y] = new Cell(CellType.river);
        // F6
        board [Position.F6.x][Position.F6.y] = new Cell(CellType.river);

        // player X's trap
        // C1
        board [Position.C1.x][Position.C1.y] = new Cell(CellType.trap);
        board [Position.C1.x][Position.C1.y].cellType.side = true;
        // D2
        board [Position.D2.x][Position.D2.y] = new Cell(CellType.trap);
        board [Position.C1.x][Position.C1.y].cellType.side = true;
        // E1
        board [Position.E1.x][Position.E1.y] = new Cell(CellType.trap);
        board [Position.E1.x][Position.E1.y].cellType.side = true;

        // player Y's trap
        // C9
        board [Position.C9.x][Position.C9.y] = new Cell(CellType.trap);
        // D8
        board [Position.D8.x][Position.D8.y] = new Cell(CellType.trap);
        // E9
        board [Position.E9.x][Position.E9.y] = new Cell(CellType.trap);

        // den
        // D1
        board [Position.D1.x][Position.D1.y] = new Cell(CellType.den);
        // D9
        board [Position.D9.x][Position.D9.y] = new Cell(CellType.den);

        // place pieces

        // Player X's pieces
        // A1 : Player X's Tiger
        board [Position.A1.x][Position.A1.y].animal = new Tiger(playerX);
        // A3 : Player X's Elephant
        board [Position.A3.x][Position.A3.y].animal = new Elephant(playerX);
        // B2 : Player X's Cat
        board [Position.B2.x][Position.B2.y].animal = new Cat(playerX);
        // C3 : Player X's Wolf
        board [Position.C3.x][Position.C3.y].animal = new Wolf(playerX);
        // E3 : Player X's Leopard
        board [Position.E3.x][Position.E3.y].animal = new Leopard(playerX);
        // F2 : Player X's Dog
        board [Position.F2.x][Position.F2.y].animal = new Dog(playerX);
        // G1 : Player X's Lion
        board [Position.G1.x][Position.G1.y].animal = new Lion(playerX);
        // G3 : Player X's Rat
        board [Position.G3.x][Position.G3.y].animal = new Rat(playerX);

        // Player Y's pieces
        // G9 : Player Y's Tiger
        board [Position.G9.x][Position.G9.y].animal = new Tiger(playerY);
        // G7 : Player Y's Elephant
        board [Position.G7.x][Position.G7.y].animal = new Elephant(playerY);
        // F8 : Player Y's Cat
        board [Position.F8.x][Position.F8.y].animal = new Cat(playerY);
        // E7 : Player Y's Wolf
        board [Position.E7.x][Position.E7.y].animal = new Wolf(playerY);
        // C7 : Player Y's Leopard
        board [Position.C7.x][Position.C7.y].animal = new Leopard(playerY);
        // B8 : Player Y's Dog
        board [Position.B8.x][Position.B8.y].animal = new Dog(playerY);
        // A9 : Player Y's Lion
        board [Position.A9.x][Position.A9.y].animal = new Lion(playerY);
        // A7 : Player Y's Rat
        board [Position.A7.x][Position.A7.y].animal = new Rat(playerY);
    }

    public boolean step (Player player, Position start, Position end) {
        if (validIndex(start, end)) {

            if (hasAnimal(start)) {

                if (isOwnAnimal(player, start)) {

                    // Player's piece
                    Animal currentAnimal = board[start.x][start.y].animal;

                    if (currentAnimal.isOneMove(board, start, end)) {

                        if (currentAnimal.canSwim(board[end.x][end.y])) {

                            if (!currentAnimal.isOwnDen(board[end.x][end.y], currentAnimal)) {

                                if (hasAnimal(end)) {

                                    if (!isOwnAnimal(player, end)) {

                                        if (currentAnimal.canCapture(board[end.x][end.y])) {

                                            if (ratCheck(start, end)) {
                                                System.out.println("valid move");
                                                victory = capture(start, end, currentAnimal);
                                                return true;
                                            }
                                            else System.out.println("ratCheck invalid"); return false;
                                        }
                                        else System.out.println("canCapture invalid"); return false;
                                    }
                                    else System.out.println("isOwnAnimal (player, end) invalid"); return false;
                                }
                                else {
                                    System.out.println("valid move");
                                    victory = move(start, end, currentAnimal);
                                    return true;
                                }
                            }
                            else System.out.println("isOwnDen invalid"); return false;
                        }
                        else System.out.println("canSwim invalid"); return false;
                    }
                    else System.out.println("isOneMove invalid"); return false;
                }
                else System.out.println("isOwnAnimal (player, start) invalid"); return false;
            }
            else System.out.println("hasAnimal (start) invalid"); return false;
        }
        else System.out.println("validIndex invalid"); return false;
    }

    // 1st check
    boolean validIndex (Position start, Position end) {
        // ending position is not in the board?
        if (end.x < 0 || end.x > 6 || end.y < 0 || end.y > 8){
            return false;
        }

        // the starting position is same as the ending position
        if (end.x == start.x && end.y == start.y) {
            return false;
        }

        return true;
    }

    // 2nd check : there is a piece in the starting position?
    // 7th check : there is a piece in the ending position?
    public boolean hasAnimal (Position p) {return board[p.x][p.y].animal != null;}

    // 3rd check : the piece in the starting position is player's piece?
    // 8th check : the piece in the ending position is player's piece?
    public boolean isOwnAnimal (Player player, Position p) {return board[p.x][p.y].animal.owner == player;}


    // 10th check:
    // Player's piece is a rat?
    // If yes, it capture a piece from normal cell to river?? (or from river to normal cell??)
    public boolean ratCheck (Position start, Position end) {
        if (board[start.x][start.y].animal.name.equals("Rat")) {
            if (board[start.x][start.y].cellType == CellType.normal && board[end.x][end.y].cellType == CellType.river) {
                return false;
            }
            if (board[start.x][start.y].cellType == CellType.river && board[end.x][end.y].cellType == CellType.normal) {
                return false;
            }
        }
        return true;
    }

    // Capture opponent's piece
    public boolean capture (Position start, Position end, Animal animal) {

        // Opponent's number of piece - 1
        board[end.x][end.y].animal.owner.pieceCount --;
        Player opponent = board[end.x][end.y].animal.owner;

        // Capture opponent's piece
        board[end.x][end.y].animal = animal;
        // Remove player's piece in the starting position
        board[start.x][start.y].animal = null;

        // Check whether player win? (1st win condition)
        return isZeroPieceCount(animal.owner, opponent);
    }

    // 1st win condition: Opponent have zero piece
    public boolean isZeroPieceCount (Player player, Player opponent) {
        // if Player X's number of piece is 0 or Player Y's number of piece is 0
        if(opponent.pieceCount == 0) {
            System.out.println(player.name + " win (zero).");
            return true;
        }
        return false;
    }

    // Player's piece move to a empty cell
    public boolean  move (Position start, Position end, Animal animal) {
        board[end.x][end.y].animal = animal;
        // Remove player's piece in the starting position
        board[start.x][start.y].animal = null;

        // Check whether player win? (2nd win condition)
        return isDen(end, animal.owner);
    }

    // 2nd win condition: Player's piece move to opponent's den
    public boolean isDen (Position end, Player player) {

        // player X/Y wins
        if (board[end.x][end.y].cellType == CellType.den && board[end.x][end.y].cellType.side != player.side) {
            System.out.println(player.name + " win (den)");
            return true;
        }
        return false;
    }


}
