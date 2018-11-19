package hk.edu.polyu.comp.comp2021.jungle.model;

class Board {
    private Animals[][] array;
    public Board(String playerX, String playerY){
        array = new Animals[9][7];                                  // board array = new Cell[8][6]??

        Animals Elephant1 = Animals.ELEPHANT;
        Animals Lion1 = Animals.LION;
        Animals Tiger1 = Animals.TIGER;
        Animals Leopard1 = Animals.LEOPARD;
        Animals Wolf1 = Animals.WOLF;
        Animals Dog1 = Animals.DOG;
        Animals Cat1 = Animals.CAT;
        Animals Rat1 = Animals.RAT;

        Elephant1.setBelongsToPlayer(playerX);
        Lion1.setBelongsToPlayer(playerX);
        Tiger1.setBelongsToPlayer(playerX);
        Leopard1.setBelongsToPlayer(playerX);
        Wolf1.setBelongsToPlayer(playerX);
        Dog1.setBelongsToPlayer(playerX);
        Cat1.setBelongsToPlayer(playerX);
        Rat1.setBelongsToPlayer(playerX);

        array[0][0] = Lion1;
        array[0][6] = Tiger1;
        array[1][1] = Dog1;
        array[1][5] = Cat1;
        array[2][0] = Rat1;
        array[2][2] = Leopard1;
        array[2][4] = Wolf1;
        array[2][6] = Elephant1;

        Animals Elephant2 = Animals.ELEPHANT;
        Animals Lion2 = Animals.LION;
        Animals Tiger2 = Animals.TIGER;
        Animals Leopard2 = Animals.LEOPARD;
        Animals Wolf2 = Animals.WOLF;
        Animals Dog2 = Animals.DOG;
        Animals Cat2 = Animals.CAT;
        Animals Rat2 = Animals.RAT;

        Elephant2.setBelongsToPlayer(playerY);
        Lion2.setBelongsToPlayer(playerY);
        Tiger2.setBelongsToPlayer(playerY);
        Leopard2.setBelongsToPlayer(playerY);
        Wolf2.setBelongsToPlayer(playerY);
        Dog2.setBelongsToPlayer(playerY);
        Cat2.setBelongsToPlayer(playerY);
        Rat2.setBelongsToPlayer(playerY);

        array[8][6] = Lion2;
        array[8][0] = Tiger2;
        array[7][5] = Dog2;
        array[7][1] = Cat2;
        array[6][6] = Rat2;
        array[6][4] = Leopard2;
        array[6][2] = Wolf2;
        array[6][0] = Elephant2;
    }
}
