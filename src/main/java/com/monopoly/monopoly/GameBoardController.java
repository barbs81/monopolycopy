package com.monopoly.monopoly;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.monopoly.monopoly.GameManager.listOfFields;
import static com.monopoly.monopoly.GameManager.listOfPlayers;

public class GameBoardController implements Initializable {
    @FXML
    Pane pane1, pane2, pane3, pane4;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label name1, name2, name3, name4, goop1, goop2, goop3, goop4, total1, total2, total3, total4,
            in1, in2, in3, in4, out1, out2, out3, out4;
    @FXML
    private TextArea own1, own2, own3, own4, events1, events2, events3, events4;
    @FXML
    private Button throwDice, endGame;
    @FXML
    private ImageView d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18;
    @FXML
    private VBox b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40, b41, b42;
    GameManager gameManager;
    LinkedList<ImageView> diceDots = new LinkedList<>();
    LinkedList<VBox> fieldBoxes = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initializing all backend and GUI elements needed (e.g., list of logical fields, list of GUI fields)
        gameManager = new GameManager();
        initializeDiceDots();
        initializeFieldBoxes();
        setFieldHovers();
        showCurrentPlayer();
        showPlayersData();
        pressThrowDice(throwDice, gameManager.getTwoDice());
    }

    //GUI Functions
    public void showCurrentPlayer() {

        currentPlayer.setText(gameManager.getCurrentPlayer().getName() + "'s turn");
    }

    public void makePlayerPaneVisible(Pane pane) {
        pane.setVisible(true);
    }

    public void setAllPaneLabelsTextAreas(Label name, Label total, Label goop, Label in, Label out, TextArea own, TextArea events, Player player) {
        name.setText(player.getName());
        if (player.getHasOutOfJailCard() == true) {
            goop.setText("YES");
        } else {
            goop.setText("NO");
        }
        total.setText(String.valueOf(player.getBalance()));
        in.setText(String.valueOf(player.getTotalIncome()));
        out.setText(String.valueOf(player.getTotalPayments()));
        own.clear();
        for (Field field : player.getOwn()) {
            own.appendText(field.getName() + "\n");
        }
        own.appendText("Test Property"); //TODO: Remove
        events.clear();
        for (ActionCard actionCard : player.getEvents()) {
            events.appendText(actionCard.getName() + "\n");
        }
        events.appendText("Test Event"); //TODO: Remove
    }

    public void showPlayersData() {
        switch (listOfPlayers.size()) {
            case 2:
                setPlayer1Data(listOfPlayers.get(0));
                setPlayer2Data(listOfPlayers.get(1));
                break;
            case 3:
                setPlayer1Data(listOfPlayers.get(0));
                setPlayer2Data(listOfPlayers.get(1));
                setPlayer3Data(listOfPlayers.get(2));
                break;
            case 4:
                setPlayer1Data(listOfPlayers.get(0));
                setPlayer2Data(listOfPlayers.get(1));
                setPlayer3Data(listOfPlayers.get(2));
                setPlayer4Data(listOfPlayers.get(3));
                break;
        }
    }

    public void setPlayer1Data(Player player) {
        makePlayerPaneVisible(pane1);
        setAllPaneLabelsTextAreas(name1, total1, goop1, in1, out1, own1, events1, player);
    }

    public void setPlayer2Data(Player player) {
        makePlayerPaneVisible(pane2);
        setAllPaneLabelsTextAreas(name2, total2, goop2, in2, out2, own2, events2, player);

    }

    public void setPlayer3Data(Player player) {
        makePlayerPaneVisible(pane3);
        setAllPaneLabelsTextAreas(name3, total3, goop3, in3, out3, own3, events3, player);

    }

    public void setPlayer4Data(Player player) {
        makePlayerPaneVisible(pane4);
        setAllPaneLabelsTextAreas(name4, total4, goop4, in4, out4, own4, events4, player);
    }

    public void initializeDiceDots() {
        diceDots.add(d1);
        diceDots.add(d2);
        diceDots.add(d3);
        diceDots.add(d4);
        diceDots.add(d5);
        diceDots.add(d6);
        diceDots.add(d7);
        diceDots.add(d8);
        diceDots.add(d9);
        diceDots.add(d10);
        diceDots.add(d11);
        diceDots.add(d12);
        diceDots.add(d13);
        diceDots.add(d14);
        diceDots.add(d15);
        diceDots.add(d16);
        diceDots.add(d17);
        diceDots.add(d18);
    }

    public void initializeFieldBoxes() {
        fieldBoxes.add(b1);
        fieldBoxes.add(b2);
        fieldBoxes.add(b3);
        fieldBoxes.add(b4);
        fieldBoxes.add(b5);
        fieldBoxes.add(b6);
        fieldBoxes.add(b7);
        fieldBoxes.add(b8);
        fieldBoxes.add(b9);
        fieldBoxes.add(b10);
        fieldBoxes.add(b11);
        fieldBoxes.add(b12);
        fieldBoxes.add(b13);
        fieldBoxes.add(b14);
        fieldBoxes.add(b15);
        fieldBoxes.add(b16);
        fieldBoxes.add(b17);
        fieldBoxes.add(b18);
        fieldBoxes.add(b19);
        fieldBoxes.add(b20);
        fieldBoxes.add(b21);
        fieldBoxes.add(b22);
        fieldBoxes.add(b23);
        fieldBoxes.add(b24);
        fieldBoxes.add(b25);
        fieldBoxes.add(b26);
        fieldBoxes.add(b27);
        fieldBoxes.add(b28);
        fieldBoxes.add(b29);
        fieldBoxes.add(b30);
        fieldBoxes.add(b31);
        fieldBoxes.add(b32);
        fieldBoxes.add(b33);
        fieldBoxes.add(b34);
        fieldBoxes.add(b35);
        fieldBoxes.add(b36);
        fieldBoxes.add(b37);
        fieldBoxes.add(b38);
        fieldBoxes.add(b39);
        fieldBoxes.add(b40);
        fieldBoxes.add(b41);
        fieldBoxes.add(b42);
    }

    public void setFieldHover(String fieldId, String statement) {
        StringBuilder number = new StringBuilder();
        for (char c : fieldId.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            }
        }
        int index = Integer.parseInt(number.toString()) - 1;
        fieldBoxes.get(index).addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Tooltip.install(fieldBoxes.get(index), new Tooltip(statement));
            }
        });
    }

    public void setFieldHovers() {
        String fieldName = "";
        int price = 0;
        int rent = 0;
        Player owner = null;
        String ownerName = "";
        String description = "";
        String statement = "";
        for (int i = 0; i < listOfFields.size(); i++) {
            if (listOfFields.size() == fieldBoxes.size()) {
                fieldName = listOfFields.get(i).getName();
                price = listOfFields.get(i).getPrice();
                rent = listOfFields.get(i).getRent();
                owner = listOfFields.get(i).getOwner();
                description = listOfFields.get(i).getDescription();
                if (listOfFields.get(i).getType() == Field.FieldType.PROPERTY) {
                    if(listOfFields.get(i).getOwner() != null){
                        ownerName = listOfFields.get(i).getOwner().getName();
                        statement = "field: " + fieldName + "\n" + "price: " + price + "\n" + "rent: " + rent + "\n" + "owner: " + ownerName + "\n" + description;
                    } else {
                        statement = "field: " + fieldName + "\n" + "price: " + price + "\n" + "rent: " + rent + "\n" + "owner: " + owner + "\n" + description;
                    }
                } else {
                    statement = "field: " + fieldName + "\n" + description + "\n";
                }
                setFieldHover(listOfFields.get(i).getId(), statement);
            }
        }
    }

    public void removeDiceDots() {
        for (int i = 0; i < diceDots.size(); i++) {
            diceDots.get(i).setVisible(false);
        }
    }

    public void showNewDiceDots(int[] diceArray) {
        int firstDice = diceArray[0];
        int secondDice = diceArray[1];
        switch (firstDice) {
            case 1:
                d5.setVisible(true);
                break;
            case 2:
                d1.setVisible(true);
                d9.setVisible(true);
                break;
            case 3:
                d1.setVisible(true);
                d5.setVisible(true);
                d9.setVisible(true);
                break;
            case 4:
                d1.setVisible(true);
                d3.setVisible(true);
                d7.setVisible(true);
                d9.setVisible(true);
                break;
            case 5:
                d1.setVisible(true);
                d3.setVisible(true);
                d5.setVisible(true);
                d7.setVisible(true);
                d9.setVisible(true);
                break;
            case 6:
                d1.setVisible(true);
                d3.setVisible(true);
                d4.setVisible(true);
                d6.setVisible(true);
                d7.setVisible(true);
                d9.setVisible(true);
                break;
        }
        switch (secondDice) {
            case 1:
                d14.setVisible(true);
                break;
            case 2:
                d10.setVisible(true);
                d18.setVisible(true);
                break;
            case 3:
                d10.setVisible(true);
                d14.setVisible(true);
                d18.setVisible(true);
                break;
            case 4:
                d10.setVisible(true);
                d12.setVisible(true);
                d16.setVisible(true);
                d18.setVisible(true);
                break;
            case 5:
                d10.setVisible(true);
                d12.setVisible(true);
                d14.setVisible(true);
                d16.setVisible(true);
                d18.setVisible(true);
                break;
            case 6:
                d10.setVisible(true);
                d12.setVisible(true);
                d13.setVisible(true);
                d15.setVisible(true);
                d16.setVisible(true);
                d18.setVisible(true);
                break;
        }
    }

    public void showPlayersOnCurrentField() {
        int indexPrevious, indexCurrent;
        String color;
        for (Player player : listOfPlayers) {
            indexPrevious = player.getPreviousPositionIndex();
            indexCurrent = player.getCurrentPositionIndex();
            color = player.getColor();
            //TODO: Check that the field fx:id matches with the field id from the listOfFields
            fieldBoxes.get(indexPrevious).setStyle("-fx-border-color: white; -fx-border-width: 3");
            fieldBoxes.get(indexCurrent).setStyle("-fx-border-color:" + color + "; -fx-border-width: 3");
            System.out.println("PlayerName: " + player.getName() + " is on field # " + player.getCurrentPositionIndex()); //TODO: Remove
        }
    }

    public void showAlert(Alert.AlertType type, String contentText) {
        Alert alert;
        Image icon = new Image(getClass().getResourceAsStream("message.png"));
        ImageView iconImageView = new ImageView(icon);
        if (type == Alert.AlertType.WARNING){
            alert = new Alert(Alert.AlertType.WARNING);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle("message");
        alert.setHeaderText(listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getName());
        alert.setGraphic(iconImageView);
        iconImageView.setFitWidth(48);
        iconImageView.setFitHeight(48);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public boolean getConfirmation(String contentText){
        boolean confirmed = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Image icon = new Image(getClass().getResourceAsStream("questionMark.png"));
        ImageView iconImageView = new ImageView(icon);
        alert.setTitle("confirm");
        String propertyName = listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getName();
        int price = listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getPrice();
        int rent = listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getRent();
        alert.setHeaderText("name: " + propertyName + "\n" + "price: " + price + "\n" + "rent: " + rent);
        alert.setGraphic(iconImageView);
        iconImageView.setFitWidth(48);
        iconImageView.setFitHeight(48);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            confirmed = true;
        }
        return confirmed;
    }

    public void playerActionAlertsBasedOnField(Field.FieldType type){
        int canBuy;
        boolean canLeaveJail, wants;
        switch(type){
            case PROPERTY:
                canBuy = gameManager.playerActionDistrictStationFieldCheckIfBuyable(gameManager.getCurrentPlayer());
                if(canBuy == 0){
                    Player owner = listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getOwner();
                    int rent = listOfFields.get(gameManager.getCurrentPlayer().getCurrentPositionIndex()).getRent();
                    showAlert(Alert.AlertType.INFORMATION, "You landed on " + owner.getName() + "'s property and will pay rent: " + rent + ".");
                    gameManager.payAndGetRent(gameManager.getCurrentPlayer(), owner);
                    showPlayersData();
                    //TODO: Player leaves the game if his balance is negative
                } else if (canBuy == 1){
                    wants = getConfirmation("This property is not owned by anyone. Do you want to buy this property?");
                    if(wants == true){
                        gameManager.buyProperty(gameManager.getCurrentPlayer());
                    }
                    showPlayersData();
                    setFieldHovers();
                    //TODO: Player leaves the game if his balance is negative
                } else if (canBuy == 2){
                    showAlert(Alert.AlertType.INFORMATION, "You already own this property.");
                }
                break;
            case EVENT:
                break;
            case KLIMA_BONUS:
                showAlert(Alert.AlertType.INFORMATION, "You qualify for the Klima Bonus. You're $225 richer.");
                gameManager.getCurrentPlayer().deposit(225);
                showPlayersData();
                break;
            case PRISON:
                canLeaveJail = gameManager.getCurrentPlayer().getHasOutOfJailCard();
                if(canLeaveJail == true){
                    wants = getConfirmation("You have a get out of jail free card, do you want to use it? If not, you have to pay $25 to get out!");
                    if(wants == true){
                        gameManager.getCurrentPlayer().setHasOutOfJailCard(false);
                    } else {
                        showAlert(Alert.AlertType.INFORMATION, "$25 will be removed from your bank account.");
                        gameManager.getCurrentPlayer().withdraw(25);
                    }
                    gameManager.moveNumberOfFields(-1);
                    showPlayersData();
                    showPlayersOnCurrentField();
                }
                break;
            case GO_TO_PRISON:
                showAlert(Alert.AlertType.INFORMATION, "You've been arrested and are going to jail.");
                gameManager.moveToPrison(gameManager.getCurrentPlayer());
                showPlayersOnCurrentField();
                playerActionAlertsBasedOnField(Field.FieldType.PRISON);
                break;
            default:
                break;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //All backend and frontend functions go into throwDice()
    public void pressThrowDice(Button button, int[] diceArray) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameManager.throwDice();
                System.out.println("dice1: " + diceArray[0]); //TODO: Remove
                System.out.println("dice2: " + diceArray[1]); //TODO: Remove
                button.setDisable(true);
                removeDiceDots();
                showNewDiceDots(diceArray);
                gameManager.movePlayer(gameManager.getCurrentPlayer(), diceArray);
                showPlayersOnCurrentField();
                Field.FieldType type = gameManager.checkCurrentPlayerFieldLocationType(gameManager.getCurrentPlayer());
                playerActionAlertsBasedOnField(type);
                gameManager.chooseNextPlayer(gameManager.getCurrentPlayer());
                showCurrentPlayer();
                button.setDisable(false);

                //TODO:
                //0. Complete the ActionCard Field player actions BARBARA
                //1. Remove player if balance negative (remove from player list) -> player lost BARBARA
                //2. If only one player remaining -> game over and player won -> print statistics BARBARA
                //3. Check the move functions -> handle when player goes beyond the array range (LEON??)
                //4. End button -> game over, print statistics, close view
                //5. If player moves to round 2, 3, 4, etc. -> add $200 to balance (LEON)
                //6. GUI -> need a better way to show players on field -> two players on one field at a time (LEON -> BARBARA)
                //7. Test the game -> make corrections (improve code if have time) (MEHMET)
                //8. GUI changes -> if we have time to make it look better
                //10. Descriptions of districts in the fields.json file
                //11. Go through TODO:s in code and remove print test functions + unused variables
                //12. Error: "You already own this property, when two players stand on the same field" (MEHMET)
                //13. Error: Correct the umlauts when data brought in from Json file (MEHMET)
            }
        });
    }
}