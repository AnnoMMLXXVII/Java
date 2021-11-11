package com.Outline.PlayerController;

import java.util.ArrayList;

import com.Player.Player.Player;

public interface PlayerControllerOutline {


    public void addPlayer(Player p);

    public void removePlayer(Player p);

    public void updatePlayerFirst(String first);

    public void updatePlayerLast(String last);

    public void updatePlayerTeam(String team);

    public void updatePlayerPosition(String position);

    public void updatePlayerNumber(int number);

    public void updatePlayerAge(int age);

    public void updatePlayerYears(int years);

    public boolean removeAllPlayers();

    public void setRosterList(ArrayList<Player> roster);

    public ArrayList<Player> getRosterList();


}
