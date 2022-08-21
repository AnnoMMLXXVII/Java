import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Class that will be the Demo of the Pokmeon Class and demonstrate reading a file, converting it to a Data Structure, and printing its contents 
 * 
 *
 */
public class PokemonDemo {

	private static Scanner scanner = new Scanner(System.in); // static datafield of a Scanner

	/**
	 * Main method for the Java Program.
	 * @param args
	 */
	public static void main(String[] args) {
		String input = askForFilePath();
		while(input == null || input.equals("")) {
			System.out.println("Sorry. Input is not correct. Must not be blank. Try again.");
			input = askForFilePath();
		}
		System.out.println("Attempting to open the file...");
		ArrayList<Pokemon> pokemons = openDataFile(input);
		displayData(pokemons);
	}

	/**
	 * Method that will just print the Pokemon Data from the parameter
	 * 
	 * @param pokemons: ArrayList
	 * @return
	 */
	public static void displayData(ArrayList<Pokemon> pokemons) {
		for (int i = 0; i < pokemons.size(); i++) {
			System.out.println(pokemons.get(i).toString());
		}
	}

	/**
	 * Method that returns the user's response
	 * 
	 * @return String
	 */
	private static String askForFilePath() {
		System.out.println("Please Enter the Name of the File.");
		return scanner.next();
	}

	/**
	 * Driving method that will open the data and convert the data from the file
	 * into the Data Structure: ArrayList The array list will be null at
	 * initialization and then created prior to reading reading each line of the
	 * file
	 * 
	 * @param file
	 * @return ArrayList
	 */
	private static ArrayList<Pokemon> openDataFile(String file) {
		ArrayList<Pokemon> pokemons = null;
		try {
			FileReader fileReader = new FileReader(file + ".csv"); // looks for a file to read
			System.out.println("Data file Successfully opened");
			Scanner scanner = new Scanner(fileReader);
			pokemons = new ArrayList<>(); // ArrayList to house the Pokemons
			while (scanner.hasNextLine()) {				// Loop condition that will allow the fileReader and scanner to keep reading the file
				String line = scanner.nextLine();		// pull single line into an array using split
				String[] pokemonInfo = line.split(","); // creates a small array/list based on the delimeter (,)
					// 	new Pokemon w/ the values in the array (pokemonInfo)
				Pokemon pokemon = new Pokemon(
						Integer.parseInt(pokemonInfo[0]), // Id to Int
						pokemonInfo[1], // Name
						pokemonInfo[2], // Primary Type
						pokemonInfo[3], // Secondary Type
						Integer.parseInt(pokemonInfo[4]), // Total to Int
						Integer.parseInt(pokemonInfo[5]), // HP
						Integer.parseInt(pokemonInfo[6]), // Attack
						Integer.parseInt(pokemonInfo[7]), // Defense
						Integer.parseInt(pokemonInfo[8]), // Sp. Attk
						Integer.parseInt(pokemonInfo[9]), // Sp. Def
						Integer.parseInt(pokemonInfo[10]), // Speed
						Integer.parseInt(pokemonInfo[11]), // Generation Number
						pokemonInfo[12]); 				   // Legendary
				pokemons.add(pokemon); 					// add single pokemon into the pokemons list (line 60)
			}
			scanner.close(); // Closing the Scanner
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open the Pokemon file: " + file + "\n" + e.getMessage());
			pokemons = null;
			e.printStackTrace();
		}
		return pokemons;
	}
}
