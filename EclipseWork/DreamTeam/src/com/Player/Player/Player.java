package com.Player.Player;

import com.Outline.Player.PlayerOutline;

public class Player implements PlayerOutline {
    private String first, last, team, position;
    private int number, age, years;

    public Player(String first, String last, String team, String position, int number, int age, int years) {
        this.first = first;
        this.last = last;
        this.team = team;
        this.position = position;
        this.number = number;
        this.age = age;
        this.years = years;
    }

    public Player getPlayerInstance() {
        return this;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }


}
