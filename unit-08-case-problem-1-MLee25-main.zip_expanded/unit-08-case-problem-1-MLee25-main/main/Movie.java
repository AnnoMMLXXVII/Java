/**
 *  Name: Myla Lee
 *  Date: 03/20/2022
 *  Assignment: Unit 8 Case Problem 1
 * 
 *  Purpose (class description): Represent a movie rating for differnt movies.
 * 
 */

public class Movie {
    public static final int EARLIEST_RELEASE_YEAR = 1895;
    public static final int MINIMUM_RUNTIME_IN_MINUTES = 1;
  
    private String title;
    private String director;
    private int releaseYear;
    private int runtimeInMinutes;
  
    /**
     * Parameterized constructor to create a new Movie based on the following
     * parameters
     * 
     * @param title
     * @param director
     * @param releaseYear
     * @param runtimeInMinutes
     */
    public Movie(String title, String director, int releaseYear, int runtimeInMinutes) {
      setTitle(title);
      setDirector(director);
      setReleaseYear(releaseYear);
      setRuntimeInMinutes(runtimeInMinutes);
    }
  
    /**
     * Returns the title of this movie
     * 
     * @return title of this movie
     */
    public String getTitle() {
      return this.title;
    }
  
    /**
     * Sets the title of this movie to the given
     * newTitle
     * 
     * @param newTitle the title to set for this movie
     */
    public void setTitle(String newTitle) {
      this.title = newTitle;
    }
  
    /**
     * Returns the director of this movie
     * 
     * @return director of this movie
     */
    public String getDirector() {
      return this.director;
    }
  
    /**
     * Sets the director of this movie to the given
     * newDirector
     * 
     * @param newDirector the director to set for this movie
     */
    public void setDirector(String newDirector) {
      this.director = newDirector;
    }
  
    /**
     * Returns the release year of this movie
     * 
     * @return releaseYear of this movie
     */
    public int getReleaseYear() {
      return this.releaseYear;
    }
  
    /**
     * Sets the release year of this movie. If the given
     * release year is too early, then it sets it to
     * EARLIEST_RELEASE_YEAR. Otherwise, it sets it to the
     * given release year
     * 
     * @param newReleaseYear the release year to try to set
     *                       for this movie
     */
    public void setReleaseYear(int newReleaseYear) {
      final int EARLIEST_RELEASE_YEAR = 1895;
      if (newReleaseYear < EARLIEST_RELEASE_YEAR) {
        this.releaseYear = EARLIEST_RELEASE_YEAR;
      } else {
        this.releaseYear = newReleaseYear;
      }
    }
  
    /**
     * Returns the runtime in minutes of this movie
     * 
     * @return runtimeInMinutes of this movie
     */
    public int getRuntimeInMinutes() {
      return this.runtimeInMinutes;
    }
  
    /**
     * Sets the runtime in minutes of this movie. If the given
     * runtime is too low, then it sets it to MINIMUM_RUNTIME_IN_MINUTES.
     * Otherwise, it sets it to the given runtime.
     * 
     * @param newRuntimeInMinutes the runtime in minutes to try to
     *                            set for this movie
     */
    public void setRuntimeInMinutes(int newRuntimeInMinutes) {
      if (newRuntimeInMinutes < MINIMUM_RUNTIME_IN_MINUTES) {
        this.runtimeInMinutes = MINIMUM_RUNTIME_IN_MINUTES;
      } else {
        this.runtimeInMinutes = newRuntimeInMinutes;
      }
    }
  
    /**
     * Overrides the toString method to return a string with
     * the details about this movie.
     * 
     * @return string that includes the current values of
     *         key instance variables of this movie.
     */
    @Override
    public String toString() {
      String movieDetails = "Title: " + getTitle() +
          "\nDirector: " + getDirector() +
          "\nReleased: " + getReleaseYear() +
          "\nRuntime (minutes): " + getRuntimeInMinutes();
  
      return movieDetails;
    }
  }