package hk.edu.polyu.comp.comp2021.jungle.model;

class Elephant extends AnimalNotJump {
    Elephant(Player player) {
        name = "Elephant";
        rank = 8;
        owner = player;
        owner.pieceCount ++;
    }
}
