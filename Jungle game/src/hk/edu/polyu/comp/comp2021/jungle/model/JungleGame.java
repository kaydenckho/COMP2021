package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class JungleGame {
    Player playerX;
    Player playerY;
    boolean turn = true;                // true = x's trun / false = y's turn
    Board board;
    String path = "";                   // custom save directory that input in save/load command;
    String filename = "/JungleSave.ser";	// save file name
    boolean save = false;

    public void StartNewGame(String nameX, String nameY){
        playerX = new Player (nameX);
        playerY = new Player (nameY);
        board = new Board(playerX, playerY);

    }

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
    // Need to open this project using ADMINISTRATOR to have the access permission
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
