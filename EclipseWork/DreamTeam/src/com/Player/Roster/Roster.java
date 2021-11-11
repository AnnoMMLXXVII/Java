package com.Player.Roster;

import com.Player.Player.Player;
import com.Player.RosterManager.RosterManager;

public class Roster {
    private RosterManager rm = null;

    public Roster() {
        rm = getRosterManagerInstance();
    }

    private RosterManager getRosterManagerInstance() {
        return (rm == null) ? RosterManager.getInstance() : rm;
    }

    public void displayRoster() {
        printRoster(getRosterManagerInstance());
    }

    private void printRoster(RosterManager rm) {
        for (Player p : rm.getRosterList()) {
            System.out.printf("[ %10s, %10s, %8s, %2s, %2d, %2d, %2d ] \n",
                    p.getFirst(), p.getLast(), p.getTeam(), p.getPosition(), p.getNumber(), p.getAge(), p.getYears());
        }
        System.out.println();
    }

    public void displayParticularPlayer(String name) {
        printByAttribute(getRosterManagerInstance(), name);
    }

    private void printByAttribute(RosterManager rm, String input) {
        for (Player p : rm.getRosterList()) {
            if (p.getFirst().equalsIgnoreCase(input)
                    || p.getLast().equalsIgnoreCase(input)
                    || p.getPosition().equalsIgnoreCase(input)
                    || p.getTeam().equalsIgnoreCase(input)) {
                printPlayerAttribute(p);
            }
        }
    }

    private void printPlayerAttribute(Player p) {
        System.out.printf("[ %10s, %10s, %8s, %2s, %2d, %2d, %2d ] \n",
                p.getFirst(), p.getLast(), p.getTeam(), p.getPosition(), p.getNumber(), p.getAge(), p.getYears());
    }


}
