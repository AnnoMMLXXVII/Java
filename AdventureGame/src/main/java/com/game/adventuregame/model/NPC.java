package com.game.adventuregame.model;

public class NPC extends Character {

    private String name;

    public NPC(String name) {
        this.name = name;
        setHealth((int) ((Math.random() * 6) + 1));
        setStrength((int) ((Math.random() * 6) + 1) * 2);
        setDexterity((int) ((Math.random() * 6) + 1) * 2);
        setIntelligence((int) ((Math.random() * 6) + 1) * 2);
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return -1;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "health=" + health +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                '}';
    }
}
