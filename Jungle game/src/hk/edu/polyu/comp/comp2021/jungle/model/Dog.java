package hk.edu.polyu.comp.comp2021.jungle.model;

public class Dog extends AnimalNotJump {
    Dog(Player player) {
        name = "Dog";
        rank = 3;
        owner = player;
        owner.pieceCount ++;
    }
}
