package com.monopoly.monopoly;

import java.io.InputStream;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

//TODO: We could probably just have one Random random = new Random() for the whole class
public class GameManager {
    static LinkedList<Field> listOfFields = new LinkedList<>();
    static LinkedList<Player> listOfPlayers = new LinkedList<>();
    static LinkedList <ActionCard> listOfActionCards = new LinkedList<>();
    private Player currentPlayer;
    int [] twoDice = new int [2];

    public GameManager() {
        createFieldListFromJson("fields.json");
        createActionCardListFromJson("action_cards.json");
        chooseStartPlayer(listOfPlayers);
    }

    //Setters and getters
    public LinkedList<Field> getListOfFields() { return listOfFields; }
    public LinkedList<Player> getListOfPlayers() { return listOfPlayers; }
    public LinkedList <ActionCard> getListOfActionCards(){ return listOfActionCards; }
    public void setCurrentPlayer(Player currentPlayer){this.currentPlayer = currentPlayer; }
    public Player getCurrentPlayer(){return this.currentPlayer; }
    public void setTwoDice(int [] twoDice){this.twoDice = twoDice; }
    public int [] getTwoDice(){ return this.twoDice; }


    /*Create an inputStream, Read content into the inputStream, Parse the Json content into a Json Array,
     * Save attributes of Json object into the Field object, add Field object into the Field List*/
    public void createFieldListFromJson(String fileName){
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream != null) {
                String jsonContent = new Scanner(inputStream).useDelimiter("\\A").next();
                JSONArray jsonArray = new JSONArray(jsonContent);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    int price = jsonObject.getInt("price");
                    int rent = jsonObject.getInt("rent");
                    JSONArray pricesJson = jsonObject.getJSONArray("prices");
                    int[] prices = new int[pricesJson.length()];
                    for(int j = 0; j < pricesJson.length(); j++){
                        prices[j] = pricesJson.getInt(j);
                    }
                    Field.FieldType type = Field.FieldType.valueOf(jsonObject.getString("type"));
                    String description = jsonObject.getString("description");
                    if(type == Field.FieldType.PROPERTY){
                        listOfFields.add(new Field(id, name, price, rent, prices, type, description, this));
                    } else {
                        listOfFields.add(new Field(id, name, type, description, this));
                    }
                }
            } else {
                System.out.println("Resource not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createActionCardListFromJson(String fileName){
        /**
         * read resources/fields.json and create Field objects
         */
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream != null) {
                String jsonContent = new Scanner(inputStream).useDelimiter("\\A").next();
                JSONArray jsonArray = new JSONArray(jsonContent);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String message = jsonObject.getString("message");
                    int money = jsonObject.getInt("money");
                    int moveDirection = jsonObject.getInt("moveDirection");
                    ActionCard.ActionType type = ActionCard.ActionType.valueOf(jsonObject.getString("type"));
                    listOfActionCards.add(new ActionCard(name, message, money, moveDirection, type));
                }
            } else {
                System.out.println("Resource not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseStartPlayer(LinkedList <Player> listOfPlayers) {
        Random random = new Random();
        int index = random.nextInt(listOfPlayers.size());
        this.currentPlayer = listOfPlayers.get(index);
    }

    public int[] throwDice(){
        Random random = new Random();
        twoDice[0] = random.nextInt(1, 7);
        twoDice[1] = random.nextInt(1, 7);
        return twoDice;
    }

    public void movePlayer(Player player, int diceResult){
        /**
         * move the player to the next field according to dice roll.
         * deposit 200 when player crosses start
         */
        int indexCurrentField = player.getCurrentPositionIndex();
        int fieldsSize = listOfFields.size() - 1;
        int indexTargetField = indexCurrentField + diceResult;

        if(indexTargetField > fieldsSize){
            indexTargetField -= fieldsSize;
            player.deposit(200);
            player.setRoundNumber(player.getRoundNumber() + 1);
        }
        player.setPreviousPositionIndex(indexCurrentField);
        player.setCurrentPositionIndex(indexTargetField);
    }

    public Field.FieldType checkCurrentPlayerFieldLocationType(Player currentPlayer){
        int index = currentPlayer.getCurrentPositionIndex();
        Field.FieldType type = listOfFields.get(index).getType();
        return type;
    }

    public int playerActionPropertyFieldCheckIfBuyable(Player currentPlayer){
        Player owner = listOfFields.get(currentPlayer.getCurrentPositionIndex()).getOwner();
        int canBuyField;
        if(owner == null){
            canBuyField = 1;
        } else if (owner.getName().equals(currentPlayer.getName())) {
            canBuyField = 2;
        } else {
            canBuyField = 0;
        }
        return canBuyField;
    }

    public void updatePlayerEvents(int index, Player player){
        player.getEvents().add(listOfActionCards.get(index));
    }

    public void buyProperty(Player buyer){
        int amount = listOfFields.get(buyer.getCurrentPositionIndex()).getPrice();
        buyer.withdraw(amount);
        buyer.getOwn().add(listOfFields.get(buyer.getCurrentPositionIndex()));
        listOfFields.get(buyer.getCurrentPositionIndex()).setOwner(buyer);
    }

    public void payAndGetRent(Player payer, Player receiver){
        int rent = listOfFields.get(payer.getCurrentPositionIndex()).getRent();
        payer.withdraw(rent);
        receiver.deposit(rent);
    }

    public void moveNumberOfFields(int numberOfFields){

        int temporary = currentPlayer.getCurrentPositionIndex();
        currentPlayer.setCurrentPositionIndex(temporary  + numberOfFields);
        currentPlayer.setPreviousPositionIndex(temporary);
        //TODO: Set Round number if changes
    }

    public void moveToStart(){
        int currentIndex = currentPlayer.getCurrentPositionIndex();
        movePlayer(currentPlayer, currentIndex * -1);
        currentPlayer.setRoundNumber(currentPlayer.getRoundNumber() + 1);
    }

    public void chooseNextPlayer(Player currentPlayer){
        int index = listOfPlayers.indexOf(currentPlayer);
        if(index < listOfPlayers.size() -1){
            if(listOfPlayers.get(index + 1).getInGame()){
                setCurrentPlayer(listOfPlayers.get(index + 1));
            } else {
                chooseNextPlayer(listOfPlayers.get(index + 1));
            }
        } else {
            if(listOfPlayers.get(0).getInGame()){
                setCurrentPlayer(listOfPlayers.get(0));
            } else {
                chooseNextPlayer(listOfPlayers.get(0));
            }
        }
    }

    public int chooseRandomActionCard(){
        Random random = new Random();
        return random.nextInt(listOfActionCards.size());
    }

    public ActionCard.ActionType checkActionCardType(int indexActionCardList) {
        ActionCard.ActionType type = listOfActionCards.get(indexActionCardList).getActionType();
        return type;
    }

    public boolean checkIfNegativeBalance(){
        return currentPlayer.getBalance() < 0;
    }

    public void disablePlayerFromGame(Player player){
        player.setInGame(false);
    }

    public boolean printStatsIfOneRemaining(){
        boolean oneRemaining = false;
        int count = 0;
        for(Player player : listOfPlayers){
            if(player.getInGame()){
                count++;
            }
        }
        return oneRemaining = count == 1;
    }
}