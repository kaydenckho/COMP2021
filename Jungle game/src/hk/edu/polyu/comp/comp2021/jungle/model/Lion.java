package hk.edu.polyu.comp.comp2021.jungle.model;

public class Lion extends AnimalJump {
    Lion(Player player) {
        name = "Lion";
        rank = 7;
        owner = player;
        owner.pieceCount ++;
    }
}
