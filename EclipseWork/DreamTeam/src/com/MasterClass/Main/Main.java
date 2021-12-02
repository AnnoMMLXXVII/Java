package com.MasterClass.Main;

import com.Player.Roster.Roster;

public class Main {

	public static void main(String[] args) {
		new MainFrame().setVisible(true);

		Roster rost = new Roster();
		rost.displayRoster();
		rost.displayParticularPlayer("Tom");
		rost.displayParticularPlayer("Browns");

	}
}
