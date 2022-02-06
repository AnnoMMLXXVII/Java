
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SmartHome {

	public static void main(String[] args) {

		double temperature = 20; // create the variables
		String ACSettings;
		boolean isMusicOn = false;
		boolean isTVOn = false;
		String song = null;
		String channel = "";
		boolean roomLights[] = { // In the beginning all lights off
				false, false, false, false, false };
		String roomNames[] = { "bedroom", "kitchen", "living room", "bathroom", "garage" };

		Scanner console = new Scanner(System.in); // created a scanner to read the user input

		System.out.println("Welcome home! What would you like to do?");

		while (true) {
			String command = console.nextLine(); // This checks for the command and performs the action

			if (command.equals("Change temperature")) {
				System.out.println("What temperature would you like?");
				double newTemp = console.nextDouble();
				console.nextLine();
				if (temperature > newTemp) {
					ACSettings = "cool";
					System.out.println("A/C Setting is now: " + ACSettings);
					temperature = newTemp;
				} else if (temperature < newTemp) {
					ACSettings = "heat";
					System.out.println("A/C Setting is now: " + ACSettings);
					temperature = newTemp;
				}
				System.out.print("The temprature is now: ");
				System.out.println(temperature);
			} else if (command.equals("Play music")) {
				if (isMusicOn) {
					System.out.println("Currently playing: " + song);
				}
				isMusicOn = false;
				System.out.println("What song would you like?");
				String newsong = console.nextLine();

				try {
					Scanner songs = new Scanner(new File("songs.txt")); // This opens the songs file in order to play
																		// new song
					while (songs.hasNextLine()) {
						song = songs.nextLine();
						if (song.contains(newsong)) {
							System.out.println("Currently playing: " + song);
							isMusicOn = true;
							break;
						}
					}
					if (!isMusicOn) {
						System.out.println("The Song: " + newsong + " is not in playlist!");
					}
					songs.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else if (command.equals("Stop music")) {
				if (isMusicOn) {
					isMusicOn = false;
					System.out.println("Stoped playing music.");
				}
			} else if (command.equals("Turn on television")) {
				if (isTVOn) {
					System.out.println("Currently showing: " + channel);
				}
				isTVOn = false;
				System.out.println("What channel would you like?");
				String newChannel = console.nextLine();

				try {
					Scanner channels = new Scanner(new File("channels.txt"));
					while (channels.hasNextLine()) {
						channel = channels.nextLine();
						if (channel.equals(newChannel)) {
							System.out.println("Currently showing: " + channel);
							isTVOn = true;
							if (roomLights[2]) {
								System.out.println("Would you like lights dimmed?");
								if (console.nextLine().equals("Yes")) {
									roomLights[2] = false;
									System.out.println(roomNames[2] + "'s lights are turned off.");

								}
							}
							break;
						}
					}
					if (!isTVOn) {
						System.out.println("The channel: " + newChannel + " is not subscribed!");
					}
					channels.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			} else if (command.equals("Turn off television")) {
				if (isTVOn) {
					isTVOn = false;
					System.out.println("Television is turned off.");
				}
			} else if (command.equals("Turn on light")) {
				System.out.println("Which room light would you like to turn on?");
				String roomname = console.nextLine();
				boolean isRoomFound = false;

				for (int i = 0; i < roomNames.length; i++) {
					if (roomNames[i].equals(roomname)) {
						roomLights[i] = true;
						System.out.println(roomname + "'s lights are turned on.");
						isRoomFound = true;
					}
				}
				if (!isRoomFound) {
					System.out.println(roomname + " doesn't have system interface!");
				}
			} else if (command.equals("Turn off light")) {
				System.out.println("Which room light would you like to turn off?");
				String roomname = console.nextLine();
				boolean isRoomFound = false;

				for (int i = 0; i < roomNames.length; i++) {
					if (roomNames[i].equals(roomname)) {
						if (roomLights[i]) {
							roomLights[i] = false;
							System.out.println(roomname + "'s lights are turned off.");
						}
						isRoomFound = true;
					}
				}
				if (!isRoomFound) {
					System.out.println(roomname + " doesn't have system interface!");
				}
			} else if (command.equals("Make a call")) {

				if (isTVOn) { // turn off television
					isTVOn = false;
					System.out.println("Television is turned off.");
				}
				if (isMusicOn) {
					isMusicOn = false;
					System.out.println("Stoped playing music.");
				}
				System.out.println("What number would you like to call");
				String number = console.nextLine();

				boolean isValidNumber = true; // checking for a the correct number
				if (number.length() != 10) {
					isValidNumber = false;
				} else {
					for (int i = 0; i < 10; i++) {
						if (number.charAt(i) > '9' || number.charAt(i) < '0') {
							isValidNumber = false;
						}
					}
				}
				if (isValidNumber) {
					System.out.println("Calling " + number + " ....");
				} else {
					System.out.println(number + " is not a valid phone number");
				}
			} else if (command.equals("Answer doorbell")) {
				if (isTVOn) {
					isTVOn = false;
					System.out.println("Television is turned off.");
				}
				if (isMusicOn) {
					isMusicOn = false;
					System.out.println("Stoped playing music.");
				}
				System.out.println("What would you like to say?");
				String message = console.nextLine();
				System.out.println("Message \"" + message + "\" delivered.");
			} else if (command.equals("Close system")) {
				if (isTVOn) {
					isTVOn = false;
					System.out.println("Television is turned off.");
				}
				if (isMusicOn) {
					isMusicOn = false;
					System.out.println("Stoped playing music.");
				}
				for (int i = 0; i < roomLights.length; i++) {
					if (roomLights[i]) {
						roomLights[i] = false;
						System.out.println(roomNames[i] + "'s lights are turned off.");
					}
				}
				System.out.println("Shutting down");
				console.close();
				break; // ending the system and loop
			} else {
				System.out.println("Sorry, Unable to understand command!");
			}
		}
	}

}