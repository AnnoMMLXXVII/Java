package com.game.adventuregame.model;

public class Player {

    private int health;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int gold;

    public Player() {
        this.health = 20;
        this.strength = (int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1);
        this.dexterity = (int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1);
        this.intelligence = (int) ((Math.random() * 6) + 1) + (int) ((Math.random() * 6) + 1) +
                (int) ((Math.random() * 6) + 1);
        this.gold = 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getGold() {
        return gold;
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", gold=" + gold +
                '}';
    }
}
