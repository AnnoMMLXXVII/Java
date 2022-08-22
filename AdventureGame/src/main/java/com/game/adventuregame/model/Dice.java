package com.game.adventuregame.model;

public class Dice {
    private static int roll;

    public static int getRoll() {
        return roll;
    }

    public static void rollDice(int sides) {
        roll = (int) ((Math.random() * sides) + 1);
    }
}
