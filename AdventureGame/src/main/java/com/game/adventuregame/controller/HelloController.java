package com.game.adventuregame.controller;

import com.game.adventuregame.model.Dice;
import com.game.adventuregame.model.NPC;
import com.game.adventuregame.model.Player;
import com.game.adventuregame.model.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button leftButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button sleepButton;

    @FXML
    private Button runButton;

    @FXML
    private Button searchButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea playerTextArea;
    @FXML
    private TextArea npcTextArea;
    @FXML
    private Button playerStatsRollButton;
    @FXML
    private Button npcStatsRollButton;

    private Rooms room = new Rooms(0);
    private NPC npc = new NPC();
    private Player player = new Player();
    private Dice dice = new Dice();

    public void initialize(URL url, ResourceBundle rb) {
        textArea.setEditable(false);
        if (room.isBlocked() == true || upButton.isDisabled() || rightButton.isDisabled() ||
                downButton.isDisabled() || leftButton.isDisabled()) {
            textArea.appendText("Room is blocked!");
        }
    }

    @FXML
    public void buttonClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == upButton) {
            textArea.appendText("You've decided to go North!\n");
            room.getRoomLocation();
            room.isBlocked();
            room.isHasNPC();
            searchButton.setDisable(false);
            sleepButton.setDisable(false);
            attackButton.setDisable(true);
            runButton.setDisable(true);

        } else if (actionEvent.getSource() == rightButton) {
            textArea.appendText("You've decided to go East!\n");
            room.getRoomLocation();
            room.isBlocked();
            room.isHasNPC();
            textArea.appendText("You have encountered a monster! What are you going to do? \n");
            attackButton.setDisable(false);
            runButton.setDisable(false);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);

        } else if (actionEvent.getSource() == downButton) {
            textArea.appendText("You've decided to go South!\n");
            room.getRoomLocation();
            room.isBlocked();
            room.isHasNPC();
            textArea.appendText("You have encountered a monster! What are you going to do? \n");
            attackButton.setDisable(false);
            runButton.setDisable(false);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);

        } else if (actionEvent.getSource() == leftButton) {
            textArea.appendText("You've decided to go West!\n");
            room.getRoomLocation();
            room.isBlocked();
            room.isHasNPC();
            searchButton.setDisable(false);
            sleepButton.setDisable(false);
            attackButton.setDisable(true);
            runButton.setDisable(true);
        }


        if (room.isHasNPC() == true) {

            if (actionEvent.getSource() == attackButton) {
                if ( dice.getRoll() >= npc.getDexterity()) {
                    textArea.appendText("You attacked! Monster health is now: " +
                            String.valueOf((npc.getHealth() - (player.getStrength() / 3)) + "\n"));
                    if ((npc.getHealth() - (player.getStrength() / 3)) > 0) {



                        textArea.appendText("Uh Oh, the monster is still alive!\n");



                        if (dice.getRoll() >= player.getDexterity()) {
                            textArea.appendText("Monster now gets to attack you! Your health is now: " +
                                    String.valueOf(player.getHealth() - (npc.getStrength()) / 3) + "\n");
                            playerTextArea.appendText("Health: " +
                                    String.valueOf(player.getHealth() - (npc.getStrength()) / 3) + "\n");
                        }
                    }
                    else if ((npc.getHealth() - (player.getStrength() / 3)) < 0) {
                        textArea.appendText("You've killed the monster! What do you do now?\n");
                    }
                }
                else if (dice.getRoll() <= npc.getDexterity()) {
                    textArea.appendText("You missed! The monster now gets to attack you!\n");
                    if (dice.getRoll() >= player.getDexterity()) {
                        textArea.appendText("The monster hit! Your health is now: " +
                                String.valueOf(player.getHealth() - (npc.getStrength()) / 3) + "\n");
                        playerTextArea.appendText("Health: " +
                                String.valueOf(player.getHealth() - (npc.getStrength()) / 3) + "\n");
                    }
                    else {
                        textArea.appendText("The monster missed! Run away!\n");
                    }

                }

            } else if (actionEvent.getSource() == runButton) {
                textArea.appendText("You've decided to run!\n");


            } else if (actionEvent.getSource() == sleepButton) {
                textArea.appendText("You've decided to sleep!\n");


            } else if (actionEvent.getSource() == searchButton) {
                textArea.appendText("You've decided to search!\n");
            }

        }


        if (actionEvent.getSource() == playerStatsRollButton) {
            playerTextArea.appendText("Health: " + player.getHealth() + "\n" +
                    "Dexterity: " + player.getDexterity() + "\n" +
                    "Intelligence: " + player.getIntelligence() + "\n" +
                    "Strength: " + player.getStrength() + "\n" +
                    "Gold: " + player.getGold() + "\n");
            playerTextArea.setEditable(false);
            playerStatsRollButton.setDisable(true);
            attackButton.setDisable(true);
            runButton.setDisable(true);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);
        }


        if (actionEvent.getSource() == npcStatsRollButton) {
            npcTextArea.appendText("Health: " + npc.getHealth() + "\n" +
                    "Dexterity: " + npc.getDexterity() + "\n" +
                    "Intelligence: " + npc.getIntelligence() + "\n" +
                    "Strength: " + npc.getStrength() + "\n");
            npcTextArea.setEditable(false);
            npcStatsRollButton.setDisable(true);
            attackButton.setDisable(true);
            runButton.setDisable(true);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);
        }

        switch ((int)Math.random() * 4){
            case 0:
                upButton.setDisable(true);
                break;
            case 1:
                rightButton.setDisable(true);
                break;
            case 2:
                downButton.setDisable(true);
                break;
            case 3:
                leftButton.setDisable(true);
                break;
        }


    }
}