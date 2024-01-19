package com.monopoly.monopoly;


import java.util.LinkedList;

import static com.monopoly.monopoly.GameManager.listOfFields;

public class Player {
    private String id;
    private String name;
    private int balance;
    private int totalPayments; //For statistics (how much has the player paid out: e.g., Miete, Stafe, Steuern, usw.)
    private int totalIncome; //For statistics (how much has the player received: e.g., Gehalt, Klimabonus, erhaltende Miete, usw.)
    private int previousPositionIndex;
    private int currentPositionIndex; //Where is the player standing
    private boolean hasOutOfJailCard; //Does the player possess an out of jail card
    private String color;
    private int roundNumber;
    private LinkedList <ActionCard> events;
    private LinkedList <Field> own;

    public Player(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.balance = 200;
        this.totalPayments = 0;
        this.totalIncome = 200;
        this.previousPositionIndex = 0;
        this.currentPositionIndex = 0;
        this.hasOutOfJailCard = false;
        this.color = color;
        this.roundNumber = 1;
        this.events = new LinkedList<>();
        this.own = new LinkedList<>();
    }

    //Setters and getters
    public void setId(String id){this.id = id; }
    public String getId(){return this.id; }
    public void setName(String name){this.name = name; }
    public String getName(){ return this.name; }
    public void setBalance(int balance){this.balance = balance; }
    public int getBalance(){ return this.balance; }
    public void setTotalPayments(int totalPayments){this.totalPayments = totalPayments; }
    public int getTotalPayments(){return this.totalPayments; }
    public void setTotalIncome(int totalIncome){this.totalIncome = totalIncome; }
    public int getTotalIncome(){return this.totalIncome; }
    public void setPreviousPositionIndex(int previousPositionIndex) {
        this.previousPositionIndex = previousPositionIndex;
    }
    public int getPreviousPositionIndex() { return this.previousPositionIndex; }
    public void setCurrentPositionIndex(int currentPositionIndex){this.currentPositionIndex = currentPositionIndex; }
    public int getCurrentPositionIndex(){return this.currentPositionIndex; }
    public void setHasOutOfJailCard(Boolean tF){this.hasOutOfJailCard = tF; }
    public Boolean getHasOutOfJailCard(){return this.hasOutOfJailCard; }
    public void setColor(String color){this.color = color; }
    public String getColor() {
        return color;
    }
    public void setRoundNumber(int roundNumber){this.roundNumber = roundNumber; }
    public int getRoundNumber() {return this.roundNumber; }
    public void setEvents(LinkedList<ActionCard> events){this.events = events; }
    public LinkedList <ActionCard> getEvents(){ return this.events; }
    public void setOwn(LinkedList<Field> events){this.own = own; }
    public LinkedList <Field> getOwn(){ return this.own; }

    //Class functions

    public void deposit(int amount){
        this.balance += amount;
        this.totalIncome += amount;
    }

    public void withdraw(int amount){
        balance -= amount;
        totalPayments += amount;
    }


}