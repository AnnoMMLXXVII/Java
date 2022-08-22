package com.game.adventuregame.model;

public abstract class Character {
    protected int health;
    protected int strength;
    protected int dexterity;
    protected int intelligence;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health < 0 ? 0 : health;
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

    public abstract int getGold();

}
