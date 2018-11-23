package hk.edu.polyu.comp.comp2021.jungle.model;

public class Tiger extends AnimalJump {
    Tiger(Player player) {
        name = "Tiger";
        rank = 6;
        owner = player;
        owner.pieceCount ++;
    }
}
