import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 */
public class PlaylistDemo {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Playlist userPlaylist = askForPlaylist(input);
		System.out.println(userPlaylist);

		// TODO: Add on to this based on the requirements in the README for this assignment
		System.out.println("Attempting to Save the Playlist...");
		boolean isSaved = savePlaylist(userPlaylist);	// call savePlaylist method w/ userPlaylist as parameter
		if (isSaved == true) {	// checks if the saved boolean is true
			System.out.println("playlist Successfully saved");
			System.out.println("Attempting to Open the Playlist...");
			Playlist newPlayList = openPlaylist(userPlaylist.getName()); 		// call openPlaylist method w/ name of playlist 
			if (newPlayList == null) {
				throw new NullPointerException("Unable to open new Playlist: Null object");		// throw null pointer exception
			} else {
				System.out.println("playlist Successfully opened");
				System.out.println(userPlaylist);
			}
		} else {
			System.out.println("playlist unSuccessfully saved");
		}
		
	}

	/**
	 * @param input The Scanner to get input from the console
	 * @return the playist created based on user input
	 */
	public static Playlist askForPlaylist(Scanner input) {
		String name = askForStringValue(input, "Enter playlist name: ");
		ArrayList<Song> songs = askForSongs(input);
		return new Playlist(name, songs);
	}

	/**
	 * @param input The Scanner to get input from the console
	 * @return the list of songs created based on user input
	 */
	public static ArrayList<Song> askForSongs(Scanner input) {
		ArrayList<Song> songs = new ArrayList<Song>();
		boolean isAddingSongs = true;
		do {
			isAddingSongs = askIfAddingSong(input);
			System.out.println();

			if (isAddingSongs) {
				Song song = askForSong(input);
				System.out.println();
				songs.add(song);
			}
		} while (isAddingSongs);
		return songs;
	}

	/**
	 * Requests information from the user needed to create a song. Returns the song
	 * created based on the user input.
	 * 
	 * @param input The Scanner to get input from the console
	 * @return the song created based on user input
	 */
	public static Song askForSong(Scanner input) {
		String title = askForStringValue(input, "Enter the title of the song: ");
		String artist = askForStringValue(input, "Enter the artist of the song: ");
		return new Song(title, artist);
	}

	/**
	 * Asks the user if they would like to add another song to the playlist and
	 * repeats until a valid response is provided. Returns a boolean representing
	 * whether the user would like to add another song to the playlist or not.
	 * 
	 * @param input The Scanner to get input from the console
	 * @return boolean representing whether the user would like to add another song
	 *         to the playlist or not
	 */
	public static boolean askIfAddingSong(Scanner input) {
		while (true) {
			String continueResponse = askForStringValue(input, "Would you like to add a song to the playlist (y/n)? ");
			if (continueResponse.equalsIgnoreCase("y")) {
				return true;
			} else if (continueResponse.equalsIgnoreCase("n")) {
				return false;
			} else {
				System.out.println("Invalid response.  Please enter 'y' for YES or 'n' for NO");
			}
		}
	}

	/**
	 * Outputs the prompt to the user to request information. Reads in the next line
	 * of information and returns it.
	 * 
	 * @param input  The Scanner to get input from the console
	 * @param prompt the prompt to output to the user for the requested information
	 * @return the String value entered by the user
	 */
	public static String askForStringValue(Scanner input, String prompt) {
		System.out.println(prompt);
		return input.nextLine();
	}

	/**
	 * Outputs the prompt to the user to request information. Tries to read in the
	 * next integer value. It will provide an error message and repeat the prompt if
	 * the next value is not an integer, if it is less than the specified minValue,
	 * or if it is greater than the specified maxValue. Returns the valid integer
	 * value entered by the user.
	 * 
	 * @param input    The Scanner to get input from the console
	 * @param prompt   the prompt to output to the user for the requested
	 *                 information
	 * @param minValue the lowest valid integer value
	 * @param maxValue the highest valid integer value
	 * @return the valid integer value entered by the user
	 */
	public static int askForIntBounded(Scanner input, String prompt, int minValue, int maxValue) {
		System.out.println(prompt);
		int answer;
		while (true) {
			try {
				answer = input.nextInt();
				input.nextLine();
				if (answer < minValue) {
					System.out.println("Value must not be less than " + maxValue);
				} else if (answer > maxValue) {
					System.out.println("Value must not exceed " + maxValue);
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("You need to enter a number.");
				input.nextLine();
			}
		}
		return answer;
	}

	/**
	 * 
	 * @param playlist
	 * @return
	 */
	public static boolean savePlaylist(Playlist playlist) {
		boolean isSaved = false;
		String fileName = playlist.getName(); // Store incoming playList name into a variable
		File file = new File(fileName + ".txt");
		if (file.exists()) { // Checking to see if fileName.txt already exists
			try {
				FileWriter fileWriter = new FileWriter(file, false); // new FileWRiter with False flag --> Overwrite the file
				for (int i = 0; i < playlist.getSongs().size(); i++) {
					fileWriter.write(playlist.getSongs().get(i).getTitle() + "\t"
							+ playlist.getSongs().get(i).getArtist() + "\n");
					// example output line : Title    Artist
				}
				// clean up process
				fileWriter.flush();
				fileWriter.close();
				isSaved = true;
			} catch (IOException e) {
				isSaved = false;
				e.printStackTrace();
			}
		} else {					// when file does NOT exist
			try {
				File newFile = new File(fileName + ".txt");
				FileWriter fileWriter = new FileWriter(newFile, false); // new FileWRiter with False flag --> Overwrite the file
				for (int i = 0; i < playlist.getSongs().size(); i++) {
					fileWriter.write(playlist.getSongs().get(i).getTitle() + "\t"
							+ playlist.getSongs().get(i).getArtist() + "\n");
					// example output line : Title    Artist
				}
				// clean up process
				fileWriter.flush();	// clean out the leaking bytes
				fileWriter.close();	// closes the object or stream
				isSaved = true;
			} catch (IOException e) {
				isSaved = false;
				e.printStackTrace();
			}
		}
		return isSaved;
	}
	
	/**
	 * 
	 * @param playListName
	 * @return
	 */
	public static Playlist openPlaylist(String playListName) {
		Playlist playList = null;
		String fileName = playListName;
		try {
			FileReader fileReader = new FileReader(fileName+".txt"); // looks for a file to read
			Scanner scanner = new Scanner(fileReader);					/// scanner will allow the file to be read
			ArrayList<Song> songs = new ArrayList<Song>();			// ArrayList to hold all songs from that read file
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] titleAndArtist = line.split("\t");			// creates a small array/list based on the delimeter (\t)
				Song song = new Song(titleAndArtist[0], titleAndArtist[1]);		// new song w/ the values in the array
				songs.add(song);			// add single song into the ArrayList songs (from line 219)
			}
			scanner.close();		// clean up process
			playList = new Playlist(playListName, songs);	// new Playlist object w/ the two param (playlist name, list of songs)
		} catch (IOException e) {
			System.out.println("Unable to open the playlist file: "+playListName+"\n"+e.getMessage());
			e.printStackTrace();
		}
		return playList;
	}
}
