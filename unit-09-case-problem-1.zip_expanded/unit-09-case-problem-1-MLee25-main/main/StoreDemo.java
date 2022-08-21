import java.util.*;

/**
 * Name: James Sekcienski
 * Date: 10/30/21
 * Assignment: Unit 9 Case Problem 1
 * 
 * Purpose (Class Description): Allows the user to view and sort the products of
 * a store.
 */
public class StoreDemo {
  public enum SortChoices {
    ID, NAME, COST, PRICE
  };

  public static final int QUIT = 0;

  /**
   * Takes in an array of arguments
   * Creates the initial products for a store.
   * Allows the user to interact with the products in the store that is created.
   */
  public static void main(String[] args) {
    // Creates the store with an initial list of products
    ProductList storeProducts = new ProductList();

    // Prints out the list of products in the store to start
    storeProducts.getProductsDisplay();

    // Continues to ask user how they want to sort the list until they decide to
    // quit
    Scanner input = new Scanner(System.in);
    int userResponse = 0;
    do {
      userResponse = askUserHowToDisplay(input);
      handleUserDisplayResponse(userResponse, storeProducts);
    } while (userResponse != QUIT);
    input.close();
  }

  /**
   * Shows the user options for how to sort and display the products.
   * Continues to ask the user how they want the products displayed until a valid
   * choice is provided.
   * 
   * @param input The Scanner that gets input from the console
   * @return the option for how the user wants to sort the product
   */
  public static int askUserHowToDisplay(Scanner input) {
    // Display menu of choices to user
    int numOfChoices = SortChoices.values().length;
    System.out.println("How would you like to display the products for this store?");
    for (int i = 0; i < numOfChoices; i++) {
      System.out.println("\t" + (i + 1) + ": " + SortChoices.values()[i]);
    }
    System.out.println("Enter " + QUIT + " to QUIT");

    // Get user response. Repeat until a valid input is provided
    int userResponse = 0;
    do {
      String answer = input.nextLine();
      try {
        int numericAnswer = Integer.parseInt(answer);
        if (numericAnswer != QUIT && (numericAnswer <= 0 || numericAnswer > numOfChoices)) {
          System.out.println("You must enter a choice from the menu or " + QUIT + " to quit.  Try again.");
        } else {
          userResponse = numericAnswer;
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("You must enter a number.  Try again.");
      }
    } while (true);

    return userResponse;
  }

  /**
   * This will sort and display the products in the ProductList based on the
   * displayChoice. If it is QUIT, it displays a quit message.
   * 
   * @param displayChoice Value corresponds to index of the SORT_CHOICE for how to
   *                      display the products. A value of QUIT means display the
   *                      exit message
   * @param products      The ProductList that is being sorted and displayed based
   *                      on the displayChoice
   */
  public static void handleUserDisplayResponse(int displayChoice, ProductList products) {
    if (displayChoice == QUIT) {
      System.out.println("Thank you for taking a look at the products!");
    } else {
      SortChoices[] choices = SortChoices.values();
      switch (choices[(displayChoice - 1)]) {
        case ID:
          products.sortProductsById();
          break;
        case NAME:
          products.sortProductsByName();
          break;
        case COST:
          products.sortProductsByCost();
          break;
        case PRICE:
          products.sortProductsByPrice();
          break;
      }

      products.getProductsDisplay();
      System.out.println();
    }

  }
}
