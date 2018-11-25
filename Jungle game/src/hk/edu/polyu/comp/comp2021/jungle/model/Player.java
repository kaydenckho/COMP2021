package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

class Player implements Serializable {
    // Name of player
    String name;

    // Number of player's remaining piece in the board
    int pieceCount;

    // Side of the player (true: player X) (false: player Y)
    boolean side;

    Player (String name) {
        this.name = name;
    }
}
