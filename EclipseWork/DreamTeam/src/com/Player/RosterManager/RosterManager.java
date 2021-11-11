package com.Player.RosterManager;

import java.util.ArrayList;

import com.Player.Player.Player;
import com.Player.PlayerController.PlayerController;
import com.Player.Positions.Offence.FullBacks.FullBack;
import com.Player.Positions.Offence.OffensiveLines.OffensiveLine;
import com.Player.Positions.Offence.QuarterBacks.Quarterback;
import com.Player.Positions.Offence.RunningBacks.RunningBack;
import com.Player.Positions.Offence.TightEnds.TightEnd;
import com.Player.Positions.Offence.WideReceivers.WideReceiver;

public class RosterManager {
    private PlayerController pc;
    private static RosterManager instance;
    private ArrayList<Player> rosterList;
    private ArrayList<String> colHeader;

    public static RosterManager getInstance() {
        return (instance == null) ? new RosterManager() : instance;
    }

    private RosterManager() {
        pc = new PlayerController();
        addAllPlayers(pc);
        initColHeader(colHeader);
        rosterList = pc.getRosterList();
    }

    public ArrayList<Player> getRosterList() {
        return (rosterList == null) ? pc.getRosterList() : rosterList;
    }

    public ArrayList<String> getColHederList() {
        return colHeader;
    }

    private void addAllPlayers(PlayerController pc) {
        addAllOffence(pc);
    }

    private void initColHeader(ArrayList<String> colHeader) {
        colHeader = new ArrayList<String>();
        colHeader.add("First");
        colHeader.add("Last");
        colHeader.add("Team");
        colHeader.add("Position");
        colHeader.add("Number");
        colHeader.add("Age");
        colHeader.add("Exp");
    }

    private void addAllOffence(PlayerController pc) {
        addQBs(pc);
        addRBs(pc);
        addFBs(pc);
        addWRs(pc);
        addTEs(pc);
        addOLs(pc);
    }

    private void addAllDefense(PlayerController pc) {

    }

    private void addQBs(PlayerController pc) {
        pc.addPlayer(
                new Quarterback("Tom", "Brady", "Patriots", 12, 42, 19));
        pc.addPlayer(
                new Quarterback("Joe", "Montana", "49ers", 16, 39, 16));
        pc.addPlayer(
                new Quarterback("Fran", "Tarkenton", "Vikings", 10, 39, 18));
    }

    private void addRBs(PlayerController pc) {
        pc.addPlayer(
                new RunningBack("Walter", "Payton", "Bears", 34, 31, 12));
        pc.addPlayer(
                new RunningBack("Barry", "Sanders", "Lions", 20, 30, 9));
        pc.addPlayer(
                new RunningBack("Jim", "Brown", "Browns", 32, 39, 8));
    }

    private void addFBs(PlayerController pc) {
        pc.addPlayer(
                new FullBack("James", "Devlin", "Patriots", 46, 31, 9));
    }

    private void addWRs(PlayerController pc) {
        pc.addPlayer(
                new WideReceiver("Randy", "Moss", "Vikings", 84, 35, 14));
        pc.addPlayer(
                new WideReceiver("Cris", "Carter", "Vikings", 80, 37, 15));
        pc.addPlayer(
                new WideReceiver("Jerry", "Rice", "49ers", 80, 42, 19));
        pc.addPlayer(
                new WideReceiver("Dante", "Hall", "Chiefs", 82, 30, 8));
        pc.addPlayer(
                new WideReceiver("Michael", "Irvin", "Cowboys", 88, 33, 11));
    }

    private void addTEs(PlayerController pc) {
        pc.addPlayer(
                new TightEnd("Rob", "Gronkowski", "Patriots", 87, 30, 9));
        pc.addPlayer(
                new TightEnd("Shannon", "Sharpe", "Broncos", 81, 35, 13));
    }

    private void addOLs(PlayerController pc) {
        pc.addPlayer(
                new OffensiveLine("Jim", "Parker", "Colts", 77, 33, 10));
        pc.addPlayer(
                new OffensiveLine("Anthony", "Munoz", "Bengals", 78, 34, 12));
        pc.addPlayer(
                new OffensiveLine("Jackie", "Slater", "Rams", 78, 41, 19));
        pc.addPlayer(
                new OffensiveLine("Jonathan", "Ogden", "Ravens", 75, 31, 9));
        pc.addPlayer(
                new OffensiveLine("John", "Hannah", "Patriots", 73, 34, 12));
        pc.addPlayer(
                new OffensiveLine("Bruce", "Matthews", "Oilers", 74, 40, 18));
        pc.addPlayer(
                new OffensiveLine("Randall", "McDaniel", "Vikings", 64, 37, 13));
        pc.addPlayer(
                new OffensiveLine("Jerry", "Kramar", "Packers", 64, 32, 10));
        pc.addPlayer(
                new OffensiveLine("Jim", "Otto", "Raiders", 00, 36, 14));
        pc.addPlayer(
                new OffensiveLine("Dermontti", "Dawson", "Steelers", 63, 35, 12));
    }


}
