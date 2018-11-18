package hk.edu.polyu.comp.comp2021.jungle.model;

import java.util.*;

public class JungleGame {
    public JungleGame(){
        int input;
        System.out.println("To start a new game, please enter 1.");
        System.out.println("To open a saved game, please enter 2.");
        System.out.println("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        while (input<1 || input>2){
            System.out.println("Your input is invalid, please enter again!!");
            System.out.println("Your choice: ");
            input = scanner.nextInt();
        }
        if (input==1){
            StartNewGame();
        }
        if (input==2){
            OpenSavedGame();
        }
    }

    public void StartNewGame(){
        String playerX;
        String playerY;
        System.out.println("Please enter your names.");
        System.out.print("Player X: ");
        Scanner scanner = new Scanner(System.in);
        playerX = scanner.next();
        System.out.print("Player Y: ");
        playerY = scanner.next();

        Board board = new Board(playerX, playerY);
    }

    public void OpenSavedGame(){

    }

}
