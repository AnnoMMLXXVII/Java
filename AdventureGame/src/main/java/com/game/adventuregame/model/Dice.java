package com.game.adventuregame.model;

public class Dice {
    private static int roll;

    public static int getRoll() {
        roll = (int) ((Math.random() * 20) + 1);
        return roll;
    }

    public static void rollDice() {
        roll = (int) ((Math.random() * 20) + 1);
    }
}
