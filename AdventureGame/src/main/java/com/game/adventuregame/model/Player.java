package com.game.adventuregame.model;

public class Player extends Character {

    private int gold;

    public Player() {
        setHealth(20);
        setStrength((int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1));
        setDexterity((int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1));
        setIntelligence((int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1));
        setGold(0);
    }

    public void setGold(int gold) {
        this.gold = this.gold + gold;
    }

    public int getGold() {
        return gold;
    }

}
