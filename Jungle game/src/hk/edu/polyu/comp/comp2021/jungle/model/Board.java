package hk.edu.polyu.comp.comp2021.jungle.model;

class Board {
    class Cell{
        Animals animal = null;   // default cell has no piece
        Celltype celltype = Celltype.NORMAL;    // default cell is type normal
        int x_coordinate, y_coordinate;      // x,y coordinate of the cell
        public Cell(int x ,int y, Animals animal, Celltype celltype){   // used for initialize the board in board constructor
            x_coordinate = x;
            y_coordinate = y;
            this.animal = animal;
            this.celltype = celltype;
        }

        // See if the cell contains animal
        boolean hasAnimal(){ return this.animal != null; }
    }

    private Cell[][] array;

    public Board(String playerX, String playerY){  // playerX,playerY(names of first and second players)
        array = new Cell[9][7];
        // initialize the cell array using above constructor(To Be Done...)
    }



}
