package hk.edu.polyu.comp.comp2021.jungle.model;

enum Animals {
    ELEPHANT("Elephant",8), LION("Lion", 7), TIGER("Tiger", 6),
    LEOPARD("Leopard",5), WOLF("Wolf",4), DOG("Dog", 3),CAT("Cat", 2),RAT("Rat", 1);

    private String name;
    private int rank;                                                               // The power of the animal
    private static final int MAX_RANK = 8;                                          // Max rank of power level is 8 = elephant
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
        return this.name + " of " + BelongsToPlayer;                                // Return the name of the animal objects
    }

    public boolean higherRank(Animals other){
        if(other.rank == MAX_RANK){                                                 // See if other == elephant
            return (this.rank == 1) || (this.rank == MAX_RANK);                     // if (this == rat) or (this == elephant) then higherRank = true
        }
        else{
            return this.rank>=other.rank;                                           //if other != elephant, rank is calculated with normal method
        }
    }

}
