package assignments.movies;

public class Movie {
	private static final String[] RATINGS = { "G", "PG", "PG-13", "R", "NC-17", "Not Rated" };
	private int ratingIndex = 0;

	public Movie(int ratingIndex) {
		this.ratingIndex = ratingIndex;

	}

	/**
	 * @return the ratingIndex
	 */
	public String getRating() {
		return RATINGS[ratingIndex];
	}

	/**
	 * @param ratingIndex the ratingIndex to set
	 */
	public void setRating(int ratingIndex) {
		if (ratingIndex < 0 || ratingIndex > RATINGS.length - 1) {
			this.ratingIndex = (RATINGS.length - 1);		// "Not Rated" if the ratingIndex is Less than Zero or Greater than 5;
		}
		this.ratingIndex = ratingIndex;
	}
	
	public static String[] getRatings() {
		return RATINGS;
	}

	@Override
	public String toString() {
		return "[" + getRating() + "]";
	}
	
	
}
