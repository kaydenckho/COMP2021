package hk.edu.polyu.comp.comp2021.jungle.model;

public class Rat extends AnimalNotJump {
    Rat(Player player) {
        name = "Rat";
        rank = 1;
        owner = player;
        owner.pieceCount ++;
    }

    // Rat can go onto a water square
    boolean canSwim(Board.Cell cell) {return true;}
}
