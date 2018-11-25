package hk.edu.polyu.comp.comp2021.jungle.model;

class Leopard extends AnimalNotJump {
    Leopard(Player player) {
        name = "Leopard";
        rank = 5;
        owner = player;
        owner.pieceCount ++;
    }
}
