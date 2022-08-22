package com.game.adventuregame.controller;

import com.game.adventuregame.model.NPC;
import com.game.adventuregame.model.Room;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomController implements Initializable {

    private static final int X = 10;
    private static final int Y = 10;
    private static List<List<Room>> rooms = new ArrayList<>();

    public RoomController() {
        initialize(null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRooms();
    }

    /**
     * Method to initialize all Rooms
     */
    private void initializeRooms() {
        rooms = new ArrayList<>();
        for (int i = 0; i < Y; i++) {
            rooms.add(initializeRowOfRooms());
        }
        updateEdge();
        updateRoomAttributes();
//        printRoomLayout();
    }

    /**
     * Method that will initialize one Row of Rooms
     *
     * @return
     */
    private List<Room> initializeRowOfRooms() {
        List<Room> rowOfRooms = new ArrayList<>();
        for (int i = 0; i < X; i++) {
            rowOfRooms.add(new Room(i, 0));
        }
        return rowOfRooms;
    }

    /**
     * Method that will update the room to be edges
     */
    private void updateEdge() {
        rooms.get(0).forEach(e -> e.setEdge(true));                 // Top Edge
        rooms.get(rooms.size() - 1).forEach(e -> e.setEdge(true));    // Bottom Edge
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).get(0).setEdge(true);                      // Left Edge
            rooms.get(i).get(rooms.size() - 1).setEdge(true);         // Right Edge
        }
    }

    /**
     *  Update each room's attributes
     *  These may include Location, Gold, NPC, and Blocked or not
     */
    private void updateRoomAttributes() {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
                Room r = rooms.get(i).get(j);
                r.setX(i);
                r.setY(j);
                r.setGold(randomizeGold());
                r.setNpc((NPC)getRandomNPC());
                r.setBlocked(randomizeIfRoomIsBlocked());
            }
        }
    }

    private Integer randomizeGold() {
        return new Random(System.nanoTime()).nextInt(200)+1;
    }

    private boolean randomizeIfRoomIsBlocked() {
        Random r = new Random(System.nanoTime());
        if(r.nextInt(9999999)%5 == 0) {
            return true;
        }
        return false;
    }

    private NPC getRandomNPC() {
        List<String> npc = NonPlayableCharacterRepository.getNonPlayableCharacterNames();
        return new NPC(npc.get(new Random(System.nanoTime()).nextInt(npc.size()-1)+0)) ;
    }

    private void printRoomLayout() {
        rooms.forEach(i -> {
            i.forEach(e -> {
                System.out.printf("%s", e.toString());
            });
            System.out.println();
        });
    }

    public List<List<Room>> getRooms() {
        return rooms;
    }

}
