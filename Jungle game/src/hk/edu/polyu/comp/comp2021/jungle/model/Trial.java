package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Trial {
    public static void main (String[] args) {
        Player playerX = new Player ("Tom");
        Player playerY = new Player ("Jerry");
        Board board = new Board(playerX, playerY);
        Scanner scanner = new Scanner (System.in);
        String command = null;
        String[] commandArray = null;
        String path = "";
        String filename = "mysave.ser";
        boolean turn = false;
        if(path.length() != 0){
            path = path+"/";
        }
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
        boolean trun = true;

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
        if(!turn){
            System.out.println("Success turn is false now");
        }
        boolean valid = false;
        while (!board.victory) {
            while (!valid) {
                System.out.println(playerX.name + "'s turn");
                command = scanner.nextLine();
                commandArray = command.split(",");
                if (commandArray[0].equals("move")) {
                    valid = board.step(playerX, Position.valueOf(commandArray[1]), Position.valueOf(commandArray[2]));
                }
                else {
                    System.out.println(playerX.name + " other command");
                    break;
                }
            }
            if (!commandArray[0].equals("move")) {
                break;
            }
            valid = false;
            while (!valid) {
                System.out.println(playerY.name + "'s turn");
                command = scanner.nextLine();
                commandArray = command.split(",");
                if (commandArray[0].equals("move")) {
                    valid = board.step(playerY, Position.valueOf(commandArray[1]), Position.valueOf(commandArray[2]));
                }
                else {
                    System.out.println(playerY.name + " other command");
                    break;
                }
            }
            if (!commandArray[0].equals("move")) {
                break;
            }
        }
        scanner.close();
    }
}