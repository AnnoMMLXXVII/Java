package com.game.adventuregame.model;

import java.util.ArrayList;

public class Rooms {

    private ArrayList<ArrayList<String>> board;

    private int height;
    private int width;

    ArrayList<ArrayList<Integer>> border;

    private boolean isBlocked = false;
    private boolean isHasNPC = false;
    private int roomLocation;
    private int amountOfGold;

    public Rooms(int roomLocation) {
        board = new ArrayList<>();
        for (int row = 0; row < 10; row++) {
            board.add(new ArrayList<>());
            for (int column = 0; column < 10; column++) {
                board.get(row).add(String.valueOf(isHasNPC));
                board.get(row).add(String.valueOf(isBlocked));
            }
        }
        this.border = border;
        this.roomLocation = roomLocation;
        this.height = 10;
        this.width = 10;
    }

    public ArrayList<ArrayList<Integer>> getBorder() {
        return border;
    }

    public void setBorder(ArrayList<ArrayList<Integer>> border) {
        border = new ArrayList<>(height);
        border = new ArrayList<>(width);
        this.border = border;
    }

    public int getAmountOfGold() {
        return amountOfGold;
    }

    public void setAmountOfGold(int amountOfGold) {
        if (isHasNPC == true) {
            amountOfGold = (int) (Math.random() * (100));
        }
        this.amountOfGold = amountOfGold;
    }

    public boolean isBlocked() {
        int row = 0;
        int column = 0;
        if (row < 0 || row > 10 || column < 0 || column > 10) {
            isBlocked = true;
        } else {
            isBlocked = false;
        }
        return isBlocked;
    }

    public boolean isHasNPC() {
        int npcID = 0;
        if (isBlocked == true) {
            isHasNPC = false;
        }
        if (isBlocked == false) {
            isHasNPC = true;
            npcID++;
        }
        return isHasNPC;
    }

    public void setBlocked(int row, int column) {
        if (row < 0 || row > 10 || column < 0 || column > 10) {
            isBlocked = true;
        } else {
            isBlocked = false;
        }
    }

    public void setIsHasNPC(int npcID) {
        if (isBlocked == true) {
            isHasNPC = false;
        }
        if (isBlocked == false) {
            isHasNPC = true;
            npcID++;
        }
    }

    public int getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation() {
        int roomID = 0;
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                roomID++;
            }
            isHasNPC();
            this.roomLocation = roomID;
        }
    }
}
