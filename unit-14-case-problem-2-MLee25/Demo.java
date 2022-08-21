import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
//		new PokemonFrame(readDataFile());
		randomNumber(12345);
		System.out.println();
		randomNumber(54321);
		System.out.println();
		randomNumber(0);
	}

	public static void randomNumber(int seed) {
		System.out.println("Seed: " + seed);
		Random r = new Random(seed);
		String res = "";
		int value = r.nextInt();
		int i = 0;
		while (!res.trim().contains("H H H")) {
			if (value <= 0) {
				res += "H ";
			} else {
				res += "T ";
			}
			value = r.nextInt();
			System.out.printf("[%d] - %s\n", i++, res.trim());
		}
		System.out.println(">>>>> " + res.trim());
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
	private static List<Pokemon> readDataFile() {
		List<Pokemon> pokemons = null;
		try {
			FileReader fileReader = new FileReader("Pokemon.csv"); // looks for a file to read
			Scanner scanner = new Scanner(fileReader);
			pokemons = new ArrayList<>(); // ArrayList to house the Pokemons
			while (scanner.hasNextLine()) { // Loop condition that will allow the fileReader and scanner to keep reading
											// the file
				String line = scanner.nextLine(); // pull single line into an array using split
				String[] pokemonInfo = line.split(","); // creates a small array/list based on the delimeter (,)
				// new Pokemon w/ the values in the array (pokemonInfo)
				Pokemon pokemon = new Pokemon(pokemonInfo[1], // Name
						pokemonInfo[2], // Primary Type
						pokemonInfo[3], // Secondary Type
						Pokemon.convertGenNumberToText(Integer.parseInt(pokemonInfo[11])), // Generation Number
						pokemonInfo[12]); // Legendary
				pokemons.add(pokemon); // add single pokemon into the pokemons list
			}
			scanner.close(); // Closing the Scanner
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open the Pokemon file: Pokemon\n" + e.getMessage());
			pokemons = null;
			e.printStackTrace();
		}
		return pokemons;
	}

}
