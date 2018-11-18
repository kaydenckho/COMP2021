package hk.edu.polyu.comp.comp2021.jungle.model;

enum Animals {
    ELEPHANT("Elephant",8), LION("Lion", 7), TIGER("Tiger", 6),
    LEOPARD("Leopard",5), WOLF("Wolf",4), DOG("Dog", 3),CAT("Cat", 2),RAT("Rat", 1);

    private String name;
    private int rank;
    private String BelongsToPlayer;

    Animals(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    void setBelongsToPlayer(String playerName){
        BelongsToPlayer = playerName;
    }

    @Override
    public String toString() {
        return this.name + " of " + BelongsToPlayer;
    }

    public boolean higherRank(Animals other){
        return this.rank>=other.rank;
    }

}
