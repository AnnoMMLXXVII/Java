import java.util.Scanner;

/**
 * Name: James Sekcienski Date: 11/7/21 Assignment: Unit 10 Case Problem 2
 * 
 * Purpose (Class Description): Allows the user to place an order consisting of
 * an appetizer, entree, two sides, and dessert.
 */
public class MenuDemo {
	public static void main(String[] args) {
		// Creates the available menu items
		Appetizer[] appetizers = createAppetizers();
		Entree[] entrees = createEntrees();
		Side[] sides = createSides();
		Dessert[] desserts = createDesserts();

		System.out.println("Welcome to the Food Lovers Restaurant.");
		System.out.println("Our meals include one appetizer, one entree, two sides, and one dessert.");

		Scanner input = new Scanner(System.in);
		MenuItem[] mealItems = askForMealItems(input, appetizers, entrees, sides, desserts);
		input.close();

		System.out.println("You have ordered the following: ");
		displayMenuItems(mealItems);
	}

	/**
	 * Creates array of appetizers and returns them
	 * 
	 * @return Array of appetizers
	 */
	public static Appetizer[] createAppetizers() {
		Appetizer[] appetizers = new Appetizer[3];

		// TODO Create and add 3 appetizers (you can increase the number if you want)
		appetizers[0] = new Appetizer("Frickles", 6.99);
		appetizers[1] = new Appetizer("Expensive Frickles", 12.99);
		appetizers[2] = new Appetizer("Super Expensive Frickles", 20.99);

		return appetizers;
	}

	/**
	 * Creates array of entrees and returns them
	 * 
	 * @return Array of entrees
	 */
	public static Entree[] createEntrees() {
		Entree[] entrees = new Entree[4];

		// TODO Create and add 4 entrees (you can increase the number if you want)
		entrees[0] = new Entree("Stuned Walobean", 2.99);
		entrees[1] = new Entree("Super Stuned Walobean", 17.99);
		entrees[2] = new Entree("Omega Stuned Walobean", 27.99);
		entrees[3] = new Entree("Super Omega Stuned Walobean", 39.99);
		return entrees;
	}

	/**
	 * Creates array of sides and returns them
	 * 
	 * @return Array of sides
	 */
	public static Side[] createSides() {
		Side[] sides = new Side[4];

		// TODO Create and add 4 sides (you can increase the number if you want)
		sides[0] = new Side("Small Warm and Brown", 3.99);
		sides[1] = new Side("Small (not-so) Warm and Brown", 7.99);
		sides[2] = new Side("Small Warm and (not-so) Brown", 12.99);
		sides[3] = new Side("Small Only Brown", 16.99);
		return sides;
	}

	/**
	 * Creates array of desserts and returns them
	 * 
	 * @return Array of desserts
	 */
	public static Dessert[] createDesserts() {
		Dessert[] desserts = new Dessert[3];

		// TODO Create and add 3 desserts (you can increase the number if you want)
		desserts[0] = new Dessert("Platter of Ketchup", 4.99);
		desserts[1] = new Dessert("Smatter of Mustard", 4.99);
		desserts[2] = new Dessert("WhatsAMatter with Mayo", 4.99);
		return desserts;
	}

	/**
	 * Asks user for the menu items they would like to order for their meal
	 * 
	 * @param input      The Scanner to get console input
	 * @param appetizers Array of available appetizers
	 * @param entrees    Array of available entrees
	 * @param sides      Array of available sides
	 * @param desserts   Array of available desserts
	 * @return Array of menu items that were selected for the meal
	 */
	public static MenuItem[] askForMealItems(Scanner input, Appetizer[] appetizers, Entree[] entrees, Side[] sides,
			Dessert[] desserts) {
		MenuItem[] mealItems = new MenuItem[5];

		System.out.println("Appetizer");
		mealItems[0] = askForMenuItemSelection(input, appetizers);

		System.out.println("Entree");
		mealItems[1] = askForMenuItemSelection(input, entrees);

		System.out.println("Side 1");
		mealItems[2] = askForMenuItemSelection(input, sides);

		System.out.println("Side 2");
		mealItems[3] = askForMenuItemSelection(input, sides);

		System.out.println("Dessert");
		mealItems[4] = askForMenuItemSelection(input, desserts);

		return mealItems;
	}

	/**
	 * Takes in an array of menu items and returns the user-selected Item
	 * @param input     The Scanner to get console input
	 * @param menuItems The Array of MenuItems to select from
	 * @return
	 */
	public static MenuItem askForMenuItemSelection(Scanner input, MenuItem[] menuItems) {
		if (input == null) {
			throw new NullPointerException("Scanner object is null");
		}
		if (menuItems == null || menuItems.length == 0) {
			throw new IllegalArgumentException("There are no menu Items.");
		}
//		System.out.printf("Please Choose the Type of Item (%d-%d):\n", 1, menuItems.length);
		for (int i = 0; i < menuItems.length; i++) {
			System.out.printf("(%d)-%s\n", (i + 1), menuItems[i]);
		}
		int response = (input.nextInt() - 1);
		boolean isCorrect = (response > -1 && response <= (menuItems.length - 1)) ? true : false;
		while (!isCorrect) {
			System.out.println("Please try enter the choices again.");
			response = (input.nextInt() - 1);
			isCorrect = (response > -1 && response <= (menuItems.length - 1)) ? true : false;
		}
		return menuItems[response];
	}

	/**
	 * Takes in an array of menu items and prints them out.
	 * 
	 * @param menuItems The array of menu items to display
	 */
	public static void displayMenuItems(MenuItem[] menuItems) {
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem);
		}
	}
}
