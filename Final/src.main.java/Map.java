import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Map {

	private static House[][] houses;
	private static final String FILE_NAME = "house.txt";

	public static void loadHouse() {
		houses = new House[4][4];
		try (Scanner z = new Scanner(new FileReader(new File(FILE_NAME)))) {
			int k = 0;
			while(z.hasNextLine()) {
				String line = z.nextLine();
				String[] split = line.split(",");
				for(int i = k; i < houses.length; i++) {
					for (int j = 0; j < houses[i].length;j++) {
						houses[i][j] = new House(split[j].split(":")[0].trim(),
								Integer.parseInt(split[j].split(":")[1].trim()));
					}
				}
				k++;
			}
			System.out.println(Arrays.deepToString(houses));
		} catch (FileNotFoundException e) {
			System.err.printf("Error loading the house data: %s\n", e.getMessage());
		}
	}
	
	public static void upgradeHouses(int row, int column) {
		for(int i = 0; i < houses[row].length; i++) {
			houses[row][i].upgrade();
		}
		System.out.println(Arrays.deepToString(houses));
	}
}
