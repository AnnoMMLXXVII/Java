package com.Player.PlayerController;

import java.util.ArrayList;

import com.Outline.PlayerController.PlayerControllerOutline;
import com.Player.Player.Player;

public class PlayerController implements PlayerControllerOutline {
    private ArrayList<Player> roster;


    Player p;

    public PlayerController() {

        setRosterList(new ArrayList<Player>());
    }

    @Override
    public void addPlayer(Player p) {
        roster.add(p);
    }

    @Override
    public void removePlayer(Player p) {
        roster.remove(p);
    }

    @Override
    public void updatePlayerFirst(String first) {
//		for (Player p: roster) {
        p.setFirst(first);
//		}
    }

    @Override
    public void updatePlayerLast(String last) {
        p.setLast(last);
    }

    @Override
    public void updatePlayerTeam(String team) {
        p.setTeam(team);
    }

    @Override
    public void updatePlayerPosition(String position) {
        p.setPosition(position);
    }

    @Override
    public void updatePlayerNumber(int number) {
        p.setNumber(number);
    }

    @Override
    public void updatePlayerAge(int age) {
        p.setAge(age);
    }

    @Override
    public void updatePlayerYears(int years) {
        p.setYears(years);
    }

    @Override
    public boolean removeAllPlayers() {
        boolean flag = true;
        if (roster.isEmpty()) {
            flag = false;
            // flag == false because the list was already emmpty and couldn't removeAllPlayers
        } else {
            roster.removeAll(this.roster);
        }
        return flag;
    } // endae removeAllPlayers


    @Override
    public void setRosterList(ArrayList<Player> roster) {
        this.roster = roster;
    }

    @Override
    public ArrayList<Player> getRosterList() {
        return (roster == null) ? new ArrayList<Player>() : roster;
    }

}
