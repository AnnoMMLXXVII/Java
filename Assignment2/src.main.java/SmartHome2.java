
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SmartHome2 {
	private static Scanner z = new Scanner(System.in);
	private static HVAC h1;
	private static Oven o1;
	private static Refrigerator r1;
	private static List<MotionSensor> sensors = new ArrayList<>();
	private static List<Camera> cameras = new ArrayList<>();
	private static List<Light> lights = new ArrayList<>();
	private static List<Music> musicDevices = new ArrayList<>();
	private static List<Television> televisions = new ArrayList<>();
	private static List<Device> devices = new ArrayList<>();

	public static void main(String args[]) {
		int in = 0;
		setupDevices();
		System.out.println("Hello! What can I do for You?");
		printHelpOptions();
		while (in != 11) {
			try {
				in = z.nextInt();
				z.nextLine();
			} catch (InputMismatchException ex) {
				System.err.println("Input Mismatch: Please Enter Digits (1 or 2...etc)");
				z.nextLine();
				in = z.nextInt();
				z.nextLine();
			}
			if (in == 1) {
				System.out.println("Select Room to Adjust the Light: ");
				lights.forEach(e -> System.out.println(e.toString()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					System.out.println("Would you like to Turn 'ON' or 'OFF' the light?");
					String onOrOff = z.nextLine();
					DeviceImpl<Light> deviceImplementation = new DeviceImpl<>();
					deviceImplementation.adjustPower(lights, room, onOrOff.equalsIgnoreCase("ON"));
				} else {
					printHelpOptions();
				}
			} else if (in == 2) {
				System.out.println("Select Room to Check Camera Status: ");
				cameras.forEach(e -> System.out.println(e.getRoom()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<Camera> deviceImplementation = new DeviceImpl<>();
					deviceImplementation.previewDevice(cameras, room);
					System.out.println("Would you like to Turn the Camera 'ON' or 'OFF' ?");
					String onOrOff = z.nextLine();
					deviceImplementation.adjustPower(cameras, room, onOrOff.equalsIgnoreCase("ON"));
				} else {
					printHelpOptions();
				}
			} else if (in == 3) {
				System.out.println("Select Room to Play the Music in: ");
				musicDevices.forEach(e -> System.out.printf("%-16s : %s\n", e.getRoom(), e.getSong()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<Music> deviceImplementation = new DeviceImpl<>();
					Music m = deviceImplementation.previewDevice(musicDevices, room);
					deviceImplementation.adjustPower(m, true);
					playMusic(m);
				} else {
					printHelpOptions();
				}
			} else if (in == 4) {
				System.out.println("Select Room to Stop the Music in: ");
				musicDevices.forEach(e -> System.out.printf("%s : %s\n", e.getRoom(), e.getSong()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<Music> deviceImplementation = new DeviceImpl<>();
					Music m = deviceImplementation.previewDevice(musicDevices, room);
					m.stopMusic();
					System.out.println(m.toString());
				} else {
					printHelpOptions();
				}
			} else if (in == 5) {
				System.out.println("Select Room to Check the Motion Sensor Status: ");
				cameras.forEach(e -> System.out.println(e.getRoom()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<MotionSensor> deviceImplementation = new DeviceImpl<>();
					deviceImplementation.previewDevice(sensors, room);
					System.out.println("Would you like to Turn the Sensor 'ON' or 'OFF' ?");
					String onOrOff = z.nextLine();
					deviceImplementation.adjustPower(sensors, room, onOrOff.equalsIgnoreCase("ON"));
				} else {
					printHelpOptions();
				}
			} else if (in == 6) {
				System.out.println("Select Room you want to configure the TV in: ");
				televisions.forEach(e -> System.out.printf("%-16s : %s\n", e.getRoom(), e.getStation()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<Television> deviceImplementation = new DeviceImpl<>();
					Television t = deviceImplementation.previewDevice(televisions, room);
					deviceImplementation.adjustPower(t, true);
					changeTheChannel(t);
				} else {
					printHelpOptions();
				}
			} else if (in == 7) {
				System.out.println("Select Room you want to configure the TV in: ");
				televisions.forEach(e -> System.out.printf("%s : %s\n", e.getRoom(), e.getStation()));
				String room = z.nextLine();
				if (isLocationValid(room)) {
					DeviceImpl<Television> deviceImplementation = new DeviceImpl<>();
					Television t = deviceImplementation.previewDevice(televisions, room);
					t.changeStation(null);
					System.out.println(t.toString());
				} else {
					printHelpOptions();
				}
			} else if (in == 8) {
				DeviceImpl<Oven> deviceImplementation = new DeviceImpl<>();
				System.out.println("Would you like to Turn the Oven 'ON' or 'OFF' ?");
				String onOrOff = z.nextLine();
				deviceImplementation.adjustPower(o1, onOrOff.equalsIgnoreCase("ON"));
				configureTheOven(o1);
			} else if (in == 9) {
				DeviceImpl<Refrigerator> deviceImplementation = new DeviceImpl<>();
				System.out.println("Would you like to Turn the Refrigerator 'ON' or 'OFF' ?");
				String onOrOff = z.nextLine();
				deviceImplementation.adjustPower(r1, onOrOff.equalsIgnoreCase("ON"));
			} else if (in == 10) {
				DeviceImpl<HVAC> deviceImplementation = new DeviceImpl<>();
				System.out.println("Would you like to Turn the HVAC 'ON' or 'OFF' ?");
				String onOrOff = z.nextLine();
				deviceImplementation.adjustPower(h1, onOrOff.equalsIgnoreCase("ON"));
			} else if (in == 11) {
				System.out.println("Shutting down...");
				break;
			} else if (in == 12) {
				printHelpOptions();
			}
		}
	}

	private static void printHelpOptions() {
		System.out.println("I will respond to any of the below mentioned commands:");
		String[] options = { ("LightAdjust"), ("CheckCamera"), ("PlayMusic"), ("StopMusic"), ("SensorCheck"),
				("TelevisionOn"), ("TelevisionOff"), ("Ovencheck"), ("OpenRefrigerator"), ("CheckHVAC"),
				("CloseSystem"), ("Help") };
		for (int i = 0; i < options.length; i++) {
			System.out.printf("[%2d]--%s\n", i + 1, options[i]);
		}
	}

	private static void setupDevices() {

		musicDevices.add(new Music(state(false), removeUnderScore(LOCATIONS.LIVING_ROOM.name())));
		musicDevices.add(new Music(state(false), removeUnderScore(LOCATIONS.UPSTAIRS_BEDROOM.name())));
		musicDevices.add(new Music(state(false), LOCATIONS.GARAGE.name()));
		musicDevices.add(new Music(state(false), LOCATIONS.BASEMENT.name()));
		musicDevices.add(new Music(state(false), LOCATIONS.KITCHEN.name()));

		televisions.add(new Television(state(false), removeUnderScore(LOCATIONS.LIVING_ROOM.name())));
		televisions.add(new Television(state(false), removeUnderScore(LOCATIONS.UPSTAIRS_BEDROOM.name())));
		televisions.add(new Television(state(false), LOCATIONS.BASEMENT.name()));
		televisions.add(new Television(state(false), LOCATIONS.KITCHEN.name()));

		h1 = new HVAC(state(true), "Outside");
		r1 = new Refrigerator(state(true), LOCATIONS.KITCHEN.name());
		o1 = new Oven(state(false), LOCATIONS.KITCHEN.name());

		for (LOCATIONS L : LOCATIONS.values()) {
			sensors.add(new MotionSensor(state(true), removeUnderScore(L.name())));
			cameras.add(new Camera(state(true), removeUnderScore(L.name())));
			lights.add(new Light(state(false), removeUnderScore(L.name())));
		}

		devices.addAll(televisions);
		devices.addAll(musicDevices);
		devices.addAll(cameras);
		devices.addAll(lights);
		devices.add(h1);
		devices.add(r1);
		devices.add(o1);
//		devices.forEach(e -> System.out.println(e.toString()));
	}

	private static String state(boolean state) {
		return state ? "ON" : "OFF";
	}

	private static void configureTheOven(Oven o1) {
		if (o1 == null) {
			return;
		}
		String tempResponse = "";
		if (o1.getTemperature() == 0) {
			System.out.println("Please set the Temperature (F)");
			o1.updateTemperature(z.nextInt());
			System.out.println("Please set the Time (minutes)");
			o1.updateTime(z.nextInt());
			System.out.printf("Oven Configs:\nTemperature -> %s(F)\n Time -> %s (mins)\n", o1.getTemperature(),
					o1.getTime());
		} else {
			System.out.println(
					"Select Options: [U] Update Temperature, [T] Update Time, [Q] Turn Off Oven, or [X] Cancel");
			tempResponse = z.nextLine();
			while (!tempResponse.equalsIgnoreCase("x")) {
				if (tempResponse.equalsIgnoreCase("u")) {
					System.out.println("Please set the Temperature (F)");
					o1.updateTemperature(z.nextInt());
				} else if (tempResponse.equalsIgnoreCase("t")) {
					System.out.println("Please set the Time (minutes)");
					o1.updateTime(z.nextInt());
				} else if (tempResponse.equalsIgnoreCase("q")) {
					System.out.println("Please set the Time (minutes)");
					o1.updateTemperature(0);
					o1.updateTime(0);
					o1.setPowerStatus("OFF");
				}
				System.out.printf("Oven Configs: %s", o1.toString());
			}
		}
	}

	private static void playMusic(Music m) {
		String tempResponse = "";
		if (m.getSong() == null) {
			System.out.println("What Song would you like to Play?");
			tempResponse = z.nextLine();
			m.playMusic(tempResponse);
			System.out.println("Playing Song: " + m.getSong());
		} else {
			System.out.println(
					"Would you like to resume the song [R], pick a new Song [N], adjust volumne [V], or Cancel [X]?");
			tempResponse = z.nextLine();
			while (!tempResponse.equalsIgnoreCase("x")) {
				if (tempResponse.equalsIgnoreCase("r")) {
					m.playMusic();
					tempResponse = "x";
				} else if (tempResponse.equalsIgnoreCase("n")) {
					System.out.println("Would Song would you like to Play?");
					tempResponse = z.nextLine();
					m.playMusic(tempResponse.toLowerCase());
					System.out.println("Playing Song: " + m.getSong());
					tempResponse = "x";
				} else if (tempResponse.equalsIgnoreCase("v")) {
					System.out.println("Would you like to increase [i] or decrease [d] the volume, or Cancel[x]?");
					tempResponse = z.nextLine();
					while (!tempResponse.equalsIgnoreCase("x")) {
						if (tempResponse.equalsIgnoreCase("i")) {
							System.out.printf("Increasing volume from %s ", m.getVolume());
							m.increaseVolume();
							System.out.printf("to %s\n", m.getVolume());
						} else if (tempResponse.equalsIgnoreCase("d")) {
							System.out.printf("Decreasing volume from %s ", m.getVolume());
							m.descreaseVolume();
							System.out.printf("to %s\n", m.getVolume());
						}
						System.out.println("Would you like to increase [i] or decrease [d] the volume, or Cancel[x]?");
						tempResponse = z.nextLine();
					}
				} else {
					System.out.println("Invalid Option... Please Try again");
				}
			}
			printHelpOptions();
		}
	}

	private static void changeTheChannel(Television t) {
		String tempResponse = "";
		if (t.getStation() == null) {
			System.out.println("What Channel would you like see?");
			t.getStations();
			tempResponse = z.nextLine();
			t.changeStation(tempResponse.toLowerCase());
			System.out.println("Currently Watching : " + t.getStation());
		} else {
			System.out.println("Would you like to change the channel[C], adjust volumne [V], or Cancel [X]?");
			tempResponse = z.nextLine();
			while (!tempResponse.equalsIgnoreCase("x")) {
				if (tempResponse.equalsIgnoreCase("c")) {
					System.out.println("Would Channel would you like to see?");
					t.getStations();
					tempResponse = z.nextLine();
					t.changeStation(tempResponse.toLowerCase());
					System.out.println("Currently Watching : " + t.getStation());
					tempResponse = "x";
				} else if (tempResponse.equalsIgnoreCase("v")) {
					System.out.println("Would you like to increase [i] or decrease [d] the volume, or Cancel[x]?");
					tempResponse = z.nextLine();
					while (!tempResponse.equalsIgnoreCase("x")) {
						if (tempResponse.equalsIgnoreCase("i")) {
							System.out.printf("Increasing volume from %s ", t.getVolume());
							t.increaseVolume();
							System.out.printf("to %s\n", t.getVolume());
						} else if (tempResponse.equalsIgnoreCase("d")) {
							System.out.printf("Decreasing volume from %s ", t.getVolume());
							t.descreaseVolume();
							System.out.printf("to %s\n", t.getVolume());
						}
						System.out
								.println("Would you like to change the channel[C], adjust volumne [V], or Cancel [X]?");
						tempResponse = z.nextLine();
					}
				} else {
					System.out.println("Invalid Option... Please Try again");
				}
			}
			printHelpOptions();
		}
	}

	private static enum LOCATIONS {
		LIVING_ROOM, KITCHEN, GARAGE, UPSTAIRS_BEDROOM, DRIVEWAY, BASEMENT
	}

	private static String removeUnderScore(String location) {
		return location.contains("_") ? location.replace("_", " ") : location;
	}

	private static boolean isLocationValid(String room) {
		for (LOCATIONS L : LOCATIONS.values()) {
			if (L.name().equalsIgnoreCase(room)) {
				return true;
			}
		}
		System.out.println("Invalid Location Selected: Please Try again...");
		return false;
	}

}

class DeviceImpl<T extends Device> {
	private T temp = null;

	public void adjustPower(List<T> devices, String room, boolean isOn) {
		for (T e : devices) {
			if (e.getRoom().equalsIgnoreCase(room)) {
				if (e.getPowerStatus().contentEquals(state(isOn))) {
					System.out.printf("%s power is already turned %s\n", e.getRoom(), e.getPowerStatus());
					return;
				}
				e.setPowerStatus(state(isOn));
				temp = e;
			}
		}
		System.out.printf("%s Has Been Adjusted: %s Light is %s\n", temp.getClass().getName(), temp.getRoom(),
				temp.getPowerStatus());
	}

	public void adjustPower(T e, boolean isOn) {
		try {
			if (e.getPowerStatus().contentEquals(state(isOn))) {
				System.out.printf("%s\n", e.toString());
				return;
			}
			e.setPowerStatus(state(isOn));
			System.out.printf("%s's %s Status: %s\n", temp.getRoom(), temp.getClass().getName(), temp.toString());
		} catch (NullPointerException ex) {

		}
	}

	public T previewDevice(List<T> devices, String room) {
		for (T e : devices) {
			if (e.getRoom().equalsIgnoreCase(room)) {
				temp = e;
			}
		}
//		System.out.printf("%s's %s Status: %s\n", temp.getRoom(), temp.getClass().getName(), temp.toString());
		return temp;
	}

	private static String state(boolean state) {
		return state ? "ON" : "OFF";
	}
}
