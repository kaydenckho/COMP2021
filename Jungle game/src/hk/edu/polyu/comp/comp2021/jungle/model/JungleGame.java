package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** Main Class of Jungle Game software
 *  The starting point of the backend program
 *  Methods available:
 *  void StartNewGame(String nameX, String nameY),
 *  boolean OpenSavedGame()
 *  boolean SaveGame()
 */
public class JungleGame {
    /** An instance of player X */
    protected Player playerX;
    /** An instance of player Y */
    protected Player playerY;
    /** true = x's trun / false = y's turn */
    protected boolean turn = true;
    /** An instance of game board */
    protected Board board;
    /** custom save directory that input in save/load command; */
    protected String path = "";
    /** save file name *///
    protected String filename = "/JungleSave.ser";
    /** variable to indicate the success of SaveGame() */
    protected boolean save = false;

    /**
     * Method to start a new game
     * @param nameX Name of player X
     * @param nameY Name of player Y
     */
    public void StartNewGame(String nameX, String nameY){
        playerX = new Player (nameX);
        playerY = new Player (nameY);
        board = new Board(playerX, playerY);

    }

    /**
     * Method to open a saved game
     *
     **/
     public boolean OpenSavedGame(){
        //loading save files from path in load command
        try {
            FileInputStream fis = new FileInputStream(path+filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            playerX = (Player)ois.readObject();
            playerY = (Player)ois.readObject();
            turn = (boolean)ois.readObject();
            board = (Board)ois.readObject();
            ois.close();
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
      /**
       * Method to save the current game
      * Need to open this project using ADMINISTRATOR to have the access permission in
      * order to use SaveGame(), otherwise Access Denied Exception may occur.
      */
    public boolean SaveGame(){
        //Saving objects, every objects to be saved need to use implement Serializable
        try {
            FileOutputStream fs = new FileOutputStream(path+filename);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(playerX);
            os.writeObject(playerY);
            os.writeObject(turn);
            os.writeObject(board);
            os.close();
            return true;
        }
        catch(Exception ex){
            System.out.println("Opps, unable to save.");
            ex.printStackTrace();
            return false;
        }
    }

}
