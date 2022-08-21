import java.util.ArrayList;

public class Playlist {
	private String name;
	private ArrayList<String> songs;

	public Playlist(String name, ArrayList<String> songs) {
		setName(name);
		setSongs(songs);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<String> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		String result = "Playlist: " + name;
		for (int i = 0; i < songs.size(); i++) {
			result += "\n\t(" + (i + 1) + ") " + songs.get(i);
		}
		return result;
	}
}
