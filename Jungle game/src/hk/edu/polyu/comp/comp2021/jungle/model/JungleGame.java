package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class JungleGame {
    Player playerX;
    Player playerY;
    boolean turn;                       // true = x's trun / false = y's turn
    Board board;
    String path = "";                   // custom save directory that input in save/load command;
    String filename = "/mysave.ser";	// save file name
    boolean save = false;

    public JungleGame(){
        String command = null;
        String[] commandArray = null;
        System.out.println("To start a new game, please enter new.");
        System.out.println("To open a saved game, please enter load, \"your path (optional)\".");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter your command: ");
            command = scanner.nextLine();
            commandArray = command.split(",");
            if (commandArray[0].equals("new")) {
                StartNewGame();
                break;
            }
            if (commandArray[0].equals("load")) {
                if(commandArray.length > 1){
                    path = commandArray[1];
                };
                OpenSavedGame();
                break;
            }
            else {
                System.out.println("Invalid command, please try again");
                break;
            }
        }

        boolean valid = false;
        while (!board.victory) {
            while (!valid) {
                if(turn){
                    System.out.println(playerX.name + "'s turn");
                    command = scanner.nextLine();
                    commandArray = command.split(",");
                    if (commandArray[0].equals("move")) {
                        valid = board.step(playerX, Position.valueOf(commandArray[1]), Position.valueOf(commandArray[2]));
                    }
                    if (commandArray[0].equals("save")) {

                    }
                    else{
                        System.out.println(playerX.name + " other command");
                        break;
                    }
                }
                else{
                    System.out.println(playerY.name + "'s turn");
                    command = scanner.nextLine();
                    commandArray = command.split(",");
                    if (commandArray[0].equals("move")) {
                        valid = board.step(playerY, Position.valueOf(commandArray[1]), Position.valueOf(commandArray[2]));
                    }
                    if (commandArray[0].equals("save")) {
                        if(commandArray.length > 1){
                            path = commandArray[1];
                        };
                        //Saving objects, every objects to be saved need to use implement Serializable
                        try {
                            FileOutputStream fs = new FileOutputStream(path+filename);
                            ObjectOutputStream os = new ObjectOutputStream(fs);
                            os.writeObject(playerX);
                            os.writeObject(playerY);
                            os.writeObject(turn);
                            os.writeObject(board);
                            os.close();
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                        save = true;
                        break;
                    }
                    else {
                        System.out.println(playerY.name + " other command");
                        break;
                    }
                }
            }
            valid = false;
        }
        if(save){
            System.out.println("Game has saved in game directory as mysave.ser.");
        }
        else{
            if(turn){
                System.out.println(playerX.name+" win!!");
            }
            else{
                System.out.println(playerY.name+" win!!");
            }
        }
        scanner.close();
    }

    public void StartNewGame(){
        String playerX_str;
        String playerY_str;
        System.out.println("Please enter your names.");
        System.out.print("Player X: ");
        Scanner scanner = new Scanner(System.in);
        playerX_str = scanner.next();
        System.out.print("Player Y: ");
        playerY_str = scanner.next();
        playerX = new Player (playerX_str);
        playerY = new Player (playerY_str);
        board = new Board(playerX, playerY);

    }

    public void OpenSavedGame(){
        //loading save files from path in load command
        try {
            FileInputStream fis = new FileInputStream(path+filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            playerX = (Player)ois.readObject();
            playerY = (Player)ois.readObject();
            turn = (boolean)ois.readObject();
            board = (Board)ois.readObject();
            ois.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
