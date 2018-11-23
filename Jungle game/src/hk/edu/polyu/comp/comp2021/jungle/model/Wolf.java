package hk.edu.polyu.comp.comp2021.jungle.model;

public class Wolf extends AnimalNotJump {
    Wolf(Player player) {
        name = "Wolf";
        rank = 4;
        owner = player;
        owner.pieceCount ++;
    }
}
