
import java.util.Scanner;

/**
 * Name:
 * Date:
 * Assignment: Unit 8 Case Problem 1
 * 
 * Purpose (Class Description): Used to create Movie objects based on user input and display
 * the details of the created Movie objects
 */
public class MovieDemo {

  public static void main(String[] args) throws NegativeMovieRuntimeException {
    Scanner input = new Scanner(System.in);
    
    // Create movies based on user input
    System.out.println("Movie 1");
    Movie movie1 = askForMovie(input);
    System.out.println();

    System.out.println("Movie 2");
    Movie movie2 = askForMovie(input);
    System.out.println();

    input.close(); // No longer getting user input, so it can be closed

    // Display movies
    System.out.println("Movies from User");
    displayMovie(movie1);
    displayMovie(movie2);
  }

  /**
   * Returns a Movie created based on input received
   * from the given Scanner
   * 
   * @param input Scanner that is reading console input
   * @return the Movie created based on input for each
   * of the instance variables
 * @throws NegativeMovieRuntimeException 
   */
  public static Movie askForMovie(Scanner input) throws NegativeMovieRuntimeException {
    String title = askForTitle(input);
    String director = askForDirector(input);
    int releaseYear = askForReleaseYear(input);
    int runtimeInMinutes = askForRuntimeInMinutes(input);

    return new Movie(title, director, releaseYear, runtimeInMinutes);
  }

  /**
   * Returns the title for a movie that is read from the
   * input
   * 
   * @param input Scanner that is reading console input
   * @return title that is read from the next line of the
   * input
   */
  public static String askForTitle(Scanner input) {
    System.out.println("Enter the title of the movie: ");
    return input.nextLine();
  }

  /**
   * Returns the director for a movie that is read from the
   * input
   * 
   * @param input Scanner that is reading console input
   * @return name of director that is read from the next line 
   * of the input
   */
  public static String askForDirector(Scanner input) {
    System.out.println("Enter the name of the director of the movie: ");
    return input.nextLine();
  }

  /**
   * Returns the release year for a movie that is read from the
   * input.  It will continue to repeat until a valid
   * release year is read from the input
   * 
   * @param input Scanner that is reading console input
   * @return release year that is valid from the input
   */
  public static int askForReleaseYear(Scanner input) {
    int releaseYear = Movie.EARLIEST_RELEASE_YEAR - 1;
    while (true) {
      System.out.println("Enter the release year of the movie: ");
      releaseYear = input.nextInt();
      input.nextLine(); // Clear the keyboard buffer

      if (releaseYear >= Movie.EARLIEST_RELEASE_YEAR) {
        break;
      } else {
        System.out.println("Invalid release year.  The earliest it can be is " + Movie.EARLIEST_RELEASE_YEAR);
      }
    }
    
    return releaseYear;
  }

  /**
   * Returns the runtime in minutes for a movie that is read 
   * from the input.  It will continue to repeat until a valid
   * runtime in minutes is read from the input
   * 
   * @param input Scanner that is reading console input
   * @return runtime in minutes that is valid from the input
   */
	public static int askForRuntimeInMinutes(Scanner input) {
		int runtimeInMinutes = Movie.MINIMUM_RUNTIME_IN_MINUTES - 1;
		while (true) {
			System.out.println("Enter the runtime in minutes of the movie: ");
			try {
				runtimeInMinutes = input.nextInt();
				input.nextLine(); /* Clear the keyboard buffer */
				if (runtimeInMinutes >= Movie.MINIMUM_RUNTIME_IN_MINUTES) {
					break;
				} else {
					throw new NegativeMovieRuntimeException();
				}
			} catch (NegativeMovieRuntimeException negativemovieruntimeexception) {
				System.out.println(negativemovieruntimeexception);
			}
		}
		return runtimeInMinutes;
	}

  /**
   * Prints the details of the given movie and a blank
   * line after it.
   * 
   * @param aMovie the movie to display the details of
   */
  public static void displayMovie(Movie aMovie) {
    System.out.println(aMovie);
    System.out.println();
  }  
}