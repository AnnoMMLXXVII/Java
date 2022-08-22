package com.game.adventuregame.controller;

import com.game.adventuregame.model.*;
import com.game.adventuregame.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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

    private RoomController roomController;

    private Room room;
    private Dice dice;
    private String currentLocation;
    private Character npc;
    private Character player;
    private List<List<Room>> rooms;
    private int nextLocation;


    //
//    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeObjects();
        textArea.setEditable(false);

        currentLocation = room.getLocation();
        System.out.printf("Current Location: %s\n", currentLocation);
//        if (room.isBlocked() == true || upButton.isDisabled() || rightButton.isDisabled() ||
//                downButton.isDisabled() || leftButton.isDisabled()) {
//            textArea.appendText("Room is blocked!");
//        }
        rngForEncounter();
    }

    //
    private void initializeObjects() {
        roomController = new RoomController();
        dice = new Dice();
        player = new Player();
        rooms = roomController.getRooms();
        room = rooms.get(rooms.size() / 2).get(rooms.size() / 2);   // Start in the Middle of the Array
        npc = room.getNpc();
        System.out.println(npc.toString());
    }

    @FXML
    public void upAction(ActionEvent actionEvent) {
        System.out.printf("Current Location: %s\n", currentLocation);
        if (!canTravelToNextRoom("UP")) {
            return;
        }
        textArea.appendText("You've decided to go North!\n");
        currentLocation = room.getLocation();
        disablePlayerActions();
        rngForEncounter();
    }

    @FXML
    public void rightAction(ActionEvent actionEvent) {
        System.out.printf("Current Location: %s\n", currentLocation);
        if (!canTravelToNextRoom("RIGHT")) {
            return;
        }
        textArea.appendText("You've decided to go East!\n");
        currentLocation = room.getLocation();
        disablePlayerActions();
        rngForEncounter();
    }

    @FXML
    public void downAction(ActionEvent actionEvent) {
        System.out.printf("Current Location: %s\n", currentLocation);
        if (!canTravelToNextRoom("DOWN")) {
            return;
        }
        textArea.appendText("You've decided to go South!\n");
        currentLocation = room.getLocation();
        disablePlayerActions();
        rngForEncounter();
    }

    @FXML
    public void leftAction(ActionEvent actionEvent) {
        System.out.printf("Current Location: %s\n", currentLocation);
        if (!canTravelToNextRoom("LEFT")) {
            return;
        }
        textArea.appendText("You've decided to go West!\n");
        currentLocation = room.getLocation();
        disablePlayerActions();
        rngForEncounter();
    }

    @FXML
    public void rollPlayer(ActionEvent actionEvent) {
        refreshPlayerStats();
        resetAfterRoll();
        playerStatsRollButton.setDisable(true);
    }

    @FXML
    public void rollNPC(ActionEvent actionEvent) {
        refreshNPCStats();
        resetAfterRoll();
        npcStatsRollButton.setDisable(true);
    }

    private void rngForEncounter() {
        if (hasEncounteredNPC()) {
            textArea.appendText("You have encountered a monster! What are you going to do? \n");
            toggleAllNavigationActions(true);
            attackButton.setDisable(false);
            runButton.setDisable(false);
        } else {
            textArea.appendText("There are no monsters! What are you going to do? \n");
            toggleAllNavigationActions(false);
            searchButton.setDisable(false);
            runButton.setDisable(false);
            attackButton.setDisable(true);
            runButton.setDisable(true);
        }
    }

    @FXML
    public void attackAction(ActionEvent actionEvent) {
        refreshNPCStats();
        refreshPlayerStats();
        searchButton.setDisable(true);
        sleepButton.setDisable(true);
        Dice.rollDice(20);
        int temp = 0;
        if (Dice.getRoll() >= npc.getDexterity()) {
            temp = npc.getHealth() - (player.getStrength() / 3);
            textArea.appendText(String.format("Player Attacked: %s\nMonster's Health changed from %s to %s\n",
                    (player.getStrength() / 3), npc.getHealth(), (temp < 0 ? 0 : temp)));
            npc.setHealth(temp);
            refreshNPCStats();
            if (temp > 0) {
                textArea.appendText("Uh Oh, the monster is still alive!\n");
                if (Dice.getRoll() >= player.getDexterity()) {
                    temp = player.getHealth() - (npc.getStrength() / 3);
                    textArea.appendText(String.format("Monster Attacked: %s\nPlayer's Health changed from %s to %s\n",
                            (npc.getStrength() / 3), player.getHealth(), (temp < 0 ? 0 : temp)));
                    player.setHealth(temp);
                    refreshPlayerStats();
                }
            } else {
                textArea.appendText("You've killed the monster! What do you do now?\n");
                attackButton.setDisable(true);
                runButton.setDisable(true);
                searchButton.setDisable(false);
                sleepButton.setDisable(false);
                toggleAllNavigationActions(false);
                return;
            }
        } else {
            textArea.appendText("You missed! The monster now gets to attack you!\n");
            if (Dice.getRoll() >= player.getDexterity()) {
                temp = player.getHealth() - (npc.getStrength() / 3);
                textArea.appendText(String.format("Monster Attacked: %s\nPlayer's Health changed from %s to %s\n",
                        (npc.getStrength() / 3), player.getHealth(), (temp < 0 ? 0 : temp)));
                player.setHealth(temp);
                refreshPlayerStats();
            } else {
                textArea.appendText("The monster missed! Running away!\n");
                runningAway();
                attackButton.setDisable(true);
                runButton.setDisable(true);
            }
        }
        checkIfPlayerHealthIsZero();
    }

    @FXML
    public void runAction(ActionEvent actionEvent) {
        runButton.setDisable(true);
        attackButton.setDisable(true);
        searchButton.setDisable(true);
        sleepButton.setDisable(true);
        textArea.appendText("Running away!\n");
        if (textArea.getText().isEmpty()) {
            refreshNPCStats();
        }
        runningAway();
        checkIfPlayerHealthIsZero();
    }

    @FXML
    public void searchAction(ActionEvent actionEvent) {
        textArea.appendText("Searching for Gold...!\n");
        Dice.rollDice(20);
        if (Dice.getRoll() < player.getIntelligence()) {
            sleepButton.setDisable(true);
            if (room.getRemainingGold() > 0) {
                ((Player) player).setGold(room.getRemainingGold());
                textArea.appendText("Obtained " + room.getRemainingGold() + " Gold!\n");
                refreshPlayerStats();
                room.setGold(0);
            } else {
                textArea.appendText("No gold found...!\n");
            }
        } else {
            textArea.appendText("No gold found...!\n");
        }
    }

    @FXML
    public void sleepAction(ActionEvent actionEvent) {
        toggleAllNavigationActions(true);
        searchButton.setDisable(true);
        textArea.appendText("Sleeping to regain health!\n");
        if (npc.getHealth() > 0) {
            if ((int) ((Math.random() * 6) + 1) == 1) {
                player.setHealth(20);
                int monsterAttack = (int) ((Math.random() * 6) + 1);
                player.setHealth((player.getHealth() - monsterAttack));
                refreshPlayerStats();
                textArea.appendText("Monster has landed attack while asleep!\n");
                textArea.appendText("Your Health: " + (player.getHealth() + "\n"));
                toggleAllNavigationActions(false);
            } else {
                player.setHealth(20);
            }
        } else {
            toggleAllNavigationActions(false);
        }

    }

    private boolean canTravelToNextRoom(String direction) {
        Room room = null;
        String location = "";
        if (isDirectionAllowable(direction)) {
            location = updateDirection("LEFT".equals(direction) || "RIGHT".equals(direction) ? true : false);
            room = updateRoom(location);
            if (!isNextRoomBlocked(room)) {
                this.room = room;
                this.currentLocation = location;
                npc = this.room.getNpc();
                System.out.println(npc.toString());
                return true;
            } else {
                textArea.appendText("Next Room is Blocked!\n");
                return false;
            }
        } else {
            textArea.appendText("Near the Edge. Cannot Move!\n");
        }
        return false;
    }

    /**
     * Method that will determine if player movement is allowable.
     * Direction mappings and Translate to First Person View movement.
     * UP -> Forward -> (X,Y+1)
     * DOWN -> BACK -> (X, Y-1)
     * RIGHT -> RIGHT -> (X-1, Y)
     * LEFT -> LEFT -> (X+1,Y)
     *
     * @param direction
     * @return
     */
    private boolean isDirectionAllowable(String direction) {
        int x = Integer.parseInt(currentLocation.split(",")[0]);
        int y = Integer.parseInt(currentLocation.split(",")[1]);
        switch (direction) {
            case "UP":
                updateLocation(y + 1);
                System.out.printf("N-(%s,%s)\n", x, getNextLocation());
                return getNextLocation() <= rooms.size() ? true : false;
            case "DOWN":
                updateLocation(y - 1);
                System.out.printf("S-(%s,%s)\n", x, getNextLocation());
                return getNextLocation() >= 0 ? true : false;
            case "LEFT":
                updateLocation(x + 1);
                System.out.printf("W-(%s,%s)\n", getNextLocation(), y);
                return getNextLocation() <= rooms.size() ? false : true;
            case "RIGHT":
                updateLocation(x - 1);
                System.out.printf("E-(%s,%s)\n", getNextLocation(), y);
                return getNextLocation() >= 0 ? true : false;
        }
        return false;
    }

    private boolean isNextRoomBlocked(Room room) {
        return room == null ? true : room.isBlocked();
    }

    private String updateDirection(boolean isX) {
        int x = Integer.parseInt(currentLocation.split(",")[0]);
        int y = Integer.parseInt(currentLocation.split(",")[1]);
        if (isX) {
            return getNextLocation() + "," + y;
        } else {
            return (x) + "," + (getNextLocation());
        }
    }

    private void updateLocation(int nextLocation) {
        this.nextLocation = nextLocation;
    }

    private int getNextLocation() {
        return nextLocation;
    }

    private boolean hasEncounteredNPC() {
        return (int) (Math.random()) + System.nanoTime() % 2 == 0 ? true : false;
    }

    private void toggleAllNavigationActions(boolean state) {
        upButton.setDisable(state);
        downButton.setDisable(state);
        rightButton.setDisable(state);
        leftButton.setDisable(state);
    }

    private void disablePlayerActions() {
        searchButton.setDisable(false);
        sleepButton.setDisable(false);
        attackButton.setDisable(true);
        runButton.setDisable(true);
    }

    private void resetAfterRoll() {
        npcTextArea.setEditable(false);
//        attackButton.setDisable(true);
//        runButton.setDisable(true);
//        searchButton.setDisable(true);
//        sleepButton.setDisable(true);
    }

    private Room updateRoom(String location) {
        Room room = null;
        int x = Integer.parseInt(currentLocation.split(",")[0]);
        int y = Integer.parseInt(currentLocation.split(",")[1]);
        if (location != null) {
            x = Integer.parseInt(location.split(",")[0]);
            y = Integer.parseInt(location.split(",")[1]);
        }
        try {
            room = rooms.get(x).get(y);
            System.out.println(room.toString());
        } catch (RuntimeException ex) {
            System.err.println("Unable to Update Room -- On the Edge!");
        }
        return room;
    }

    private void refreshPlayerStats() {
        if (!playerTextArea.getText().isEmpty()) {
            playerTextArea.clear();
        }
        playerTextArea.setWrapText(true);
        playerTextArea.appendText(String.format("Health: %s\nDexterity:%s\nIntelligence:%s\nStrength:%s\nGold:%s\n",
                player.getHealth(), player.getDexterity(), player.getIntelligence(), player.getStrength(), player.getGold()));
    }

    private void refreshNPCStats() {
        if (!npcTextArea.getText().isEmpty()) {
            npcTextArea.clear();
        }
        npcTextArea.setWrapText(true);
        npcTextArea.appendText(String.format("Name:%s\nHealth: %s\nDexterity:%s\nIntelligence:%s\nStrength:%s\n",
                ((NPC) npc).getName(), npc.getHealth(), npc.getDexterity(), npc.getIntelligence(), npc.getStrength()));
    }

    private void runningAway() {
        Dice.rollDice(6);
        int monsterAttack = Dice.getRoll();
        if (monsterAttack < player.getIntelligence()) {
            int temp = (player.getHealth() - monsterAttack);
            player.setHealth(temp);
            refreshPlayerStats();
            textArea.appendText(String.format("Monster Attacked: %s\nPlayer's Health changed from %s to %s\n",
                    monsterAttack, player.getHealth(), (temp < 0 ? 0 : temp)));
            textArea.appendText("Your Health: " + (player.getHealth() + "\n"));
            toggleAllNavigationActions(false);
        } else {
            textArea.appendText("Swiftly ran away!\n");
        }
    }

    private void checkIfPlayerHealthIsZero() {
        if (player.getHealth() <= 0) {
            confirmationPopup("End Game", "Player has died.");
            toggleAllNavigationActions(true);
            searchButton.setDisable(true);
            sleepButton.setDisable(true);
            attackButton.setDisable(true);
            runButton.setDisable(true);
            npcStatsRollButton.setDisable(true);
            playerStatsRollButton.setDisable(true);
            textArea.setDisable(true);
        }

    }

    /**
     * Method for confirmation that will initialize header and Content of the Alert
     *
     * @param header  String
     * @param content String
     * @return boolean
     */
    public static boolean confirmationPopup(String header, String content) {
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText(header);
        conf.setContentText(content);
        Optional<ButtonType> confirmation = conf.showAndWait();
        return (confirmation.isPresent() && confirmation.get() == ButtonType.OK) ? true : false;
    }
}