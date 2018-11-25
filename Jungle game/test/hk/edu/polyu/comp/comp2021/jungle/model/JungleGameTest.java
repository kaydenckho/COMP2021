package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import org.junit.Test;


public class JungleGameTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    // Please use Administrator access to access save path correctly!!

    @Test
    public void test_JungleGame_class(){
        JungleGame game = new JungleGame();
        game.StartNewGame("Jimmy", "Tang");
        //Opens a new game
        assert game.playerX.name == "Jimmy";
        assert game.playerY.name == "Tang";
        //Saving a game
        game.SaveGame();
        //Mess around (change name, move pieces)
        game.playerY.name = "Tan";
        assert game.board.step(game.playerX, Position.A3, Position.A4);
        assert !game.board.step(game.playerX, Position.A4, Position.A6);
        assert !game.board.step(game.playerX, Position.A4, Position.A4);
        assert !game.board.step(game.playerX, Position.A4, Position.B5);
        //Load game, check if it discard the changes above
        // Please use Administrator access to access save path correctly!!
        game.OpenSavedGame();
        assert game.playerY.name.equals("Tang");
        assert game.board.board[Position.A4.x][Position.A4.y].animal == null;
        assert game.board.board[Position.A3.x][Position.A3.y].animal.rank == 8;
        // Invalid path (No such directory) (directory available in command mode only)
        game.path = "/sdsadac/sdasd/zxca";
        assert !game.SaveGame();
        assert !game.OpenSavedGame();
    }

    @Test
    public void test_Board_class(){
        JungleGame game2 = new JungleGame();
        game2.StartNewGame("Jimmy", "Tang");
        //ignoring Y's turn, testing moves with X
        game2.board.step(game2.playerX, Position.G3, Position.G4);
        game2.board.step(game2.playerX, Position.G4, Position.F4);
        game2.board.step(game2.playerX, Position.F4, Position.E4);
        assert game2.board.board[Position.E4.x][Position.E4.y].animal.rank == 1;
        //Testing mouse moves (Board)

        //Eating own animal
        assert game2.board.step(game2.playerX, Position.F2, Position.F3);
        assert !game2.board.step(game2.playerX, Position.F3, Position.E3);
        assert game2.board.step(game2.playerX, Position.F3, Position.F2);

        //Lion jump but blocked by mouse
        game2.board.step(game2.playerX, Position.G1, Position.G2);
        game2.board.step(game2.playerX, Position.G2, Position.G3);
        game2.board.step(game2.playerX, Position.G3, Position.G4);
        assert !game2.board.step(game2.playerX, Position.G4, Position.D4);
        //Mouse get away!!
        game2.board.step(game2.playerX, Position.E4, Position.E5);
        // Lion across 2 river
        assert game2.board.step(game2.playerX, Position.G4, Position.D4);
        // Lion cross 3 river
        game2.board.step(game2.playerX, Position.D4, Position.G4);
        game2.board.step(game2.playerX, Position.G4, Position.G3);
        game2.board.step(game2.playerX, Position.G3, Position.F3);
        assert game2.board.step(game2.playerX, Position.F3, Position.F7);
        assert game2.board.step(game2.playerX, Position.F7, Position.F3);
        game2.board.step(game2.playerX, Position.F3, Position.F7);
        //Lion eat elephant
        assert !game2.board.step(game2.playerX, Position.F7, Position.G7);
        //Lion eats wolf
        assert game2.board.step(game2.playerX, Position.F7, Position.E7);
        //Player Y - pieces
        assert game2.playerY.pieceCount == 7;

        // Entering Own Den
        game2.board.step(game2.playerX, Position.A1, Position.B1);
        game2.board.step(game2.playerX, Position.B1, Position.C1);
        assert !game2.board.step(game2.playerX, Position.C1, Position.D1);

        //moving animals away for following test
        assert game2.board.step(game2.playerX, Position.A3, Position.A4);
        assert game2.board.step(game2.playerX, Position.C3, Position.D3);

        // Cat moving in diagonal
        assert !game2.board.step(game2.playerX, Position.B2, Position.A1);
        assert !game2.board.step(game2.playerX, Position.B2, Position.C1);
        assert !game2.board.step(game2.playerX, Position.B2, Position.A3);
        assert !game2.board.step(game2.playerX, Position.B2, Position.C3);

        //reset board
        game2 = new JungleGame();
        game2.StartNewGame("Jimmy", "Tang");

        //Teleporter cat -> teleported to near enemies' trap
        game2.board.board[Position.E8.x][Position.E8.y].animal= game2.board.board[Position.B2.x][Position.B2.y].animal;
        game2.board.board[Position.B2.x][Position.B2.y].animal = null;
        assert game2.board.board[Position.E8.x][Position.E8.y].animal.name.equals("Cat");
        assert game2.board.board[Position.E8.x][Position.E8.y].animal.owner == game2.playerX;

        game2.board.board[Position.D8.x][Position.D8.y].animal= game2.board.board[Position.A7.x][Position.A7.y].animal;
        game2.board.board[Position.A7.x][Position.A7.y].animal = null;
        assert game2.board.board[Position.D8.x][Position.D8.y].animal.name.equals("Rat");
        assert game2.board.board[Position.D8.x][Position.D8.y].animal.owner == game2.playerY;

        game2.SaveGame();
        //trap mouse -> cat
        assert !game2.board.step(game2.playerY, Position.D8, Position.E8);
        game2.OpenSavedGame();
        // cat -> trap mouse
        assert game2.board.step(game2.playerX, Position.E8, Position.D8);

        // teleporting X's lion to enemies trap
        // and use animal of Y to eat it
        game2.board.board[Position.C9.x][Position.C9.y].animal= game2.board.board[Position.G1.x][Position.G1.y].animal;
        game2.board.board[Position.G1.x][Position.G1.y].animal = null;
        assert game2.board.board[Position.C9.x][Position.C9.y].animal.name.equals("Lion");
        assert game2.board.board[Position.C9.x][Position.C9.y].animal.owner == game2.playerX;
        assert game2.board.step(game2.playerY, Position.B8, Position.C8);
        assert game2.board.step(game2.playerY, Position.C8, Position.C9);

        //reset board
        game2 = new JungleGame();
        game2.StartNewGame("Jimmy", "Tang");

        //Teleproting 1 mouse to river
        //and 1 mouse on land near it
        game2.board.board[Position.B5.x][Position.B5.y].animal= game2.board.board[Position.G3.x][Position.G3.y].animal;
        game2.board.board[Position.G3.x][Position.G3.y].animal = null;
        assert game2.board.board[Position.B5.x][Position.B5.y].animal.name.equals("Rat");
        assert game2.board.board[Position.B5.x][Position.B5.y].animal.owner == game2.playerX;

        game2.board.board[Position.A5.x][Position.A5.y].animal= game2.board.board[Position.A7.x][Position.A7.y].animal;
        game2.board.board[Position.A7.x][Position.A7.y].animal = null;
        assert game2.board.board[Position.A5.x][Position.A5.y].animal.name.equals("Rat");
        assert game2.board.board[Position.A5.x][Position.A5.y].animal.owner == game2.playerY;
        // Can't eat each other
        assert !game2.board.step(game2.playerX, Position.B5, Position.A5);
        assert !game2.board.step(game2.playerY, Position.A5, Position.B5);

        //start pos has pieces? ...
        assert !game2.board.step(game2.playerX, Position.D3, Position.D4);
        // This animal can't swim
        assert !game2.board.step(game2.playerX, Position.E3, Position.E4);
        //is start pos animal yours? No!
        assert !game2.board.step(game2.playerX, Position.G7, Position.G6);

    }

    @Test
    public void test_animal_construct(){
        Player dude = new Player("Legendary Eagle");
        Player dude2 = new Player("The Global Elite of Jungle Game");
        Animal cat = new Cat(dude);
        assert cat.owner == dude;
        assert dude.pieceCount == 1;

        Animal dog = new Dog(dude2);
        assert dog.owner == dude2;
        assert dude2.pieceCount == 1;

        Animal elephant = new Elephant(dude);
        assert elephant.owner == dude;
        assert dude.pieceCount == 2;

        Animal leopard = new Leopard(dude);
        Animal lion = new Lion(dude);
        Animal rat = new Rat(dude);
        Animal tiger = new Tiger(dude2);
        Animal wolf = new Wolf(dude2);
        assert dude.pieceCount == 5;
        assert dude2.pieceCount == 3;
    }

    @Test
    public void TestAll(){
        test_JungleGame_class();
        test_Board_class();
        test_animal_construct();
        /// Excluding GUI, coverage > 97.00%
    }
}