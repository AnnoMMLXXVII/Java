[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7372664&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-8-Case-Problem-1

## Files for this case problem:
- Movie.java
- MovieDemo.java

## Movie.java Requirements
Update the following in the Movie class:
- Add new class constant
  - Array of possible movie ratings (G, PG, PG-13, R, NC-17, Not Rated)
- Add new instance variable
  - Name it ratingIndex
  - It will be an int that corresponds to the index of the movie's rating based on the movie ratings array
- Add/update methods
  - Update constructor method
    - Add a parameter for the new instance variable
    - Set the value using the appropriate setter method
  - Create a setRatingIndex method
    - It will take in an int for the index of a movie rating as a parameter
    - If the value is too high or too low based on the array of possible movie ratings, set the movie rating instance variable to the one corresponding to "Not Rated"
    - Otherwise, set the movie rating index instance variable as the given value
  - Create a getRatingAsString method
    - It will return the movie rating based on the value of the movie rating instance variable
    - It will use the ratingIndex to return the corresponding value from the movie ratings array
  - Update the toString method
    - Add on the information about the movie rating (only need the string version not the index)
    - Follow the same format that is used so far

## MovieDemo.java Requirements
Update the following in the MovieDemo class:
- Create a method to ask for the movie rating
  - Be sure to refer to the existing methods as a model
  - Take in a Scanner as a parameter for user input
  - Ensure you display the list of options to the user to select from based on the movie rating array from the Movie class
    - To make it easy to validate a proper selection, it is recommended to include a number/letter before each choice that the user is to enter
    - If you are expecting the user to enter a number, be sure to start with the number 1, for a more natural user experience
    - Continue to ask for the movie rating until a valid option is selected
    - Return the index of the movie rating the user selected (Remember that the index starts at 0)
- Update the askForMovie method to also call the method you made to ask for the movie rating
  - Be sure to save the result to a variable as well
  - Update the return statement to include the argument for the index of the movie rating
- Create a displayMovies method
  - It takes in an array of Movie objects as a parameter
  - It uses a for loop to print the details about each Movie
  - To be efficient, be sure to call the displayMovie method in your loop
- Main method
  - Remove the lines of code that currently create Movies and display them one at a time
  - Declare an array that will hold 3 Movie objects
  - Use a for loop to fill the array
    - To be efficient, be sure to use the helper method to create the movie objects in the loop
    - To improve readability, you may want to include a print statement so the user knows what movie number they are on and print a blank line to separate the movies
  - Call the displayMovies method that takes in the array of Movie objects you created
    - Be sure to close the Scanner still
