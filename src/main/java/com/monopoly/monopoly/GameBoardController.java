package com.monopoly.monopoly;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

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
        showStartPlayer();
        showPlayersData();
        pressThrowDice(throwDice, gameManager.getTwoDice());
    }

    //GUI Functions
    public void showStartPlayer() {
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
        String statement = "";
        for (int i = 0; i < gameManager.getListOfFields().size(); i++) {
            if (gameManager.getListOfFields().size() == fieldBoxes.size()) {
                if (gameManager.getListOfFields().get(i).getType() == Field.Type.DISTRICT ||
                        gameManager.getListOfFields().get(i).getType() == Field.Type.STATION) {
                    statement = "FIELD NAME: " + gameManager.getListOfFields().get(i).getName() + "\n" +
                            "PRICE: " + gameManager.getListOfFields().get(i).getPrice() + "\n" +
                            "RENT: " + gameManager.getListOfFields().get(i).getRent() + "\n" +
                            "OWNER: " + gameManager.getListOfFields().get(i).getOwner() + "\n" +
                            "DESCRIPTION: " + gameManager.getListOfFields().get(i).getDescription() + "\n";
                } else {
                    statement = "FIELD NAME: " + gameManager.getListOfFields().get(i).getName() + "\n" +
                            "DESCRIPTION: " + gameManager.getListOfFields().get(i).getDescription() + "\n";
                }
                setFieldHover(gameManager.getListOfFields().get(i).getId(), statement);
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
                //choose Action depending on field -> Implement with Alert Windows (Player picks Yes, or No)
                //Note -> Player also has the option to leave, or if s/he is negative, has to leave
                //-> then remove player from the player list
                //calculations for the player's attributes -> balance, in, out, usw.
                //update player's GUI statistics -> reflect changes in the side boxes
                //choose next player
                //enable throw dice button, so the round can start all over again
                //Print out statistics when someone presses end button
            }
        });
    }
}