package com.monopoly.monopoly;

import java.util.ArrayList;
import java.util.Arrays;

public class Field {
    private String id;
    public String name;
    private int price;
    private int rent;
    private int[] prices;
    private int properties;
    private Player owner;
    public enum FieldType{
        START, DISTRICT, EVENT, NOTHING, KLIMA_BONUS, STATION, PRISON, GO_TO_PRISON, FREE_PARKING;
    }
    private FieldType type;
    private String description;

    private GameManager gameManager;

    public Field(String id, String name, FieldType type, String description, GameManager gameManager){
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.gameManager = gameManager;
    }

    public Field(String id, String name, int price, int rent, int[] prices, FieldType type, String description, GameManager gameManager) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.prices = prices;
        this.type = type;
        this.properties = 0;
        this.owner = null;
        this.description = description;
        this.gameManager = gameManager;
    }

    //Setters and getters
    public void setId(String id){this.id = id;}
    public String getId(){return this.id;}
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    public void setPrice(int price){this.price = price;}
    public int getPrice(){return this.price;}
    public void setRent(int rent){this.rent = rent;}
    public int getRent(){return this.rent * (properties + 1);}
    public void setProperties(int number){this.properties = this.properties + number;}
    public int getProperties(){return this.properties;}
    public void setOwner(Player player){this.owner = player;}
    public Player getOwner(){return this.owner;}
    public void setType(FieldType type){this.type = type;}
    public FieldType getType(){return this.type;}
    public void setDescription(String description){this.description = description;}
    public String getDescription(){return this.description;}

    //Class functions








    public boolean action(Player p){
        if(type != FieldType.STATION && type != FieldType.DISTRICT) return false;
        if(owner == null && p.withdraw(price)) {
            owner = p;
            return true;
        }
        if(owner == p && type == FieldType.DISTRICT &&
                properties < 6 && p.withdraw(price)) {
            properties += 1;
            return true;
        }
        return false;
    }

    /*private ArrayList<Player> getPlayersOnField(){
        ArrayList<Player> list = new ArrayList<>();
        for(Player p:gameManager.getListOfPlayers()){
            if(p.getCurrentPosition() == this) list.add(p);
        }
        return list;
    }*/
   /* @Override
    public String toString() {
        StringBuilder players = new StringBuilder();
        for(Player p:getPlayersOnField()) players.append(p.getColor()).append(p.getName()).append(" \u001B[0m");

        if(owner == null) return "[" + String.join(" | ", name, players, prices.toString()) + "]";
        return "[" + String.join(" | ", owner.getColor() + name + "\u001B[0m", Integer.toString(properties), players, prices.toString()) + "]";
    }*/
}