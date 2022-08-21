[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7317629&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-9-Case-Problem-2

For this case problem, you will be creating a Memory Game.  In memory, there is a grid which is made of a shuffled set of pairs of cards.  
Usually you would play against at least one other person to try to find the most matches.  However, for this version, you are going to keep 
track of how many rounds it takes the user to find all of the matches.

## Files for this case problem:
- Card.java
- Grid.java
- GameDemo.java

## Card.java Requirements
Create a class called Card that has the following:
- enum for card values
  - Create an enum to represent the possible values for cards in your game.  
  - You get to pick the theme.
  - Include at least 10 possible values
- Instance variables
  - value
  - isShown
- Methods
  - Constructor method
    - Include a parameter for the value of the card
    - Set the value of the card to the given value using the appropriate setter method
    - Set isShown to false using the appropriate method
  - Getter/setter methods for the value
  - Getter method for isShown
  - show() will set isShown to true
  - hide() will set isShown to false

## Grid.java Requirements
Create a class Grid that does the following:
- Class constant
  - NUM_OF_PAIRS for the number of pairs of cards in a game (Must be at least 2)
    - If this is 2, then there will be 4 Cards total in your grid
    - If this is 5, then there will be 10 Cards total in your grid
    - You are not required to have Cards for every single value in your enum.  The enum just represents the possible Card values.
    - *Your program still needs to work when this number is changed
- Instance variable
  - cards (this will be a 2-D array of Card objects)
- Methods
  - Constructor method
    - No parameter
    - Set the cards using the appropriate method
    - Shuffle the cards using the appropriate method
  - generateCards method
    - Initialize cards to an empty multi-dimensional (2-D) array of Card objects
      - Recommend using a fixed number for the number of columns, like 4 (declare a constant) 
        - *Challenge: Ensure your program still works when this number is changed
      - Then calculate the number of rows that will be needed to have enough cells for all the pairs of cards
      - Example: If there are 5 pairs and you use 4 for the number of columns, then you will need 3 rows to have enough spaces in the grid for all the pairs
    - Fill the array with Card objects
      - Use a loop to fill the array
      - It is recommended to add the Cards in pairs
      - It is ok to repeat values for pairs if there are more pairs than there are enum values for the Card value
      - *There may be some elements of the grid that are not set since there are more spaces than pairs of cards
  - shuffle method
    - This method will swap cards in the Grid so that they are randomly ordered
    - You will process over the array and for each Card (not null) you are going to randomly select another position to try to swap the Card with
    - As long as the randomly selected position is not null, you will swap the current card with the card at that random position
    - If you want to perform more shuffles, you could also make this method include a parameter for the number of times to shuffle and you could use a 
    	loop to have it shuffle that many times
  - getCard method
    - This method will take in a number that represents a position of a Card (The value passed to this method is the number in the grid minus 1)
    - If the position is invalid or there is no Card at that position it returns null.  Otherwise, it returns the Card.
  - getCardValue method
    - This method will take in a number that represents a position of a Card (The value passed to this method is the number in the grid minus 1)
    - If the position is invalid or there is no Card at that position it returns null.  Otherwise, it returns the value of the Card.
    - Use getCard as a helper method
  - showCard method
    - This method will take in a number that represents a position of a Card  (The value passed to this method is the number in the grid minus 1)
    - It will set that Card to be shown
    - Use getCard as a helper method
  - hideCard method
    - This method will take in a number that represents a position of a Card (The value passed to this method is the number in the grid minus 1)
    - It will set that Card to be hidden
    - Use getCard as a helper method
  - isCardShown method
    - This method will take in a number that represents a position of a Card (The value passed to this method is the number in the grid minus 1)
    - It will return the isShown value of the Card
    - Use getCard as a helper method
  - removeCard method
    - This method will take in a number that represents a position of a Card (The value passed to this method is the number in the grid minus 1)
    - It will set that position in cards to be null
  - hasCards method
    - This method will go through the cards using a loop.
    - If all elements are null, it will return false.
    - Otherwise it returns true.
  - getGridDisplay method
    - This method will return a String that represents the current state of the cards
    - Each row should be separated with a new line character ("\n")
    - Each column should be separated with a pipe ("|")
    - You may want to use the tab character ("\t") or spaces to give some extra space between values
    - Optionally, after each row, you can create a line of hyphens ("-")
    - If a Card is shown, you will use the value of the card.  If the Card is not shown, you will use the number to represent its position in the grid.  
    If there are empty spaces without a Card, you will show an "X"
    - Example for a Grid of 12 cards that are all still hidden:  "1\t|\t2\t|\t3\t|\t4\n5\t|\t6\t|\t7\t|\t8\n\t|\t9\t|\t10\t|\t11\t|\t12"
    - You are welcome to experiment with other ways to display the information as long as it has a grid shape when printed out.  *Note that the grid shape
    may get a little out of alignmnet when you are showing cards depending on the values that you use.

*Note: When referring to the position of a Card in the grid, it would be like the value of the index if all cards were in a one-dimensional array, so the count 
would start at 0.  When displaying the position to the user, it would be like menu choices so we start the count at 1.*

## GameDemo.java
This class has been provided for you and assumes you have defined all the appropriate methods within the other classes.  You may temporarily comment out 
code while testing, but do NOT remove or change code within this class.  If there is an error with the code for this class, it is something you need to 
fix in the implementation of your other classes rather than this one.
