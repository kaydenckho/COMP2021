package hk.edu.polyu.comp.comp2021.jungle.model;

import javafx.scene.control.Cell;

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
        BelongsToPlayer = playerName;                                               // Initialize the master of the animal object
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

    public boolean valid_move(int dir, int x, int y){                                           //dir determines up, down, left, right: cell row and column x,y
        //if(!boardClassname.arrayName[x][y].hasAnimal()){                                      // Check if the cell move from has an animal (Should be done in board class)
        //    return false;                                                                     // valid_move should be called in board class after interpreting the move command
        //}
        switch(dir){                                                                            // Check if the moving direction is out of bound
            case 1:{
                if(y+1 > 8){return false;}
                else{
                    y=y+1;
                    Cell nextcell = boardClassname.arrayName[x][y]
                }
            }
            case 2:{
                if(y-1 < 0){return false;}
                else{
                    y=y-1
                    Cell nextcell = boardClassname.arrayName[x][y]
                }
            }
            case 3:{
                if(x-1 < 0){return false;}
                else{
                    x=x-1;
                    Cell nextcell = boardClassname.arrayName[x][y]
                }
            }
            case 4:{
                if(x+1 > 6){return false;}
                else{
                    x = x+1;
                    Cell nextcell = boardClassname.arrayName[x][y]
                }
            }
        }
        if(nextcell.isWater() == true){
            if(this.rank==7 || this.rank ==6 || this.rank==1){
                if(this.rank==1){                                   // mouse goes into water
                    if(nextcell.hasAnimal()){                       // check if there's already animal in water
                        return false;                               // move is blocked by another mouse
                    }
                    else{
                        return true;                                // valid if no animal in water
                    }
                }
                else{                                               // Lion and tiger jump across river
                    if(nextcell.hasAnimal()){                       // check if there's already animal in water
                        return false;                               // move is blocked by the animal
                    }
                    else{
                        return valid_move(dir, x, y);                   // if the next cell is still water
                    }
                }
            }
            else{
                return false;                                       // For other animals, no one can go into the river
            }
        }
        else{
            nextcell not river
        }
    }

}
