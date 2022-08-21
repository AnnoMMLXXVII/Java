package assignments.movies;

import java.util.Scanner;

public class MovieDemo {

	public static void main(String[] args) {
		new MovieDemo();
	}

	public MovieDemo() {

		Movie[] movies = addNMovieToArray(25);
		displayMovies(movies);
//		askForMovieRating(new Scanner(System.in));
	}

	private int askForMovieRating(Scanner z) {
		System.out.println("Please enter a number for the desired rating: ");

		for (int i = 0; i < Movie.getRatings().length; i++) {
			System.out.printf("(%d)-(%s),", (i + 1), Movie.getRatings()[i]);
		}
		System.out.println();
		boolean isCorrectResponse = false;
		int response = z.nextInt();
		while (!isCorrectResponse) {
			if (response > 0 && response < Movie.getRatings().length) {
				isCorrectResponse = true;
			}
			System.out.println("Please enter a rating: ");
			response = z.nextInt();
		}
		z.close();
		return response - 1;
	}

	private void displayMovie(Movie movie) {
		System.out.printf("%s,", movie.toString());
	}

	private void displayMovies(Movie[] movies) {
		int i = 0;
		for (Movie m : movies) {
			System.out.printf("%d-", i);
			displayMovie(m);
			i++;
		}
	}

	private Movie[] addNMovieToArray(int n) {
		Movie[] m = new Movie[n];
		int ratingIndex = 0;
		for (int i = 0; i < m.length; i++) {
			if ((i > 0) && (i % 5 == 0)) {
				ratingIndex = 0;
			}
			m[i] = new Movie(ratingIndex);
			ratingIndex++;
		}
		return m;
	}

}
