package com.game.adventuregame.model;

import java.util.ArrayList;

public class Room {

//    private ArrayList<ArrayList<String>> board;

    //    private int height;
//    private int width;
    private int x;
    private int y;
    private boolean isEdge = false;
    private boolean isBlocked = false;
    private Character npc;
    private int gold = 0;


//    ArrayList<ArrayList<Integer>> border;

//    private boolean isBlocked = false;
//    private boolean isHasNPC = false;
//    private int roomLocation;
//    private int amountOfGold;

    public Room(int x, int y) {
        this.x = x;
        this.y = y;
//        board = new ArrayList<>();
//        for (int row = 0; row < 10; row++) {
//            board.add(new ArrayList<>());
//            for (int column = 0; column < 10; column++) {
//                board.get(row).add(String.valueOf(isHasNPC));
//                board.get(row).add(String.valueOf(isBlocked));
//            }
//        }
//        this.border = border;
//        this.roomLocation = roomLocation;
//        this.height = 10;
//        this.width = 10;
    }

    public boolean isEdge() {
        return isEdge;
    }

    public void setEdge(boolean edge) {
        isEdge = edge;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getLocation() {
        return getX() +","+ getY();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Character getNpc() {
        return npc;
    }

    public void setNpc(Character npc) {
        this.npc = npc;
    }

    public int getRemainingGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String toString() {
        return String.format("|(%s,%s) - %s - %s - G%s - %s|", x, y, isEdge ? "Y" : "N", ((NPC)getNpc()).getName(), getRemainingGold(), isBlocked() ? "X" : "O");
    }

}
