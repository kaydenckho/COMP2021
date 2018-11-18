package hk.edu.polyu.comp.comp2021.jungle.model;

public class Animals {
    private String name;
    private int rank;
    public Animals(String name) {
        this.name = name;
        switch (name){
            case "Elephant" : rank = 8;
            case "Lion" : rank = 7;
            case "Tiger" : rank = 6;
            case "Leopard" : rank = 5;
            case "Wolf" : rank = 4;
            case "Dog" : rank = 3;
            case "Cat" : rank = 2;
            case "Rat" : rank = 1;
        }
    }

    public boolean canCapture(Animals other){
        return this.rank>=other.rank;
    }

}
