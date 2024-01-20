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
                    System.out.println("id: " + id); //TODO: Remove
                    String name = jsonObject.getString("name");
                    System.out.println("name: " + name); //TODO: Remove
                    int price = jsonObject.getInt("price");
                    System.out.println("price: " + price); //TODO: Remove
                    int rent = jsonObject.getInt("rent");
                    System.out.println("rent: " + rent); //TODO: Remove
                    JSONArray pricesJson = jsonObject.getJSONArray("prices");
                    int[] prices = new int[pricesJson.length()];
                    for(int j = 0; j < pricesJson.length(); j++){
                        prices[j] = pricesJson.getInt(j);
                        System.out.println("prices: " + prices[j]); //TODO: Remove
                    }
                    Field.FieldType type = Field.FieldType.valueOf(jsonObject.getString("type"));
                    System.out.println("type: " + type); //TODO: Remove
                    String description = jsonObject.getString("description");
                    System.out.println("description: " + description); //TODO: Remove
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
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream != null) {
                String jsonContent = new Scanner(inputStream).useDelimiter("\\A").next();
                JSONArray jsonArray = new JSONArray(jsonContent);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    System.out.println("name: " + name); //TODO: Remove
                    String message = jsonObject.getString("message");
                    System.out.println("message: " + message); //TODO: Remove
                    int money = jsonObject.getInt("money");
                    System.out.println("money: " + money); //TODO: Remove
                    int moveDirection = jsonObject.getInt("moveDirection");
                    System.out.println("moveDirection: " + moveDirection); //TODO: Remove
                    ActionCard.ActionType type = ActionCard.ActionType.valueOf(jsonObject.getString("type"));
                    System.out.println("type: " + type); //TODO: Remove
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
        System.out.println("startPlayer: " + currentPlayer.getName()); //TODO: Remove
    }

    public int[] throwDice(){ //TODO: Implement in GUI (e.g., animation two dice?)
        Random random = new Random();
        twoDice[0] = random.nextInt(1, 7);
        twoDice[1] = random.nextInt(1, 7);
        return twoDice;
    }

    public void movePlayer(Player player, int [] diceResult){
        int numberOfMoves = diceResult[0] + diceResult[1];
        System.out.println("Player is: " + player.getName()); //TODO: Remove
        int indexCurrentField = player.getCurrentPositionIndex();
        System.out.println("indexCurrentField: " + indexCurrentField); //TODO: Remove
        int indexTargetField = indexCurrentField + numberOfMoves;
        System.out.println("indexTargetField: " + indexTargetField); //TODO: Remove
        player.setPreviousPositionIndex(indexCurrentField);
        player.setCurrentPositionIndex(indexTargetField);
        //TODO: Set round number
    }

    public Field.FieldType checkCurrentPlayerFieldLocationType(Player currentPlayer){
        int index = currentPlayer.getCurrentPositionIndex();
        Field.FieldType type = listOfFields.get(index).getType();
        System.out.println("field type: " + type); //TODO: Remove
        return type;
    }

    public int playerActionDistrictStationFieldCheckIfBuyable(Player currentPlayer){
        Player owner = listOfFields.get(currentPlayer.getCurrentPositionIndex()).getOwner();
        int canBuyField;
        if(owner == null){
            canBuyField = 1;
        } else if (owner != null && owner.getName() == currentPlayer.getName()) {
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
        moveNumberOfFields(currentIndex * -1);
        currentPlayer.setRoundNumber(currentPlayer.getRoundNumber() + 1);
    }

    public void moveToPrison(Player player){
        moveNumberOfFields(-21);
    }

    public void chooseNextPlayer(Player currentPlayer){
        int index = listOfPlayers.indexOf(currentPlayer);
        if(index < listOfPlayers.size() - 1){
            setCurrentPlayer(listOfPlayers.get(index + 1));
        } else {
            setCurrentPlayer(listOfPlayers.get(0));
        }
    }

    public int chooseRandomActionCard(){
        Random random = new Random();
        int index = random.nextInt(listOfActionCards.size());
        return index;
    }

    public ActionCard.ActionType checkActionCardType(int indexActionCardList) {
        ActionCard.ActionType type = listOfActionCards.get(indexActionCardList).getActionType();
        return type;
    }

    public boolean checkIfNegativeBalance(){
        if(currentPlayer.getBalance() < 0){
            return true;
        } else {
            return false;
        }
    }

    public void removePlayerFromGame(Player player){
        listOfPlayers.remove(player);
    }
    public boolean printStatsIfOneRemaining(int sizeOfPlayerList){
        if(sizeOfPlayerList == 1){
            return true;
        } else {
            return false;
        }
    }
}