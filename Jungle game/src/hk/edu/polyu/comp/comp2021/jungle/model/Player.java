package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public class Player implements Serializable {
    // Name of player
    public String name;

    // Number of player's remaining piece in the board
    public int pieceCount;

    // Side of the player (true: player X) (false: player Y)
    public boolean side;

    public Player (String name) {
        this.name = name;
    }
}
