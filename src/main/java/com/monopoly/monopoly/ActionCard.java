package com.monopoly.monopoly;

import java.util.Random;

import static com.monopoly.monopoly.GameManager.listOfActionCards;

public class ActionCard {

    public enum ActionType{ MOVE, MONEY, BACK_TO_START, GET_OUT_OF_JAIL; }

    private String name;
    private String message;
    private int money;
    private int moveDirection;
    private ActionType type;


    //CONSTRUCTOR
    public ActionCard(String name, String message, int money, int moveDirection, ActionType type){
        this.name = name;
        this.message = message;
        this.money = money;
        this.moveDirection = moveDirection;
        this.type = type;
    }

    //GETTERS & SETTERS
    public String getName(){ return this.name; }
    public String getMessage(){ return this.message; }
    public int getMoney() { return this.money; }
    public int getMoveDirection() { return this.moveDirection; }
    public ActionType getActionType(){ return this.type; }

    //Class functions

}