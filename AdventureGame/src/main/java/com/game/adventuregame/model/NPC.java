package com.game.adventuregame.model;

public class NPC {

    private int health;
    private int strength;
    private int dexterity;
    private int intelligence;

    public NPC() {
        this.health = (int) ((Math.random() * 6) + 1);
        this.strength = (int) ((Math.random() * 6) + 1) * 2;
        this.dexterity = (int) ((Math.random() * 6) + 1) * 2;
        this.intelligence = (int) ((Math.random() * 6) + 1) * 2;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int minHealth() {
        if (health < 0) {
            health = 0;
        }
        return health;
    }
}
