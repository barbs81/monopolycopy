package com.monopoly.monopoly;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.monopoly.monopoly.GameManager.listOfPlayers;


public class StartWindowController implements Initializable {

    @FXML
    ToggleGroup singleClickRadioButtons;
    @FXML
    RadioButton rb2, rb3, rb4;
    @FXML
    TextField name1, name2, name3, name4;
    @FXML
    Button addPlayers;
    @FXML
    Button play;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        rb2.setOnAction(event -> readRadioButtonInputShowTextFields());
        rb3.setOnAction(event -> readRadioButtonInputShowTextFields());
        rb4.setOnAction(event -> readRadioButtonInputShowTextFields());
        addPlayers.setOnAction(event -> writeAllPlayersToList(readRadioButtonInputShowTextFields()));
        switchToGameBoardScene();
    }

    public void makeTextFieldVisible(int numberOfPlayers){
        switch(numberOfPlayers){
            case 2:
                name1.setVisible(true);
                name2.setVisible(true);
                name3.setVisible(false);
                name4.setVisible(false);
                break;
            case 3:
                name1.setVisible(true);
                name2.setVisible(true);
                name3.setVisible(true);
                name4.setVisible(false);
                break;
            case 4:
                name1.setVisible(true);
                name2.setVisible(true);
                name3.setVisible(true);
                name4.setVisible(true);
                break;
        }
    }

    @FXML
    public int readRadioButtonInputShowTextFields(){
        int numberOfPlayers = 0;
        RadioButton selected = (RadioButton) singleClickRadioButtons.getSelectedToggle();
        if(selected != null){
            String fxId = selected.getId();
            System.out.println("fxId: " + fxId); //TODO: Remove
            StringBuilder number = new StringBuilder();
            for(char c : fxId.toCharArray()){
                if(Character.isDigit(c)){
                    number.append(c);
                }
            }
            numberOfPlayers = Integer.parseInt(number.toString());
            makeTextFieldVisible(numberOfPlayers);
        }
        return numberOfPlayers;
    }

    public void writeAllPlayersToList(int numberOfPlayers){
        String playerName1, playerName2, playerName3, playerName4;
        Player player1, player2, player3, player4;
        switch(numberOfPlayers){
            case 2:
                playerName1 = name1.getText();
                player1 = new Player("1", playerName1, "#F08080");
                listOfPlayers.add(player1);
                playerName2 = name2.getText();
                player2 = new Player("2", playerName2, "#F0E68C");
                listOfPlayers.add(player2);
                break;
            case 3:
                playerName1 = name1.getText();
                player1 = new Player("1", playerName1, "#F08080");
                listOfPlayers.add(player1);
                playerName2 = name2.getText();
                player2 = new Player("2", playerName2, "#F0E68C");
                listOfPlayers.add(player2);
                playerName3 = name3.getText();
                player3 = new Player("3", playerName3, "#8FBC8F");
                listOfPlayers.add(player3);
                break;
            case 4:
                playerName1 = name1.getText();
                player1 = new Player("1", playerName1, "#F08080");
                listOfPlayers.add(player1);
                playerName2 = name2.getText();
                player2 = new Player("2", playerName2, "#F0E68C");
                listOfPlayers.add(player2);
                playerName3 = name3.getText();
                player3 = new Player("3", playerName3, "#8FBC8F");
                listOfPlayers.add(player3);
                playerName4 = name4.getText();
                player4 = new Player("4", playerName4, "#ADD8E6");
                listOfPlayers.add(player4);
                break;
        }
        for(Player player : listOfPlayers){
            System.out.println("playerName: " + player.getName()); //TODO: Remove
        }
        addPlayers.setDisable(true);
        play.setDisable(false);
    }

    public void switchScene(Button button, String filePath){
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try {
                    Parent scene = FXMLLoader.load(getClass().getResource(filePath));
                    ((Button)event.getSource()).getScene().setRoot(scene);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void switchToGameBoardScene(){
        switchScene(play, "board-view.fxml");
    }
}