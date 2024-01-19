package com.monopoly.monopoly;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class CLI_Game {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    private static void print(String s, String color){
        System.out.print(color + s + RESET);
    }

    private static void printGame(GameManager gm){
        System.out.println("----------------------------");
        for(Player p:gm.getListOfPlayers()) {
            print(p.getName() + " $" + p.getBalance() + " ", p.getColor());
        }
        System.out.println("\r\n----------------------------");
        for(Field f: gm.getListOfFields()) System.out.println(f);
    }
   /* public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        GameManager gm = new GameManager();
        LinkedList<Player> listOfPlayers = new LinkedList<>();
        //Player p1 = new Player(1, "Barbara", GREEN, gm);
        //listOfPlayers.add(p1);
        //Player p2 = new Player(2, "David", CYAN, gm);
        //listOfPlayers.add(p2);
        //gm.setListOfPlayers(listOfPlayers);

        printGame(gm);
        in.nextLine(); // Print board and wait for start
        int round = 0;
        while(true){
            round += 1;
            if(listOfPlayers.size() < 2){
                print(listOfPlayers.get(0).getName() + " has won!", GREEN);
                return;
            }
            for(Player p:listOfPlayers){
                print("\r\n\r\nRound #"+round + " ", PURPLE);
                print(p.getName() + "'s turn: \r\n", p.getColor());
                //int[] dice = gm.throwDice();
                System.out.println("DEBUG:set dice:");
                int[] dice = {0, in.nextInt()};
                System.out.println("DICE: " + dice[0] + " + " + dice[1] + " = " + (dice[0]+dice[1]));
                gm.movePlayer(p, dice);
                Field field = p.getCurrentPosition();
                if(field.getType() == Field.Type.GO_TO_PRISON){
                    p.setCurrentPosition(gm.getListOfFields().get(10));
                    print(p.getName(), p.getColor());
                    print(" went to prison!", RED);
                    continue;
                }
                int rent = field.getRent();
                if(field.getOwner() != null && field.getOwner() != p){
                    print(p.getName(), p.getColor());
                    print(" pays $" + rent + " in rent to ", RED);
                    print(field.getOwner().getName(), field.getOwner().getColor());
                    print("!\r\n", RED);
                    p.withdraw(rent);
                    field.getOwner().deposit(rent);
                }
                printGame(gm);
                if(p.getBalance() < 0) {
                    print(p.getName() + " has lost!", RED);
                    listOfPlayers.remove(p);
                    break;
                }
                else{
                    print("\r\n>", PURPLE);
                    int choice = in.nextInt();
                    if(choice == 1 && !p.getCurrentPosition().action(p)) print("action unavailable\r\n", RED);
                }
            }
        }
    }*/
}