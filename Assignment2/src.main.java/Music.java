import java.util.List;

public class Music extends Entertainment {

	private String song;
	private List<String> songs;

	public Music(String powerStatus, String room) {
		super(powerStatus, room);
		songs = retrieveContent("song.txt");
	}

	/**
	 * @return the song
	 */
	public String getSong() {
		if (getPowerStatus().equalsIgnoreCase("OFF")) {
//			System.out.println("Music Device is turned off!");
			return "No Songs Playing";
		}
		return song;
	}

	/**
	 * @param song the song to set
	 */
	public void setSong(String song) {
		this.song = song;
	}

	public void playMusic(String requestedSong) {
		if (songs.contains(requestedSong)) {
			setSong(requestedSong);
			setVolume(12);
		} else {
			System.out.printf("Unable to played request song: %s\n", requestedSong);
		}
	}

	public void playMusic() {
		if (getSong() == null || getSong().equalsIgnoreCase("")) {
			System.out.println("There are not songs current in the playlist");
			return;
		}
		if (getPowerStatus().equalsIgnoreCase("OFF")) {
			System.out.println("Music Device is turned off!");
			return;
		}
		setSong(getSong());
		setVolume(12);
		System.out.println("Playing Song : " + getSong());
	}

	public void stopMusic() {
		setSong(null);
		System.out.println("Song has stopped playing");
	}

	@Override
	public void increaseVolume() {
		if (getVolume() > 24) {
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
		return String.format("Music %s Song=%s", super.toString(),
				(getSong() == null) ? "No Song" : getSong());
	}

//	private String name;
//
//	public Music() {
//		System.out.println("Welcome to the Music System");
//	}
//

}
