package hk.edu.polyu.comp.comp2021.jungle.model;

public class Cat extends AnimalNotJump {
    Cat(Player player) {
        name = "Cat";
        rank = 2;
        owner = player;
        owner.pieceCount ++;
    }
}
