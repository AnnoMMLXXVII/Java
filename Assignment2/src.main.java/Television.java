import java.util.List;

public class Television extends Entertainment {

	private String station;
	private List<String> stations;

	public Television(String powerStatus, String room) {
		super(powerStatus, room);
		stations = retrieveContent("channel.txt");
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		if (getPowerStatus().equalsIgnoreCase("OFF")) {
			return "Television is turned off!";
		}
		return station;
	}

	/**
	 * print the stations
	 */
	public void getStations() {
		System.out.println("Stations Preview: ");
		for (String s : stations) {
			System.out.printf("%s\n", s);
		}
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	public void changeStation(String requestedStation) {
		if (requestedStation == null) {
			setStation(null);
			return;
		}

		if (stations.contains(requestedStation)) {
			setStation(requestedStation);
			setVolume(25);
		} else {
			System.out.printf("Unable to change to requested station: %s\n", requestedStation);
		}
	}

	@Override
	public void increaseVolume() {
		if (getVolume() > 49) {
			// Do nothing
			return;
		}
		int vol = getVolume(); // temporary volume
		setVolume(++vol); // increase volume by 1
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Television %s Song=%s", super.toString(),
				(getStation() == null) ? "Nothing On" : getStation());
	}

	// private String name;

//	public Television() {
//		System.out.println("Welcome to the Television System");
//	}
//
//	public void TV_ON() {
//		SwitchOnTV();
//	}
}
