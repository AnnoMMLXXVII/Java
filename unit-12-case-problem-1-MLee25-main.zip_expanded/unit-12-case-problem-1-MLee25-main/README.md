[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7532498&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-12-Case-Problem-1

In this lab, you will be modifying the Movie program that you made in Unit 8 to use exception handling to protect your program from crashing and prevent invalid data.

## Files for this case problem:
For this case problem, you will use your code from the Unit 8 Case Problem 1 as your starter code.  You will need to copy the code from your Unit 8 Case Problem 1 assignment to this assignment to update
- Movie.java
- MovieDemo.java
- NegativeMovieRuntimeException.java

## NegativeMovieRuntimeException.java
- Make this a child class of Exception
- Define a default constructor method
  - It will call the super constructor with the following message: "Movie runtime cannot be negative."

## Movie.java Requirements
You should already have this complete from the original assignment.  You are now going to make some adjustments for this class:
- Make your setter method for runtime throw a NegativeMovieRuntimeException when the value of the parameter is less than 0
- Make your setRating method throw an ArrayIndexOutOfBoundsException instead of setting the movie rating instance variable to -1
- Change getRatingAsString to only return the corresponding value from the movie ratings array, as the setter method will now force the index to be valid
- Since some setter methods are throwing Exceptions, you will need to update the constructor method to also throw those Exceptions since it calls the setter methods

These updates will now make it so a Movie cannot be created with invalid data.  As an extension, you are welcome to add an Exception to the release year to ensure it cannot be a year before the first movie was released.

## MovieDemo.java Requirements
You should already have this complete from the original assignment.  You are now going to make some 
adjustments for this class:
- Any methods asking for numeric input, must now include Exception handling to ensure the
 program does not crash if something other than a number is entered.  
  - Similar to invalid values, you will need to provide the user with an error message and 
  it needs to repeat until a valid value is entered.
  - If an Exception is caught, make sure you remember to still clear the keyboard buffer before it repeats.
- You will now need a try-catch when calling the Movie constructor method.
  - You will need to alert the user to the type of error if one is caught
  - You may either say you were unable to create a Movie object if an Exception is caught at this point 
  and use null OR you can repeat the prompts until a Movie object is successfully created
    - Please note that if an Exception is caught here that means there is an error with how
     you are validating the user input and/or handling index for the movie rating

With these updates, you should now be able to enter in any values you want and your program will no longer crash.
